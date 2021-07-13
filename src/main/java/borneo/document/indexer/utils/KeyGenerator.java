package borneo.document.indexer.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * Utility class for UUID/Key generation
 */
public class KeyGenerator {

    /**
     * @return UUID
     */
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * @return
     */
    public static String generateRandomUniqueString() {
        return RandomStringUtils.random(30, Boolean.TRUE, Boolean.TRUE);
    }
}
