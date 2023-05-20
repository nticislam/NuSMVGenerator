public class NuSMVGenerator {
    public static String generateNuSMVCode(PetriNet petriNet) {
        StringBuilder sb = new StringBuilder();

        sb.append("MODULE main\n");
        sb.append("VAR\n");
        sb.append("s: {");
        for (int i = 0; i < petriNet.getTransitions().size(); i++) {
            sb.append("s").append(i);
            if (i < petriNet.getTransitions().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("};\n");

        for (Place place : petriNet.getPlaces()) {
            sb.append(place.getName()).append(": 0 .. ").append(place.getMarking()).append(";\n");
        }

        sb.append("ASSIGN\n");
        sb.append("init(s) := s0;\n");

        sb.append("next(s) := case\n");
        for (int i = 0; i < petriNet.getTransitions().size(); i++) {
            Transition transition = petriNet.getTransitions().get(i);
            sb.append("  s = s").append(i).append(": s").append((i + 1) % petriNet.getTransitions().size()).append(";\n");
        }
        sb.append("esac;\n");

        for (Place place : petriNet.getPlaces()) {
            sb.append(place.getName()).append(" := case\n");
            for (int i = 0; i < petriNet.getTransitions().size(); i++) {
                Transition transition = petriNet.getTransitions().get(i);
                if (transition.getOutputPlaces().contains(place.getName())) {
                    sb.append("  s = s").append(i).append(": ").append(place.getMarking()).append(";\n");
                }
            }
            sb.append("  TRUE: 0;\n");
            sb.append("esac;\n");
        }

        return sb.toString();
    }
}
