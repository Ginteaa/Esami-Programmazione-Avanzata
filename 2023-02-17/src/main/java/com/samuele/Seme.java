package com.samuele;

public enum Seme {
    CUORI,
    QUADRI,
    FIORI,
    PICCHE;

    @Override
    public String toString(){
        switch (this) {
            case CUORI:  return "C";
            case QUADRI: return "Q";
            case FIORI:  return "F";
            default:     return  "P";
        }
    }
}
