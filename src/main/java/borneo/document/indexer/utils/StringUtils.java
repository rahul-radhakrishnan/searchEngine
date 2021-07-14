package borneo.document.indexer.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * String Utility class.
 */
public class StringUtils {

    /**
     * Description:
     *
     * @param contents
     * @return
     * @throws IOException
     */
    public static String analyze(String contents) throws IOException {
        return analyze(contents, new StandardAnalyzer());
    }

    /**
     * Description:
     *
     * @param text
     * @param analyzer
     * @return
     * @throws IOException
     */
    private static String analyze(String text, Analyzer analyzer) throws IOException {
        String result = "";
        TokenStream tokenStream = analyzer.tokenStream("contents", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result = result + " " + attr.toString();
        }
        return result;
    }

}
