package borneo.document.indexer.common;


import java.io.IOException;
import java.util.Properties;

import borneo.document.indexer.config.DropBoxConfig;
import borneo.document.indexer.config.ElasticSearchConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author RahulRadhakrishnan
 */
@SuppressWarnings("deprecation")
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTest.SpringTestConfig.class, ElasticSearchConfig.class,
        DropBoxConfig.class})
public class IntegrationTest {

    /**
     *
     */
    public IntegrationTest() {
        super();
    }

    /**
     * Inner class to fill config gaps which are normally handled by Spring Boot
     */
    @Configuration
    @ComponentScan({"borneo.document.indexer.*"})
    public static class SpringTestConfig {

        /**
         * Simulates the application.yml
         *
         * @return properties
         * @throws IOException
         */
        @Bean
        public PropertyPlaceholderConfigurer props() throws IOException {
            PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
            Properties properties = new Properties();
            properties.setProperty("elasticsearch.host", "localhost");
            properties.setProperty("elasticsearch.port", "9200");
            properties.setProperty("elasticsearch.clusterName", "indexer");
            properties.setProperty("elasticsearch.userName", " ");
            properties.setProperty("elasticsearch.password", " ");
            properties.setProperty("elasticsearch.scheme", "http");
            properties.setProperty("elasticsearch.index", "indexer");
            properties.setProperty("elasticsearch.type", " ");
            properties.setProperty("elasticsearch.type", "es");

            properties.setProperty("dropbox.accesskey", "DyM650aKB6IAAAAAAAAAAfLhrwNQmgGy4SmGPnqKPFf5JyeAp6EqagvEx449BMVX");
            properties.setProperty("dropbox.app", "testApp");
            properties.setProperty("index.drivePath", "/documents");
            properties.setProperty("index.localPath", "/Users/rahulradhakrishnan/Documents/dropbox");
            ppc.setProperties(properties);
            return ppc;
        }

    }

}
