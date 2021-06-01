import java.util.ArrayList;
import java.util.List;

public class Line {
    private String number;
    private String name;
    private List<Station> stations;

    public Line(String number, String name) {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void addStations(List<Station> stationsLine) {
        stations.addAll(stationsLine);
    }

    public List<Station> getStations() {
        return stations;
    }
}
