package org.informatics.exceptions.printingMachineExceptions;


public class PrintingMachineException extends Exception {
    private int maxPaperCount;

    public PrintingMachineException(int maxPaperCount) {
        this.maxPaperCount = maxPaperCount;
    }

    public int getMaxPaperCount() {
        return maxPaperCount;
    }
}