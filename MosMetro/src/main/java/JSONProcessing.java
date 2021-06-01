import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONProcessing {
    private static String dataFile = "data/map.json";

    public static void writeJSONFile(MetroMap metroMap) {
        try {
            JSONObject jsonObject = metroMap.getMap();
            FileWriter file = new FileWriter(dataFile);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJSONFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static ArrayList<Line> parseLinesToList(JSONArray lineArray) {
        ArrayList lines = new ArrayList();
        for (Object lineObject : lineArray) {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line((String) lineJsonObject.get("number"), (String) lineJsonObject.get("name"));
            lines.add(line);
        }
        return lines;
    }

    public static HashMap<String, ArrayList<Station>> parseStationsToMap(JSONArray stationsArray) {
        HashMap<String, ArrayList<Station>> stationMap = new HashMap();
        for (Object stationObject : stationsArray) {
            JSONObject stationJsonObject = (JSONObject) stationObject;
            for (Object key : stationJsonObject.keySet()) {
                String lineNumber = (String) key;
                JSONArray stationArray = (JSONArray) stationJsonObject.get(key);
                ArrayList stations = new ArrayList();
                for (Object stationsObject : stationArray) {
                    Station station = new Station((String) stationsObject);
                    stations.add(station);
                }
                stationMap.put(lineNumber, stations);
            }
        }
        return stationMap;
    }
}
