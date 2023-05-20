public class Main {
    public static void main(String[] args) {
        // Création du réseau de Petri
        PetriNet petriNet = new PetriNet();

        // Ajout des places
        Place p1 = new Place("P1", 1);
        Place p2 = new Place("P2", 0);
        Place p3 = new Place("P3", 0);
        petriNet.addPlace(p1);
        petriNet.addPlace(p2);
        petriNet.addPlace(p3);

        // Ajout des transitions
        Transition t1 = new Transition("T1");
        t1.addInputPlace("P1");
        t1.addOutputPlace("P2");

        Transition t2 = new Transition("T2");
        t2.addInputPlace("P2");
        t2.addOutputPlace("P3");

        petriNet.addTransition(t1);
        petriNet.addTransition(t2);

        // Génération du code NuSMV équivalent
        String nusmvCode = NuSMVGenerator.generateNuSMVCode(petriNet);

        // Affichage du code NuSMV
        System.out.println(nusmvCode);
    }
}
