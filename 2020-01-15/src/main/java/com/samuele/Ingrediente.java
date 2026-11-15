package com.samuele;

public enum Ingrediente {
    LATTE,
    CAFFE,
    NIENTE;

    public Ingrediente altro() {
        switch (this) {
            case LATTE: return CAFFE;
            case CAFFE: return LATTE;
            default:    return NIENTE;
        }
    }
}
