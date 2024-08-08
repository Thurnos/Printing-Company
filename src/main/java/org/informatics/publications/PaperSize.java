package org.informatics.publications;

public enum PaperSize {
    A1(0.20),
    A2(0.15),
    A3(0.10),
    A4(0.05),
    A5(0.03);

    private final double price;
    PaperSize(double price){
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
