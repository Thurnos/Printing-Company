package org.informatics;
import org.informatics.employees.Employee;
import org.informatics.employees.EmployeeType;
import org.informatics.exceptions.printingMachineExceptions.PrintingMachineException;
import org.informatics.publications.Publication;
import org.informatics.printingMachine.PrintingMachine;
import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class PrintingShop{
        private List<Publication> publications;
        private List<Employee> employees;
        private double baseSalary;
        private double revenueThreshold;
        private double managerBonusPercentage;
        private double discountPercentage;

        public PrintingShop(double baseSalary, double revenueThreshold, double managerBonusPercentage, double discountPercentage) {
            this.baseSalary = baseSalary;
            this.revenueThreshold = revenueThreshold;
            this.managerBonusPercentage = managerBonusPercentage;
            this.discountPercentage = discountPercentage;
            this.publications = new ArrayList<>();
            this.employees = new ArrayList<>();
        }

        public void addPublication(Publication publication) {
            publications.add(publication);
        }


        public double calculateSalaryExpenses() {
            return employees.size() * baseSalary;
        }

    public double calculatePaperExpenses(double paperPrice) {
        return publications.stream()
                .mapToDouble(publication -> publication.getPages())
                .sum() * paperPrice;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.equals(employee)) {
                iterator.remove();
                break;
            }
        }
    }
    public double getTotalRevenue(double pricePerPublication, int totalPrintedPublications) {
        double revenue = calculateRevenue(pricePerPublication, totalPrintedPublications);
        double managerBonus = calculateManagerBonus(revenue);
        return revenue - managerBonus;
    }

    public double calculateManagerSalary(double totalRevenue) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeType() == EmployeeType.MANAGER)
                .mapToDouble(employee -> {
                    double salary = baseSalary;
                    if (totalRevenue > revenueThreshold) {
                        double bonus = baseSalary * managerBonusPercentage;
                        salary += bonus;
                    }
                    return salary;
                })
                .sum();
    }

    public double calculateRevenue(double pricePerPublication, int totalPrintedPublications) {
        double revenue = pricePerPublication * totalPrintedPublications;
        if (totalPrintedPublications > revenueThreshold) {
            double discountAmount = (pricePerPublication * discountPercentage) / 100.0;
            revenue -= (discountAmount * totalPrintedPublications);
        }
        return revenue;
    }

    public double calculateManagerBonus(double revenue) {
        return (revenue > revenueThreshold) ? (managerBonusPercentage / 100.0) * baseSalary : 0;
    }

        public void printReportToFile(String filename, double paperPrice, double pricePerPublication, int totalPrintedPublications, double revenue) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("Printing Shop Report");
                writer.println("---------------------");
                writer.println("Publications:");
                for (Publication publication : publications) {
                    writer.println("Title: " + publication.getName());
                    writer.println("Page Count: " + publication.getPages());
                    writer.println("Paper Type: " + publication.getPaperType());
                    writer.println();
                }
                writer.println("Employees: " + employees.size());
                writer.println("Base Salary: " + baseSalary);
                writer.println("Salary Expenses: " + calculateSalaryExpenses());
                writer.println();
                writer.println("Paper Price: " + paperPrice);
                writer.println("Total Paper Count: " + calculateTotalPaperCount());
                writer.println("Paper Expenses: " + calculatePaperExpenses(paperPrice));
                writer.println();
                writer.println("Price per Publication: " + pricePerPublication);
                writer.println("Total Printed Publications: " + totalPrintedPublications);
                writer.println("Revenue: " + revenue);
                writer.println("Manager Bonus: " + calculateManagerBonus(revenue));
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }

    public void readReportFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private int calculateTotalPaperCount() {
        return publications.stream()
                .mapToInt(publication -> publication.getPages())
                .sum();
    }
    public void loadPaper(PrintingMachine machine, int paperCount) throws PrintingMachineException {
        if (paperCount > machine.getMaxNumPapers()) {
            throw new PrintingMachineException(machine.getMaxNumPapers());
        }
        machine.setCurrentNumPapers(paperCount);
    }


}
