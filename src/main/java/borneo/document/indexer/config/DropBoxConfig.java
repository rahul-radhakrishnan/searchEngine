package borneo.document.indexer.config;

import borneo.document.indexer.constants.Constants;
import borneo.document.indexer.services.impl.DropBoxApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The DropBox bean creation configuration.
 */
@Configuration
public class DropBoxConfig {

    /**
     * dropbox accesskey
     */
    @Value("${dropbox.accesskey}")
    private String accesskey;

    /**
     * dropbox app
     */
    @Value("${dropbox.app}")
    private String app;

    /**
     * logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(DropBoxApi.class);

    /**
     * Description:
     *
     * @return
     * @throws Exception
     */
    @Bean(Constants.DROPBOX_CONNECTOR)
    public DropBoxApi dropBoxApi() throws Exception {
        LOG.info("Dropbox bean creation ");
        return new DropBoxApi(this.accesskey, this.app);
    }
}
