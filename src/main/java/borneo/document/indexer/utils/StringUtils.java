package borneo.document.indexer.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class StringUtils {


    private static String regex;
    private static Pattern pattern;

    static {
        regex = "\\s+";
        pattern = Pattern.compile(regex);
    }

    public static String tokenise(String str) {
        Matcher matcher = pattern.matcher(str);
        //Replacing all space characters with single space
        return matcher.replaceAll(" ").replaceAll("[^a-zA-Z0-9]", " ");
    }

}
