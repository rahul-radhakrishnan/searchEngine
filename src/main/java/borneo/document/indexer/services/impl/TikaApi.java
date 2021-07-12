package borneo.document.indexer.services.impl;

import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.ParserData;
import borneo.document.indexer.services.Parser;
import borneo.document.indexer.utils.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Component
public class TikaApi implements Parser {

    /**
     * Tika object
     */
    private Tika tika;

    private final static Detector detector = TikaConfig.getDefaultConfig().getDetector();

    public TikaApi() {
        this.tika = new Tika();
    }


    /**
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
            String s = StringUtils.tokenise(tika.parseToString(stream, metadata));

            if (!this.isSupportedFormat(metadata.get(Metadata.CONTENT_TYPE))) {
                throw new ServiceException("Not Supported");
            }
            data.setData(s);
            data.setType(metadata.get(Metadata.CONTENT_TYPE));
        } catch (IOException | TikaException e) {
            throw new ServiceException(e);
        }
        return data;
    }


    /**
     * @return Tika
     */

    public Tika getTika() {
        return tika;
    }


    /**
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
