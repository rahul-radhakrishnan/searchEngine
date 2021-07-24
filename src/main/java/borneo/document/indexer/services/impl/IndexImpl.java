package borneo.document.indexer.services.impl;

import borneo.document.indexer.api.requests.IndexDocumentDriveRequest;
import borneo.document.indexer.api.requests.IndexDocumentLocalRequest;
import borneo.document.indexer.api.responses.DocumentDeleteResponse;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.enums.Messages;
import borneo.document.indexer.enums.ServiceErrorType;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.DocumentDeleteQuery;
import borneo.document.indexer.models.DocumentSearchQuery;
import borneo.document.indexer.models.ParserData;
import borneo.document.indexer.models.SearchEngineData;
import borneo.document.indexer.services.DriveApi;
import borneo.document.indexer.services.Index;
import borneo.document.indexer.services.Parser;
import borneo.document.indexer.services.SearchEngine;
import borneo.document.indexer.utils.KeyGenerator;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static borneo.document.indexer.constants.Constants.*;

/**
 *
 */
@Component
public class IndexImpl implements Index {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(IndexImpl.class);

    /**
     * The Apache Tika Driver object.
     */
    @Autowired
    private Parser parser;

    /**
     * The Search Engine object.
     */
    @Autowired
    private SearchEngine searchEngine;

    /**
     * The Drive Access object.
     */
    @Autowired
    private DriveApi driveApi;

    /**
     * default documents drive path
     */
    @Value("${index.drivePath}")
    private String drivePath;

    /**
     * default documents drive path
     */
    @Value("${index.localPath}")
    private String localPath;

    /**
     * The supported format mapping
     */
    private Map<String, String> metatypeExtensionMapping;

    /**
     * Construct the mapping post the bean construction.
     */
    @PostConstruct
    private void constructMetatypeExtensionMapping() {
        this.metatypeExtensionMapping = new HashMap<>();
        this.metatypeExtensionMapping.put(PDF_METATYPE, PDF_EXTENSION);
        this.metatypeExtensionMapping.put(DOCX_METATYPE, DOCX_EXTENSION);
    }

    /**
     * Description:
     *
     * @param indexDocumentLocalRequest
     * @return
     * @throws ServiceException
     */
    @Override
    public IndexFromLocalResponse indexFromLocal(IndexDocumentLocalRequest indexDocumentLocalRequest) throws ServiceException {
        ParserData fileContents = this.parser.parseStringFromFile(indexDocumentLocalRequest.getPath());
        String id = KeyGenerator.generateRandomUniqueString();
        String fileName = FilenameUtils.getName(indexDocumentLocalRequest.getPath());
        try {
            String driveFilePath = this.drivePath + FilenameUtils.getName(indexDocumentLocalRequest.getPath());
            String url = this.driveApi.upload(indexDocumentLocalRequest.getPath(), driveFilePath, indexDocumentLocalRequest.isOverwrite());
            if (indexDocumentLocalRequest.isOverwrite() && this.searchEngine.documentAlreadyExists(new DocumentSearchQuery(fileName, this.drivePath))) {
                this.searchEngine.deleteDocument(new DocumentDeleteQuery(fileName, this.drivePath));
            }
            this.searchEngine.insert(new SearchEngineData(id, fileContents.getData(), this.drivePath,
                    fileContents.getType(), fileName, url));
            return new IndexFromLocalResponse(indexDocumentLocalRequest.getPath(), driveFilePath, url,
                    Messages.INDEX_FROM_LOCAL_SUCCESS.getMessage());
        } catch (ServiceException ex) {
            throw ex;
        } finally {
            if (!new File(this.localPath + fileName).delete()) {
                logger.error("File not deleted!!!"); // Not an exception as a this can be logged to queue and be cleaned up asynchronously.
            }
        }
    }

    /**
     * Description:
     *
     * @param indexDocumentDriveRequest
     * @return
     * @throws ServiceException
     */
    @Override
    public IndexFromDriveResponse indexFromDrive(IndexDocumentDriveRequest indexDocumentDriveRequest) throws ServiceException {
        String path = FilenameUtils.getFullPath(indexDocumentDriveRequest.getPath());
        String fileName = FilenameUtils.getName(indexDocumentDriveRequest.getPath());
        String id = KeyGenerator.generateRandomUniqueString();
        String downloadedFile = this.localPath + id;
        try {
            if (this.searchEngine.documentAlreadyExists(new DocumentSearchQuery(fileName, path))) {
                throw new ServiceException(ServiceErrorType.DOCUMENT_ALREADY_EXISTS);
            }
            String url = this.driveApi.download(indexDocumentDriveRequest.getPath(),
                    downloadedFile);
            ParserData fileContents = this.parser.parseStringFromFile(downloadedFile);
            this.searchEngine.insert(new SearchEngineData(id, fileContents.getData(), path,
                    fileContents.getType(), fileName, url));
            return new IndexFromDriveResponse(indexDocumentDriveRequest.getPath(), url,
                    Messages.INDEX_FROM_DRIVE_SUCCESS.getMessage());
        } catch (ServiceException ex) {
            logger.error("Exception while downloading the drive file : {}", indexDocumentDriveRequest.getPath(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error("Exception while downloading the drive file : {}", indexDocumentDriveRequest.getPath(), ex);
            throw new ServiceException(ServiceErrorType.DOWNLOAD_FAILED);
        } finally {
            if (!new File(downloadedFile).delete()) {
                logger.error("File not deleted!!!"); // Not an exception as a this can be logged to queue and be cleaned up asynchronously.
            }
        }
    }

    /**
     * Description:
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    @Override
    public DocumentDeleteResponse deleteDocument(DocumentDeleteQuery query) throws ServiceException {
        this.driveApi.deleteFile(query.getDocumentDrivePath() + query.getDocumentName());
        this.searchEngine.deleteDocument(query);
        logger.info("Deletion success : {}", query.getDocumentDrivePath());
        return new DocumentDeleteResponse(query.getDocumentDrivePath() + query.getDocumentName(),
                Messages.DOCUMENT_DELETED_SUCCESS.getMessage());
    }

}
