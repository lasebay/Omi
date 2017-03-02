package a404_notfound.sourceappwater.model;

import java.util.List;

/**
 * Created by Michelle on 2/27/2017.
 */

public enum WaterCondition {
    WASTE("Waste"),
    CLEAR("Treatable-Clear"),
    MUDDY("Treatable-Muddy"),
    POTABLE("Potable");

    private final String rep;

    // Constructor
    WaterCondition(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public String toString() {
        return rep;
    }

}