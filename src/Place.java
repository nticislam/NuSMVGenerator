public class Place {
    private String name;
    private int marking;

    public Place(String name, int marking) {
        this.name = name;
        this.marking = marking;
    }

    public String getName() {
        return name;
    }

    public int getMarking() {
        return marking;
    }

    // Setter pour l'attribut marking
    public void setMarking(int marking) {
        this.marking = marking;
    }
}
