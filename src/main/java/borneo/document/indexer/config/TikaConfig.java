package borneo.document.indexer.config;

import borneo.document.indexer.constants.Constants;
import borneo.document.indexer.dropbox.DropBoxApi;
import borneo.document.indexer.services.impl.TikaApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 *  The Tika bean creation configuration.
 */
@Configuration
public class TikaConfig {

    /**
     * logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(DropBoxApi.class);

    @Bean(Constants.TIKA_API)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public TikaApi dropBoxApi() throws Exception {
        LOG.info("TikaApi bean creation ");
        return new TikaApi();
    }
}
