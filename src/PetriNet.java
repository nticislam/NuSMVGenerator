import java.util.ArrayList;
import java.util.List;

public class PetriNet {
    private List<Place> places;
    private List<Transition> transitions;

    public PetriNet() {
        places = new ArrayList<>();
        transitions = new ArrayList<>();
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
}
