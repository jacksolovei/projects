import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MetroMap {
    private JSONObject map = new JSONObject();
    private JSONArray lines = new JSONArray();
    private JSONArray stationsArr = new JSONArray();

    public JSONObject getMap() {
        try {
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements elementsMetro = doc.select("div#metrodata");
            Elements elementStations = elementsMetro.select("div.js-depend");
            Elements elementLines = elementsMetro.select("span.js-metro-line");

            for (Element element : elementLines) {
                String lineNumber = element.attr("data-line");
                doc.getElementsByTag("span");
                JSONObject jObject = new JSONObject();
                jObject.put("number", lineNumber);
                jObject.put("name", element.text());
                lines.add(jObject);
            }

            for (Element element : elementStations) {
                String lineNumber = element.attr("data-depend-set").replace("lines-", "");
                doc.getElementsByTag("span");
                String station = element.text();
                String temp = station.substring(station.indexOf(".") + 2).replaceAll("\\s[0-9]+\\.\\s", "  ");
                String[] stations = temp.split("\\s{2}");
                ArrayList<String> stationList = new ArrayList<>();
                Collections.addAll(stationList, stations);
                JSONObject stationsInLines = new JSONObject();
                stationsInLines.put(lineNumber, stationList);
                stationsArr.add(stationsInLines);
            }
            map.put("lines", lines);
            map.put("stations", stationsArr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
