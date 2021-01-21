package org.hcl;

public class DrinkDTO {

    Long id;
    String name;
    boolean carbonated;

    public DrinkDTO(Long id, String name, boolean bubbles) {
        this.id = id;
        this.name = name;
        this.carbonated = bubbles;
    }

    public DrinkDTO(String name, boolean bubbles) {
        this.name = name;
        this.carbonated = bubbles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCarbonated() {
        return carbonated;
    }

    public void setGood(boolean bubbles) {
        this.carbonated = bubbles;
    }

    public static int compareByName(DrinkDTO a, DrinkDTO b) {
        return a.name.compareTo(b.name);
    }

    @Override
    public String toString() {
        return "DrinkDTO{" +
                "id='" + id + "'," +
                "name='" + name + "'," +
                "carbonated=" + carbonated +
                "}";
    }

}
