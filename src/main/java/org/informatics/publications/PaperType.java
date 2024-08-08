package org.informatics.publications;

public enum PaperType {
    Plain(1),
    Glazed(4),
    Newsprint(2);

    private int price;

    PaperType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
