package borneo.document.indexer.healthcheck;

import borneo.document.indexer.elasticsearch.ElasticSearchConnector;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest.Level;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;


/**
 * Elasticsearch health indicator
 *
 * @author rahul.r
 */
@Component
public class ElasticSearchHealthIndicator extends AbstractHealthIndicator {
    /**
     * LOG
     */
    public static final Logger LOG = LoggerFactory.getLogger(ElasticSearchHealthIndicator.class);

    /**
     * ES connector
     */
    @Autowired
    private ElasticSearchConnector esConnector;

    /**
     *
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        LOG.info("Elasticsearch health check requested!!!");
        if (this.esConnector == null || this.esConnector.getClient() == null) {
            builder.down();
        } else {
            ClusterHealthRequest request = Requests.clusterHealthRequest(new String[]{"_all"});
            request.level(Level.CLUSTER);
            request.timeout(TimeValue.timeValueMillis(5000L));
            ClusterHealthResponse response = this.esConnector.getClient().cluster().health(request,
                    RequestOptions.DEFAULT);
            switch (response.getStatus()) {
                case GREEN:
                case YELLOW:
                    builder.up();
                    break;
                case RED:
                default:
                    builder.down();
                    break;
            }
            builder.withDetail("clusterName", response.getClusterName());
            builder.withDetail("numberOfNodes", response.getNumberOfNodes());
            builder.withDetail("numberOfDataNodes", response.getNumberOfDataNodes());
            builder.withDetail("activePrimaryShards", response.getActivePrimaryShards());
            builder.withDetail("activeShards", response.getActiveShards());
            builder.withDetail("relocatingShards", response.getRelocatingShards());
            builder.withDetail("initializingShards", response.getInitializingShards());
            builder.withDetail("unassignedShards", response.getUnassignedShards());
        }
    }
}
