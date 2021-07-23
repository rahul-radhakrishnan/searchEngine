package borneo.document.indexer.services;

import borneo.document.indexer.common.Constants;
import borneo.document.indexer.common.IntegrationTest;
import borneo.document.indexer.exceptions.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The DriverApi Integration Test
 */
public class DriveApiTest extends IntegrationTest {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DriveApiTest.class);

    @Autowired
    private DriveApi driveApi;

    private String localPath;


    @Before
    public void setUp() {
        this.localPath = System.getProperty("user.dir") + "/" + "test.pdf";
    }

    @Test
    public void testSuccess() throws Exception {
        String file = this.driveApi.download(Constants.DRIVE_PATH, this.localPath);
        assertEquals("https://www.dropbox.com/s/5toypp8guburbyx/test.pdf?dl=0", file);
    }

    /**
     * Provide an invalid path and expect the Service Layer exception
     */
    @Test
    public void testFailure() throws Exception {
        boolean exceptionThrown = false;
        try {
            this.driveApi.download(Constants.INVALID_DRIVE_PATH, this.localPath);
        } catch (ServiceException ex) {
            logger.info("As expected");
            exceptionThrown = true;
        }
        assertEquals(exceptionThrown, Boolean.TRUE);
    }


    @After
    public void destroy() {
        if (new File(this.localPath).exists()) {
            new File(this.localPath).delete();
        }
    }
}
