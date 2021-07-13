package borneo.document.indexer.services;

import borneo.document.indexer.common.IntegrationTest;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.ParserData;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

/**
 * Test class for the parser interface.
 */
public class ParserTest extends IntegrationTest {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ParserTest.class);

    @Autowired
    private Parser parserApi;

    private File pdfFile;

    private File textFile;

    private File docxFile;

    private final String testString = " the quick brown fox jumps over the lazy dog";

    /**
     * Inits the test setup
     */
    @Before
    public void setUp() {
        this.docxFile = FileUtils.getFile("src", "test", "resources", "test.docx");
        this.pdfFile = FileUtils.getFile("src", "test", "resources", "test.pdf");
        this.textFile = FileUtils.getFile("src", "test", "resources", "test.txt");
    }

    /**
     * Test the docx file.
     */
    @Test
    public void testDocFile() throws ServiceException {
        ParserData parsedData = this.parserApi.parseStringFromFile(this.docxFile.getPath());
        assertEquals(this.testString, parsedData.getData());
    }

    /**
     * Test the pdf file parsing.
     *
     * @throws ServiceException
     */
    @Test
    public void testPdfFile() throws ServiceException {
        ParserData parsedData = this.parserApi.parseStringFromFile(this.pdfFile.getPath());
        assertEquals(this.testString, parsedData.getData());
    }

    /**
     * Test the txt file which is not supported.
     */
    @Test
    public void testTxtFile() {
        ParserData parsedData = null;
        Boolean exceptionThrown = false;
        try {
            parsedData = this.parserApi.parseStringFromFile(this.textFile.getPath());
        } catch (ServiceException e) {
            exceptionThrown = true;
        }
        assertEquals(exceptionThrown, Boolean.TRUE);
    }
}
