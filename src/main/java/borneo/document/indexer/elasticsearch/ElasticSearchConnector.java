package borneo.document.indexer.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.transport.TransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Elasticsearch Client connection class.
 */
public class ElasticSearchConnector {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConnector.class);

    /**
     * The ES TransportClient instance
     */
    private RestHighLevelClient client = null;

    /**
     * ES index name
     **/
    private String index;

    /**
     * index type
     */
    private String type;

    /**
     * Description:
     *
     * @param host
     * @param port
     * @param userName
     * @param scheme
     * @param password
     * @throws Exception
     */
    public ElasticSearchConnector(String host, Integer port, String userName, String password, String scheme)
            throws Exception {
        this.client = this.getConnector(host, port, userName, password, scheme);
    }

    /**
     * Description: Returns the RestHighLevelClient for ES
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param scheme
     * @return
     * @throws Exception
     */
    private RestHighLevelClient getConnector(String host, Integer port, String userName, String password, String scheme)
            throws Exception {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
            RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, scheme));
            builder.setHttpClientConfigCallback(
                    httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                            .setDefaultIOReactorConfig(IOReactorConfig.custom()
                                    .setSoKeepAlive(true)
                                    .build())
                            .setKeepAliveStrategy((response, context) -> 30 * 1000));
            this.client = new RestHighLevelClient(builder);
        } catch (TransportException e) {
            LOGGER.error("TransportException occurred while creating ES client", e);
            throw e;
        }
        return client;
    }

    /**
     * Description: Returns the client.
     *
     * @return TransportClient
     */
    public RestHighLevelClient getClient() {
        return client;
    }

}
