package org.informatics.exceptions.printingMachineExceptions;

public class PrintingMachineHasNoPaperException extends Exception{

        public PrintingMachineHasNoPaperException() {
            super("The printing machine has no paper.");
        }
    }

