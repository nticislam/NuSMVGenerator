import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetriNet {
    private List<Place> places;  // Liste des places du réseau de Petri
    private List<Transition> transitions;  // Liste des transitions du réseau de Petri
    private Map<List<Integer>, List<List<Integer>>> markingGraph;  // Graphe de marquage du réseau de Petri

    public PetriNet() {
        places = new ArrayList<>();
        transitions = new ArrayList<>();
        markingGraph = new HashMap<>();
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public List<Place> getPlaces() {
        return places;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public Place getPlaceByName(String name) {
        // Obtient une place à partir de son nom
        for (Place place : places) {
            if (place.getName().equals(name)) {
                return place;
            }
        }
        return null;
    }

    public Transition getTransitionByName(String name) {
        // Obtient une transition à partir de son nom
        for (Transition transition : transitions) {
            if (transition.getName().equals(name)) {
                return transition;
            }
        }
        return null;
    }

    public boolean isInputPlace(String placeName) {
        // Vérifie si une place est une place d'entrée
        for (Transition transition : transitions) {
            if (transition.getInputPlaces().contains(placeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutputPlace(String placeName) {
        // Vérifie si une place est une place de sortie
        for (Transition transition : transitions) {
            if (transition.getOutputPlaces().contains(placeName)) {
                return true;
            }
        }
        return false;
    }

    public int calculateTotalMarking() {
        // Calcule le marquage total en additionnant les marquages de toutes les places
        int totalMarking = 0;
        for (Place place : places) {
            totalMarking += place.getMarking();
        }
        return totalMarking;
    }

    public void printMarkingGraph() {
        markingGraph.clear();

        // Marquage initial
        List<Integer> initialMarking = new ArrayList<>();
        for (Place place : places) {
            initialMarking.add(place.getMarking());
        }

        // Génère le graphe de marquage de manière récursive
        generateMarkingGraph(initialMarking);

        // Affiche le graphe de marquage
        System.out.println("Marking Graph:");
        for (Map.Entry<List<Integer>, List<List<Integer>>> entry : markingGraph.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (List<Integer> marking : entry.getValue()) {
                System.out.print(marking + " ");
            }
            System.out.println();
        }
    }

    private void generateMarkingGraph(List<Integer> marking) {
        if (markingGraph.containsKey(marking)) {
            return; // Évite de revisiter un marquage déjà visité
        }

        markingGraph.put(marking, new ArrayList<>());

        for (Transition transition : transitions) {
            if (isTransitionEnabled(transition, marking)) {
                List<Integer> newMarking = applyTransition(transition, marking);
                markingGraph.get(marking).add(newMarking);
                generateMarkingGraph(newMarking);
            }
        }
    }

    private boolean isTransitionEnabled(Transition transition, List<Integer> marking) {
        // Vérifie si une transition est activée pour un marquage donné
        for (String inputPlace : transition.getInputPlaces()) {
            int placeIndex = getPlaceIndex(inputPlace);
            if (placeIndex != -1 && marking.get(placeIndex) <= 0) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> applyTransition(Transition transition, List<Integer> marking) {
        // Applique une transition à un marquage donné et retourne le nouveau marquage
        List<Integer> newMarking = new ArrayList<>(marking);

        for (String inputPlace : transition.getInputPlaces()) {
            int placeIndex = getPlaceIndex(inputPlace);
            if (placeIndex != -1) {
                newMarking.set(placeIndex, newMarking.get(placeIndex) - 1);
            }
        }

        for (String outputPlace : transition.getOutputPlaces()) {
            int placeIndex = getPlaceIndex(outputPlace);
            if (placeIndex != -1) {
                newMarking.set(placeIndex, newMarking.get(placeIndex) + 1);
            }
        }

        return newMarking;
    }

    private int getPlaceIndex(String placeName) {
        // Obtient l'indice d'une place à partir de son nom
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getName().equals(placeName)) {
                return i;
            }
        }
        return -1;
    }
}
