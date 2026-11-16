package com.samuele;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ImageProcessor ip = new ImageProcessor("img.txt");
        ip.process(4);
    }
}
