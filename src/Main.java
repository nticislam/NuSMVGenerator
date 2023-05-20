import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création du réseau de Petri
        PetriNet petriNet = new PetriNet();

        // Demande à l'utilisateur de saisir les places et leurs marquages
        System.out.print("Combien de places voulez-vous ajouter ? ");
        int numPlaces = scanner.nextInt();
        scanner.nextLine(); // Vider la ligne

        for (int i = 0; i < numPlaces; i++) {
            System.out.print("Nom de la place " + (i + 1) + ": ");
            String placeName = scanner.nextLine();

            System.out.print("Marquage initial de la place " + (i + 1) + ": ");
            int marking = scanner.nextInt();
            scanner.nextLine(); // Vider la ligne

            Place place = new Place(placeName, marking);
            petriNet.addPlace(place);
        }

        // Demande à l'utilisateur de saisir les transitions et leurs places d'entrée/sortie
        System.out.print("Combien de transitions voulez-vous ajouter ? ");
        int numTransitions = scanner.nextInt();
        scanner.nextLine(); // Vider la ligne

        for (int i = 0; i < numTransitions; i++) {
            System.out.print("Nom de la transition " + (i + 1) + ": ");
            String transitionName = scanner.nextLine();

            System.out.print("Combien de places d'entrée pour la transition " + (i + 1) + "? ");
            int numInputPlaces = scanner.nextInt();
            scanner.nextLine(); // Vider la ligne

            List<String> inputPlaces = new ArrayList<>();
            for (int j = 0; j < numInputPlaces; j++) {
                System.out.print("Nom de la place d'entrée " + (j + 1) + " pour la transition " + (i + 1) + ": ");
                String inputPlace = scanner.nextLine();
                inputPlaces.add(inputPlace);
            }

            System.out.print("Combien de places de sortie pour la transition " + (i + 1) + "? ");
            int numOutputPlaces = scanner.nextInt();
            scanner.nextLine(); // Vider la ligne

            List<String> outputPlaces = new ArrayList<>();
            for (int j = 0; j < numOutputPlaces; j++) {
                System.out.print("Nom de la place de sortie " + (j + 1) + " pour la transition " + (i + 1) + ": ");
                String outputPlace = scanner.nextLine();
                outputPlaces.add(outputPlace);
            }

            Transition transition = new Transition(transitionName);
            transition.setInputPlaces(inputPlaces);
            transition.setOutputPlaces(outputPlaces);
            petriNet.addTransition(transition);
        }

        // Génération du code NuSMV équivalent
        String nusmvCode = NuSMVGenerator.generateNuSMVCode(petriNet);

        // Affichage du code NuSMV
        System.out.println(nusmvCode);
    }
}
