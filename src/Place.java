public class Place {
    private String name; // Nom de la place
    private int marking; // Marquage de la place

    public Place(String name, int marking) {
        this.name = name;
        this.marking = marking;
    }

    public String getName() {
        return name; // Retourne le nom de la place
    }

    public int getMarking() {
        return marking; // Retourne le marquage de la place
    }

    // Setter pour l'attribut marking
    public void setMarking(int marking) {
        this.marking = marking; // Modifie le marquage de la place
    }
}
