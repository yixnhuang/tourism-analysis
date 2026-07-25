package A_DataCapture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Util.GetDocument;

/**
 * Extract data from parsed elements
 */
public class c_GetDataFromParseElement {

    //Get all links
    public static List<String> getLinks(Document doc) {
        ArrayList<String> list = new ArrayList<>();
        Elements href = doc.select("a[href]");
        for (Element element : href) {
            list.add(element.tagName() + "$" + element.attr("abs:href") + "(" + element.text() + ")");
        }
        return list;
    }

    //Get images
    public static List<String> getMedia(Document doc) {
        List<String> list = new ArrayList<>();
        Elements src = doc.select("[src]");
        for (Element element : src) {
            if (element.tagName().equals("img")) {
                list.add(element.tagName() + "$" + element.attr("abs:src"));
            }
        }
        return list;
    }

    //Get link[href] references
    public static List<String> getImports(Document doc) {
        ArrayList<String> list = new ArrayList<>();
        Elements href = doc.select("link[href]");
        for (Element element : href) {
            list.add(element.tagName() + "$" + element.attr("abs:href") + "(" + element.attr("rel") + ")");
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/hotel.ctrip.com.txt";
        //Load the Document object from filePath
        Document doc = GetDocument.getDoc(filePath);
        List<String> links = c_GetDataFromParseElement.getLinks(doc);
        List<String> media = c_GetDataFromParseElement.getMedia(doc);
        List<String> imports = c_GetDataFromParseElement.getImports(doc);
        System.out.println("links:"+links.size()+",media:"+media.size()+",imports:"+imports.size());
        for (int i = 0; i < links.size(); i++)
            System.out.println(links.get(i));
        for (int i = 0; i < media.size(); i++)
            System.out.println(media.get(i));
        for (int i = 0; i < imports.size(); i++)
            System.out.println(imports.get(i));
    }

}
