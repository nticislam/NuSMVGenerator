import java.util.ArrayList;
import java.util.List;

class Transition {
    private String name;
    private List<String> inputPlaces;
    private List<String> outputPlaces;

    public Transition(String name) {
        this.name = name;
        inputPlaces = new ArrayList<>();
        outputPlaces = new ArrayList<>();
    }

    // Définition des places d'entrée pour la transition
    public void setInputPlaces(List<String> inputPlaces) {
        this.inputPlaces = inputPlaces;
    }

    // Ajout d'une place d'entrée à la liste des places d'entrée
    public void addInputPlace(String placeName) {
        inputPlaces.add(placeName);
    }

    // Ajout d'une place de sortie à la liste des places de sortie
    public void addOutputPlace(String placeName) {
        outputPlaces.add(placeName);
    }

    // Récupération du nom de la transition
    public String getName() {
        return name;
    }

    // Récupération de la liste des places d'entrée
    public List<String> getInputPlaces() {
        return inputPlaces;
    }

    // Récupération de la liste des places de sortie
    public List<String> getOutputPlaces() {
        return outputPlaces;
    }

    // Définition des places de sortie pour la transition
    public void setOutputPlaces(List<String> outputPlaces) {
        this.outputPlaces = outputPlaces;
    }
}
