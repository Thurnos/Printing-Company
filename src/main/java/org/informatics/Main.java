package org.informatics;

import org.informatics.employees.Employee;
import org.informatics.employees.EmployeeType;
import org.informatics.exceptions.printingMachineExceptions.PrintingMachineException;
import org.informatics.exceptions.printingMachineExceptions.PrintingMachineHasNoPaperException;
import org.informatics.printingMachine.PaperColor;
import org.informatics.printingMachine.PrintingMachine;
import org.informatics.publications.PaperSize;
import org.informatics.publications.PaperType;
import org.informatics.publications.Publication;
import org.informatics.PrintingShop;

import javax.swing.plaf.synth.ColorType;
import javax.swing.text.PlainDocument;

public class Main {
    public static void main(String[] args) {
        PrintingShop printingShop = new PrintingShop(2000, 10000, 5, 10);

        // Add publications
        Publication publication1 = new Publication("Book 1", 100, PaperSize.A4, PaperType.Plain);
        Publication publication2 = new Publication("Book 2", 150, PaperSize.A3, PaperType.Newsprint);
        printingShop.addPublication(publication1);
        printingShop.addPublication(publication2);

        // Add employees
        Employee employee1 = new Employee(EmployeeType.PRINTING_PRESS_OPERATOR, 2000);
        Employee employee2 = new Employee(EmployeeType.MANAGER, 3000);
        printingShop.addEmployee(employee1);
        printingShop.addEmployee(employee2);

        // Load paper into the printing machine
        PrintingMachine printingMachine = new PrintingMachine(500, PaperType.Plain, PaperColor.BLACK_AND_WHITE);
        try {
            printingShop.loadPaper(printingMachine, 200);
        } catch (PrintingMachineException e) {
            System.out.println("Printing machine exception: " + e.getMaxPaperCount());
        }

        // Print a publication
        try {
            Publication publication = new Publication("Book 3", 120, PaperSize.A5, PaperType.Glazed);
            printingMachine.printPublication(publication, 100);
        } catch (PrintingMachineException e) {
            System.out.println("Printing machine exception: " + e.getMaxPaperCount());
        }

        // Calculate and print the total revenue
        double pricePerPublication = 10;
        int totalPrintedPublications = 500;
        double totalRevenue = printingShop.getTotalRevenue(pricePerPublication, totalPrintedPublications);
        System.out.println("Total Revenue: " + totalRevenue);

        // Print the shop report to a file
        String filename = "shop_report.txt";
        double paperPrice = 0.05;
        printingShop.printReportToFile(filename, paperPrice, pricePerPublication, totalPrintedPublications, totalRevenue);

        // Read and print the shop report from the file
        printingShop.readReportFromFile(filename);
    }

    }
