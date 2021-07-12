package borneo.document.indexer.services.impl;


import borneo.document.indexer.elasticsearch.ElasticSearchConnector;
import borneo.document.indexer.enums.ServiceErrorType;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.*;
import borneo.document.indexer.services.SearchEngine;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static borneo.document.indexer.constants.Constants.*;


@Component
public class SearchEngineImpl implements SearchEngine {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SearchEngineImpl.class);

    /**
     * es index
     */
    @Value("${elasticsearch.index}")
    private String index;

    /**
     * es type
     */
    @Value("${elasticsearch.type}")
    private String type;

    @Autowired
    private ElasticSearchConnector esConnector;

    /**
     * @param data
     * @throws ServiceException
     */
    @Override
    public void insert(SearchEngineData data) throws ServiceException {
        try {
            IndexRequest indexRequest = this.createInsertRequest(data);
            final IndexResponse response = this.esConnector.getClient().index(indexRequest, RequestOptions.DEFAULT);
            logger.info(" id created : {}", response.getId());
        } catch (Exception e) {
            logger.error("Document insertion failed ", e);
            throw new ServiceException(ServiceErrorType.INDEXING_FAILED);
        }
    }

    @Override
    public SearchResult query(SearchQuery query) throws ServiceException {
        SearchRequest searchRequest = this.createSearchRequest(query);
        SearchResponse searchResponse;
        SearchResult result = new SearchResult();
        try {
            searchResponse = this.esConnector.getClient().search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                Document document = new Document();
                Map<String, Object> source = hit.getSourceAsMap();
                document.setDocumentFormat((String) source.get(DOCUMENT_FORMAT));
                document.setDocumentName((String) source.get(DOCUMENT_NAME));
                document.setDocumentUrl((String) source.get(DOCUMENT_URL));
                result.getResults().add(document);
            }

        } catch (IOException e) {
            throw new ServiceException(ServiceErrorType.SEARCH_QUERY_FAILED);
        }
        return result;
    }

    /**
     * @param query
     * @return
     * @throws ServiceException
     */
    @Override
    public SearchResult query(SearchMultiQuery query) throws ServiceException {
        MultiSearchRequest searchRequest = this.createMultiSearchRequest(query);
        MultiSearchResponse multiSearchResponse;
        SearchResult result = new SearchResult();
        try {
            multiSearchResponse = this.esConnector.getClient().msearch(searchRequest, RequestOptions.DEFAULT);
            MultiSearchResponse.Item[] items = multiSearchResponse.getResponses();
            for (MultiSearchResponse.Item item : items) {
                for (SearchHit hit : item.getResponse().getHits()) {
                    Document document = new Document();
                    Map<String, Object> source = hit.getSourceAsMap();
                    document.setDocumentFormat((String) source.get(DOCUMENT_FORMAT));
                    document.setDocumentName((String) source.get(DOCUMENT_NAME));
                    document.setDocumentUrl((String) source.get(DOCUMENT_URL));
                    result.getResults().add(document);
                }
            }

        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    /**
     * @param data SearchEngineData
     * @return IndexRequest
     * @throws IOException
     */
    private IndexRequest createInsertRequest(SearchEngineData data) throws IOException {
        return new IndexRequest(this.index).id(data.getId())
                .source(XContentFactory.jsonBuilder()
                        .startObject()
                        .field(DOCUMENT_ID, data.getId())
                        .field(DOCUMENT_DATA, data.getData())
                        .field(DOCUMENT_NAME, data.getDocumentName())
                        .field(DOCUMENT_PATH, data.getDocumentPath())
                        .field(DOCUMENT_FORMAT, data.getDocumentFormat())
                        .field(DOCUMENT_URL, data.getUrl())
                        .endObject());
    }

    /**
     * @param query
     * @return
     */

    private SearchRequest createSearchRequest(SearchQuery query) {
        SearchRequest searchRequest = new SearchRequest(this.index);
        String[] includeFields = new String[]{DOCUMENT_NAME, DOCUMENT_PATH, DOCUMENT_FORMAT, DOCUMENT_URL};
        String[] excludeFields = new String[]{DOCUMENT_DATA};
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        sourceBuilder.fetchSource(includeFields, excludeFields);
        sourceBuilder.query(QueryBuilders.matchQuery(DOCUMENT_DATA, query.getKeyword()));
        sourceBuilder.fetchSource(true);
        searchRequest.source(sourceBuilder);
        return searchRequest;
    }

    /**
     * @param query
     * @return
     */
    private MultiSearchRequest createMultiSearchRequest(SearchMultiQuery query) {
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
        String[] includeFields = new String[]{DOCUMENT_NAME, DOCUMENT_PATH, DOCUMENT_FORMAT, DOCUMENT_URL};
        String[] excludeFields = new String[]{DOCUMENT_DATA};
        for (String keyword : query.getKeywords()) {
            SearchRequest searchRequest = new SearchRequest(this.index);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            sourceBuilder.fetchSource(includeFields, excludeFields);
            sourceBuilder.query(QueryBuilders.matchQuery(DOCUMENT_DATA, keyword));
            sourceBuilder.fetchSource(true);
            searchRequest.source(sourceBuilder);
            multiSearchRequest.add(searchRequest);
        }
        return multiSearchRequest;
    }

}
