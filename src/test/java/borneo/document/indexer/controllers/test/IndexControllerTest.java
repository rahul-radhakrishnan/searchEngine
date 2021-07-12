package borneo.document.indexer.controllers.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import borneo.document.indexer.api.controllers.IndexController;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.requests.IndexDocumentDrive;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.application.DocumentIndexerControllerAdvice;
import borneo.document.indexer.api.requests.IndexDocumentLocal;
import borneo.document.indexer.util.Mapper;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.services.Index;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * Test class for the Index Controller
 *
 * @author rahul.radhakrishnan
 */
@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @InjectMocks
    private IndexController controller = new IndexController();

    @Mock
    private Index index;

    private DocumentIndexerControllerAdvice advice;

    private MockMvc mvc;

    private IndexDocumentLocal indexDocumentLocalRequest;

    private IndexDocumentDrive indexDocumentDriveRequest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.advice = new DocumentIndexerControllerAdvice();
        this.mvc = MockMvcBuilders.standaloneSetup(this.controller).setControllerAdvice(this.advice).build();
        this.indexDocumentLocalRequest = new IndexDocumentLocal("path");
        this.indexDocumentDriveRequest = new IndexDocumentDrive("path");
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
    public void testIndexFromLocalPositive() throws Exception {
        IndexFromLocalResponse response = new IndexFromLocalResponse("localPath",
                "drivePath", "url", "message");
        when(this.index.indexFromLocal(this.indexDocumentLocalRequest)).thenReturn(response);
        this.mvc.perform(post("/indexLocal").contentType(MediaType.APPLICATION_JSON)
                .content(Mapper.writeValueAsString(this.indexDocumentLocalRequest)))
                .andExpect(status().isOk());
    }

    /**
     * Test positive scenario
     *
     * @throws Exception
     */
    @Test
    public void testIndexFromDrivePositive() throws Exception {
        IndexFromDriveResponse response = new IndexFromDriveResponse("drivePath", "url", "message");
        when(this.index.indexFromDrive(this.indexDocumentDriveRequest)).thenReturn(response);
        this.mvc.perform(post("/indexDrive").contentType(MediaType.APPLICATION_JSON)
                .content(Mapper.writeValueAsString(this.indexDocumentDriveRequest)))
                .andExpect(status().isOk());
    }

    /**
     * Negative Scenario
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testIndexFromLocalNegative() throws Exception {
        when(this.index.indexFromDrive(this.indexDocumentDriveRequest)).thenThrow(ApiException.class);
        this.mvc.perform(post("/indexDrive").contentType(MediaType.APPLICATION_JSON)
                .content(Mapper.writeValueAsString(this.indexDocumentDriveRequest)))
                .andExpect(status().isInternalServerError());
    }


}
