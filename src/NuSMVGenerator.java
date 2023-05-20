

public class NuSMVGenerator {
    public static String generateNuSMVCode(PetriNet petriNet) {
        StringBuilder sb = new StringBuilder();

        // Générer les déclarations des variables NuSMV
        sb.append("MODULE main\n");
        for (Place place : petriNet.getPlaces()) {
            sb.append("VAR ").append(place.getName()).append(": 0..").append(place.getMarking()).append(";\n");
        }

        // Générer les règles de transition
        for (Transition transition : petriNet.getTransitions()) {
            sb.append("TRANS ").append(transition.getName()).append(":\n");
            sb.append("    case\n");

            // Générer les conditions de tirage de la transition
            for (String inputPlace : transition.getInputPlaces()) {
                sb.append("        ").append(inputPlace).append(" = 1 : {").append(inputPlace).append(" := 0, ");
            }

            // Générer les marquages résultants de la transition
            for (String outputPlace : transition.getOutputPlaces()) {
                sb.append(outputPlace).append(" := 1, ");
            }

            sb.append("TRUE};\n");
        }

        // Générer la spécification de propriété à vérifier
        sb.append("SPEC TRUE\n");

        return sb.toString();
    }
}
