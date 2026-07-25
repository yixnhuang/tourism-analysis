package A_DataCapture;

import Util.GetDocument;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Collect data from the Ctrip travel website
 */
public class a_GetSourceData {
    public static Document getSourceData() throws IOException {
        String nextLine = "src/main/resources/www.ctrip.com.txt/";
        Document doc = null;
        return GetDocument.getDoc(nextLine);
    }
}
