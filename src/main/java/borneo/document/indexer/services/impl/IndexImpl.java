package borneo.document.indexer.services.impl;

import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.enums.Messages;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.IndexDocumentDrive;
import borneo.document.indexer.models.IndexDocumentLocal;
import borneo.document.indexer.models.ParserData;
import borneo.document.indexer.models.SearchEngineData;
import borneo.document.indexer.services.DriveApi;
import borneo.document.indexer.services.Index;
import borneo.document.indexer.services.SearchEngine;
import borneo.document.indexer.utils.KeyGenerator;
import com.dropbox.core.DbxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static borneo.document.indexer.constants.Constants.*;

@Component
public class IndexImpl implements Index {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(IndexImpl.class);

    @Autowired
    private TikaApi tikaApi;

    @Autowired
    private SearchEngine searchEngine;

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

    private Map<String, String> metatypeExtensionMapping;

    @PostConstruct
    private void constructMetatypeExtensionMapping() {
        this.metatypeExtensionMapping = new HashMap<>();
        this.metatypeExtensionMapping.put(PDF_METATYPE, PDF_EXTENSION);
        this.metatypeExtensionMapping.put(DOCX_METATYPE, DOCX_EXTENSION);
    }

    @Override
    public IndexFromLocalResponse indexFromLocal(IndexDocumentLocal indexDocumentLocal) throws ServiceException {
        ParserData fileContents = this.tikaApi.parseStringFromFile(indexDocumentLocal.getPath());
        String id = KeyGenerator.generateRandomUniqueString();
        String file = id + "." + this.metatypeExtensionMapping.get(fileContents.getType());
        String drivePath = this.drivePath + "/" + file;
        String url = this.driveApi.upload(indexDocumentLocal.getPath(), drivePath);
        this.searchEngine.insert(new SearchEngineData(id, fileContents.getData(), this.drivePath,
                fileContents.getType(), file, url));
        IndexFromLocalResponse response = new IndexFromLocalResponse(indexDocumentLocal.getPath(), drivePath, url,
                Messages.INDEX_FROM_LOCAL_SUCCESS.getMessage());
        return response;
    }

    @Override
    public IndexFromDriveResponse indexFromDrive(IndexDocumentDrive indexDocumentDrive) throws ServiceException {
        try {
            String downloadedFile = this.driveApi.download(indexDocumentDrive.getPath(), this.localPath);
            String id = KeyGenerator.generateRandomUniqueString();
            String url = this.driveApi.getDownloadLink(indexDocumentDrive.getPath());
            ParserData fileContents = this.tikaApi.parseStringFromFile(downloadedFile);
            this.searchEngine.insert(new SearchEngineData(id, fileContents.getData(), this.drivePath,
                    fileContents.getType(), indexDocumentDrive.getPath(), url));
            if (!new File(downloadedFile).delete()) {
                logger.error("File not deleted!!!"); // Not an exception as a this can be logged to queue and be cleaned up asynchronously.
            }
            IndexFromDriveResponse response = new IndexFromDriveResponse(indexDocumentDrive.getPath(), url,
                    Messages.INDEX_FROM_DRIVE_SUCCESS.getMessage());
            return response;
        } catch (IOException | DbxException e) {
            logger.error("Exception:", e);
            throw new ServiceException(e);
        }

    }

}
