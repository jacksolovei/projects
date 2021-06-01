import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MetroMap metroMap = new MetroMap();
        JSONProcessing.writeJSONFile(metroMap);

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(JSONProcessing.getJSONFile());
            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            ArrayList<Line> parsedLines = JSONProcessing.parseLinesToList(linesArray);
            JSONArray stationsJsonArray = (JSONArray) jsonData.get("stations");
            HashMap<String, ArrayList<Station>> stationMap = JSONProcessing.parseStationsToMap(stationsJsonArray);
            for (Line line : parsedLines) {
                if (stationMap.containsKey(line.getNumber())) {
                    line.addStations(stationMap.get(line.getNumber()));
                    System.out.println(line.getNumber() + " " + line.getName() + " - Количество станций : " + line.getStations().size());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
