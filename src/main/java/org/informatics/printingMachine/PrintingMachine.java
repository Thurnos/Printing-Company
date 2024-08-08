package org.informatics.printingMachine;
import org.informatics.exceptions.printingMachineExceptions.NegativeCurrentNumPapersException;
import org.informatics.exceptions.printingMachineExceptions.PrintingMachineException;
import org.informatics.exceptions.printingMachineExceptions.PrintingMachineHasNoPaperException;
import org.informatics.publications.PaperType;
import org.informatics.publications.Publication;
import org.informatics.PrintingShop;
import java.util.function.Consumer;
import org.informatics.publications.PaperType;

public class PrintingMachine {
    private PaperColor paperColor;
    private final int maxNumPapers;
    private int currentNumPapers;
    private PaperType paperType;

    private int PPM;

    public PrintingMachine(int maxNumPapers, PaperType paperType , PaperColor paperColor) {
        this.maxNumPapers = maxNumPapers;
        this.paperType = paperType;
        this.paperColor = paperColor;
        this.currentNumPapers = 0;
    }

    public void setCurrentNumPapers (int currentNumPapers){
            if (currentNumPapers < 0) {
                throw new NegativeCurrentNumPapersException("Printer must not be left with no paper.");
            }
            this.currentNumPapers = currentNumPapers;
    }

    public static void loadPaper(PrintingMachine printingMachine) {
        try {
            if (printingMachine.getCurrentNumPapers() < printingMachine.getMaxNumPapers()) {
                int numPapersToAdd = printingMachine.getMaxNumPapers() - printingMachine.getCurrentNumPapers();
                printingMachine.setCurrentNumPapers(printingMachine.getCurrentNumPapers() + numPapersToAdd);
            } else {
                printingMachine.setCurrentNumPapers(printingMachine.getMaxNumPapers());
            }
        } catch (NegativeCurrentNumPapersException e) {

        }
    }
    public boolean isLoaded(PrintingMachine printingMachine){
      if(printingMachine.getCurrentNumPapers() == printingMachine.getMaxNumPapers()){
          return true;
      }
        try{
            throw new PrintingMachineHasNoPaperException();
        }catch (Exception pmhnpe){
            System.out.println("The max number of papers to load the printingmachine is" + " " +(printingMachine.getMaxNumPapers()));
            throw new RuntimeException(pmhnpe);

            }
            //return false;
        }
    public void pagesPerMinute(PrintingMachine printingMachine, PaperColor paperColor) {
        if (isLoaded(printingMachine)) {
            try {
                int ppm = calculatePagesPerMinute(paperColor);
                printingMachine.setPPM(ppm);
                System.out.println(printingMachine.getPPM());
            } catch (IllegalArgumentException e) {
                handleException(e);
            }
        }
    }
    public static int calculatePagesPerMinute(PaperColor paperColor) {
        return paperColor == PaperColor.BLACK_AND_WHITE ? 35 : 4;
    }
    public static <T extends Exception> void handleException(T exception) {
        Consumer<T> printErrorMessage = ex -> System.out.println("Error: " + ex.getMessage());
        printErrorMessage.accept(exception);
    }
    public void printPublication(Publication publication, int copies) throws PrintingMachineException {
        if (currentNumPapers == 0) {
            throw new PrintingMachineException(maxNumPapers);
        }

        int pageCount = publication.getPages();
        int requiredPaperCount = copies * pageCount;
        if (requiredPaperCount > currentNumPapers) {
            throw new PrintingMachineException(currentNumPapers);
        }

        System.out.println("Printing " + copies + " copies of publication: " + publication.getName());
        System.out.println("Page Count: " + pageCount);
        System.out.println("Paper Type: " + publication.getPaperType());
        System.out.println("Colored: " + PaperColor.values());
        System.out.println("Total Pages Printed: " + requiredPaperCount);

        currentNumPapers -= requiredPaperCount;
    }

    public int getCurrentNumPapers() {
        return currentNumPapers;
    }

    public int getMaxNumPapers() {
        return maxNumPapers;
    }
    public void setPPM(int PPM) {
        this.PPM = PPM;
    }
    public int getPPM(){
         return this.PPM;
    }

}
