import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetriNet {
    private List<Place> places;
    private List<Transition> transitions;
    private Map<List<Integer>, List<List<Integer>>> markingGraph;

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
        for (Place place : places) {
            if (place.getName().equals(name)) {
                return place;
            }
        }
        return null;
    }

    public Transition getTransitionByName(String name) {
        for (Transition transition : transitions) {
            if (transition.getName().equals(name)) {
                return transition;
            }
        }
        return null;
    }

    public boolean isInputPlace(String placeName) {
        for (Transition transition : transitions) {
            if (transition.getInputPlaces().contains(placeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutputPlace(String placeName) {
        for (Transition transition : transitions) {
            if (transition.getOutputPlaces().contains(placeName)) {
                return true;
            }
        }
        return false;
    }

    public List<Place> getInputPlacesForTransition(Transition transition) {
        List<Place> inputPlaces = new ArrayList<>();
        for (String inputPlaceName : transition.getInputPlaces()) {
            Place inputPlace = getPlaceByName(inputPlaceName);
            if (inputPlace != null) {
                inputPlaces.add(inputPlace);
            }
        }
        return inputPlaces;
    }

    public List<Place> getOutputPlacesForTransition(Transition transition) {
        List<Place> outputPlaces = new ArrayList<>();
        for (String outputPlaceName : transition.getOutputPlaces()) {
            Place outputPlace = getPlaceByName(outputPlaceName);
            if (outputPlace != null) {
                outputPlaces.add(outputPlace);
            }
        }
        return outputPlaces;
    }

    public int calculateTotalMarking() {
        int totalMarking = 0;
        for (Place place : places) {
            totalMarking += place.getMarking();
        }
        return totalMarking;
    }

    public void printMarkingGraph() {
        markingGraph.clear();

        // Initial marking
        List<Integer> initialMarking = new ArrayList<>();
        for (Place place : places) {
            initialMarking.add(place.getMarking());
        }

        // Generate marking graph recursively
        generateMarkingGraph(initialMarking);

        // Print marking graph
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
            return; // Avoid revisiting already visited marking
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
        for (String inputPlace : transition.getInputPlaces()) {
            int placeIndex = getPlaceIndex(inputPlace);
            if (placeIndex != -1 && marking.get(placeIndex) <= 0) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> applyTransition(Transition transition, List<Integer> marking) {
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
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getName().equals(placeName)) {
                return i;
            }
        }
        return -1;
    }
}
