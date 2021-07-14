package borneo.document.indexer.services.impl;

import borneo.document.indexer.enums.ServiceErrorType;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.ParserData;
import borneo.document.indexer.services.Parser;
import borneo.document.indexer.utils.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Concrete class for the Parser interface which uses Apache Tika for parsing and extracting
 * the file data.
 */

@Component
public class TikaApi implements Parser {


    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TikaApi.class);

    /**
     * Tika object
     */
    private Tika tika;

    /**
     *
     */
    private final static Detector detector = TikaConfig.getDefaultConfig().getDetector();

    public TikaApi() {
        this.tika = new Tika();
    }


    /**
     * Description:
     *
     * @param path
     * @return ParserData
     * @throws ServiceException
     */
    public ParserData parseStringFromFile(String path) throws ServiceException {
        InputStream stream = null;
        ParserData data = null;
        try {
            stream = new FileInputStream(path);
            Tika tika = new Tika();
            Metadata metadata = new Metadata();
            data = new ParserData();
            String s = StringUtils.analyze(tika.parseToString(stream, metadata)); // Analyzing the string to reduce the network traffic.
            // Trade-off will be on the processing speed. This can be replaced with contents directly read from the file without altering.

            if (!this.isSupportedFormat(metadata.get(Metadata.CONTENT_TYPE))) {
                throw new ServiceException(ServiceErrorType.UNSUPPORTED_FORMAT);
            }
            data.setData(s);
            data.setType(metadata.get(Metadata.CONTENT_TYPE));
        } catch (FileNotFoundException ex) {
            logger.error("File not found : {}", path, ex);
            throw new ServiceException(ServiceErrorType.INVALID_FILE_PATH);
        } catch (IOException | TikaException e) {
            logger.error("Exception {}", e.getMessage());
            throw new ServiceException(ServiceErrorType.PARSER_EXCEPTION);
        }
        return data;
    }


    /**
     * Description:
     *
     * @return Tika
     */

    public Tika getTika() {
        return tika;
    }


    /**
     * Description:
     *
     * @param mediaType
     * @return
     * @throws IOException
     */
    public static boolean isSupportedFormat(String mediaType) throws IOException {
        if (mediaType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                || mediaType.equals("application/pdf"))
            return true;

        return false;
    }

}
