import java.util.ArrayList;
import java.util.List;

public class Transition {
    private String name;
    private List<String> inputPlaces;
    private List<String> outputPlaces;

    public Transition(String name) {
        this.name = name;
        inputPlaces = new ArrayList<>();
        outputPlaces = new ArrayList<>();
    }

    public void addInputPlace(String placeName) {
        inputPlaces.add(placeName);
    }

    public void addOutputPlace(String placeName) {
        outputPlaces.add(placeName);
    }

    public String getName() {
        return name;
    }

    public List<String> getInputPlaces() {
        return inputPlaces;
    }

    public List<String> getOutputPlaces() {
        return outputPlaces;
    }
}
