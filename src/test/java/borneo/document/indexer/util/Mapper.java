package borneo.document.indexer.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Utility class for object mapping
 *
 * @author RahulRadhakrishnan
 */
public final class Mapper {

    // Object mapper
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @param object
     * @param <T>
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> String writeValueAsString(T object)
            throws JsonGenerationException, JsonMappingException, IOException {
        return mapper.writeValueAsString(object);
    }

    /**
     * @param content
     * @param clz
     * @param <T>
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T readValue(String content, Class<T> clz)
            throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(content, clz);
    }
}
