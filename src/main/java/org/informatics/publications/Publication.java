package org.informatics.publications;

public class Publication {
    private String name;
    private int pages;
    private PaperSize paperSize;
    private PaperType paperType;

    public Publication(String name, int pages, PaperSize paperSize,PaperType paperType) {
        this.name = name;
        this.pages = pages;
        this.paperSize = paperSize;
        this.paperType = paperType;

    }


    public String getName() {
        return name;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                ", paperSize=" + paperSize +
                '}';
    }

}
