package org.informatics.publications;

public class Paper {
    PaperType paperType;
    PaperSize paperSize;


    public double price(PaperType paperType , PaperSize papersize){
        return paperType.getPrice() * papersize.getPrice();
    }
    public Paper(PaperType paperType, PaperSize paperSize) {
        this.paperType = paperType;
        this.paperSize = paperSize;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperType=" + paperType +
                ", paperSize=" + paperSize +
                '}';
    }
}
