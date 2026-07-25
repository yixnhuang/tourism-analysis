package A_DataCapture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Util.GetDocument;
import Util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.HotelCity;

public class d_GetAllCityName {

    /**
     * Return information for all cities
     *
     * @param doc
     * @return
     */
    public static List<HotelCity> getAllCitys(Document doc) {
        ArrayList<HotelCity> cities = new ArrayList<>();

        Elements pinyin_filter_elements = doc.getElementsByClass("pinyin_filter_detail layoutfix");

        //Select the first element that contains all cities
        Element pinyin_filter = pinyin_filter_elements.first();

        //All dd elements
        Elements all_dd = pinyin_filter.getElementsByTag("dd");

        //All dt elements
        Elements all_dt = pinyin_filter.getElementsByTag("dt");


        for (int i = 0; i < all_dt.size(); i++) {

            //Find the i-th dt element
            Element dt_headPinyin = all_dt.get(i);

            //Find the i-th dt element
            Element dd_Info = all_dd.get(i);

            //Find all child links under the i-th dd element
            Elements all_Info = dd_Info.children();

            for (Element element : all_Info) {

                HotelCity hotelCity = new HotelCity();
                //cityID
                //Extract digits with StringUtil
                String cityID = StringUtil.getNumbers(element.attr("href"));
                hotelCity.setCityId(cityID);

                //cityName
                String cityName = element.text();
                hotelCity.setCityName(cityName);

                //headPinyin
                String headPinyin = dt_headPinyin.text();
                hotelCity.setHeadPinyin(headPinyin);

                //pinyin
                String[] href = element.attr("href").split("/");
                //After splitting, derive the pinyin length from the final segment and city ID
                int length_piniyin = href[href.length - 1].length() - cityID.length();
                String pinyin = href[href.length - 1].substring(0, length_piniyin);
                hotelCity.setPinyin(pinyin);

                cities.add(hotelCity);
            }
        }
        return cities;
    }

    public static void main(String[] args) throws IOException {

        //Load the Document object from the file path
        Document doc = GetDocument.getDoc("src/main/resources/hotels.ctrip.com_domestic-city-hotel.txt");
        List<HotelCity> allCitys = d_GetAllCityName.getAllCitys(doc);
        for (int i = 0; i < allCitys.size(); i++) {
            System.out.println(allCitys.get(i));
        }
    }
}
