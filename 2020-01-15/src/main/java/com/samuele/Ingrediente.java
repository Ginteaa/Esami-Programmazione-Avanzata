package com.samuele;

public enum Ingrediente {
    LATTE,
    CAFFE,
    NIENTE;

    @Override
    public String toString() {
        switch (this) {
            case CAFFE:
                return "CAFFE";
            case LATTE:
                return "LATTE";
            default:
                return super.toString();
        }
    }



}