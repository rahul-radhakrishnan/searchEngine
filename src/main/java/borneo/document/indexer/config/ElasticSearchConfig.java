package borneo.document.indexer.config;

import borneo.document.indexer.constants.Constants;
import borneo.document.indexer.elasticsearch.ElasticSearchConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * The configuration class for the ES Connector client bean creation.
 * 
 * @author rahul.r
 *
 */
@Configuration
public class ElasticSearchConfig {

	/** es cluster name */
	@Value("${elasticsearch.clusterName}")
	private String clusterName;

	/** es user name */
	@Value("${elasticsearch.userName}")
	private String userName;

	/** es password */
	@Value("${elasticsearch.password}")
	private String password;

	/** es host */
	@Value("${elasticsearch.host}")
	private String host;

	/** es port */
	@Value("${elasticsearch.port}")
	private Integer port;

	/** es host */
	@Value("${elasticsearch.scheme}")
	private String scheme;

	/** logger */
	private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchConfig.class);

	/**
	 * Create ES connector database connector.
	 *
	 * @return the ES connector
	 * @throws Exception
	 */
	@Bean(Constants.ES_CONNECTOR)
	public ElasticSearchConnector esConnector() throws Exception {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		LOG.info("Cluster building started for ES ");
		return new ElasticSearchConnector(this.host, this.port, this.userName, this.password, this.scheme);
	}
}
