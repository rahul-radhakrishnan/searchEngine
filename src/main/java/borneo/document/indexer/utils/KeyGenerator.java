package borneo.document.indexer.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * Utility class for UUID/Key generation
 */
public class KeyGenerator {

    /**
     * Description:
     *
     * @return UUID
     */
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * Description:
     *
     * @return
     */
    public static String generateRandomUniqueString() {
        return RandomStringUtils.random(30, Boolean.TRUE, Boolean.TRUE);
    }
}
