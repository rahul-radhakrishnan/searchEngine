package borneo.document.indexer.controllers.test;

import borneo.document.indexer.api.controllers.IndexController;
import borneo.document.indexer.api.requests.IndexDocumentDriveRequest;
import borneo.document.indexer.api.requests.IndexDocumentLocalRequest;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.application.DocumentIndexerApplication;
import borneo.document.indexer.application.DocumentIndexerControllerAdvice;
import borneo.document.indexer.services.Index;
import borneo.document.indexer.util.Mapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the Index Controller
 *
 * @author rahul.radhakrishnan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DocumentIndexerApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class IndexControllerTest {

    @InjectMocks
    private IndexController controller = new IndexController();

    @Mock
    private Index index;

    private DocumentIndexerControllerAdvice advice;

    private MockMvc mvc;

    private IndexDocumentLocalRequest indexDocumentLocalRequest;

    private IndexDocumentDriveRequest indexDocumentDriveRequest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.advice = new DocumentIndexerControllerAdvice();
        this.mvc = MockMvcBuilders.standaloneSetup(this.controller).setControllerAdvice(this.advice).build();
        this.indexDocumentLocalRequest = new IndexDocumentLocalRequest("path");
        this.indexDocumentDriveRequest = new IndexDocumentDriveRequest("path");
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

}
