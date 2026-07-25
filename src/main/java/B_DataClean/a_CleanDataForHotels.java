package B_DataClean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Util.GetDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * Remove irrelevant content before extracting hotel data
 */
public class a_CleanDataForHotels {


    public static List<String> cleanHTML(Document doc) {
        ArrayList<String> list = new ArrayList<>();

        String clean_bas = Jsoup.clean(doc.toString(), Whitelist.basic());
        String clean_sim = Jsoup.clean(doc.toString(), Whitelist.simpleText());

        list.add(clean_bas);
        list.add(clean_sim);

        return list;
    }

    public static void main(String[] args) throws IOException {

        String filePath = "src/main/resources/hotels.ctrip.com_domestic-city-hotel.txt";
        //Load the Document object from filePath
        Document doc = GetDocument.getDoc(filePath);
        List<String> cleanHTML = a_CleanDataForHotels.cleanHTML(doc);
        for (String s : cleanHTML) {
            System.out.println(s);
        }
    }

}
