package emsi.ssii.devoir.models;

public enum Urgence {
    CRITICAL("CRITICAL"),
    URGENT("URGENT"), 
    MOYEN("MOYEN"), 
    NON_URGENT("NON_URGENT");

    private String label;

    Urgence(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
