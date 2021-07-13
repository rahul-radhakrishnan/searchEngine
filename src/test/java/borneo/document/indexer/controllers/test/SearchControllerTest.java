package borneo.document.indexer.controllers.test;


import borneo.document.indexer.api.controllers.SearchController;
import borneo.document.indexer.application.DocumentIndexerApplication;
import borneo.document.indexer.application.DocumentIndexerControllerAdvice;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.SearchQuery;
import borneo.document.indexer.models.SearchResult;
import borneo.document.indexer.services.SearchEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Test class for the Index Controller
 *
 * @author rahul.radhakrishnan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DocumentIndexerApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class SearchControllerTest {

    @InjectMocks
    private SearchController controller = new SearchController();

    @Mock
    private SearchEngine searchEngine;

    private DocumentIndexerControllerAdvice advice;

    private MockMvc mvc;

    private SearchResult result;

    private SearchQuery query;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.advice = new DocumentIndexerControllerAdvice();
        this.mvc = MockMvcBuilders.standaloneSetup(this.controller).setControllerAdvice(this.advice).build();
        this.result = new SearchResult();
        this.query = new SearchQuery("keyword");
    }

    @After
    public void tearDown() throws Exception {
        this.controller = null;
    }

    /**
     * Test positive scenario
     *
     * @throws Exception
     */
    @Test
    public void testSearchPositive() throws Exception {
        when(this.searchEngine.query(this.query)).thenReturn(this.result);
        this.mvc.perform(get("/search/{keyword}", "keyword"))
                .andExpect(status().isOk());
    }

    /**
     * Negative Scenario
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSearchNegative() throws Exception {
        when(this.searchEngine.query(this.query)).thenThrow(ServiceException.class);
        this.mvc.perform(get("/search/{keyword}", ""))
                .andExpect(status().isNotFound());
    }

}
