public class Printer {

    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        this.tonerLevel = (tonerLevel >= 0 && tonerLevel <= 100) ? tonerLevel : -1;
        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    // ✅ Add toner method with validation
    public int addToner(int tonerAmount) {
        if (tonerAmount <= 0 || tonerAmount > 100) {
            System.out.println("Invalid toner amount. Must be between 1 and 100.");
            return -1;
        }

        if (this.tonerLevel + tonerAmount > 100) {
            System.out.println("Toner overflow! Cannot add more toner.");
            return -1;
        }

        this.tonerLevel += tonerAmount;
        System.out.println("Toner added. Current level: " + this.tonerLevel + "%");
        return this.tonerLevel;
    }

    // ✅ Print pages method with duplex logic
    public int printPages(int pages) {
        if (pages <= 0) {
            System.out.println("Invalid number of pages. Must be greater than 0.");
            return 0;
        }

        int pagesToPrint = pages;

        if (this.duplex) {
            pagesToPrint = (int) Math.ceil(pages / 2.0);  // Duplex mode calculation
            System.out.println("Printing in duplex mode...");
        } else {
            System.out.println("Printing in single-sided mode...");
        }

        this.pagesPrinted += pagesToPrint;
        System.out.println("Pages printed in this session: " + pagesToPrint);
        System.out.println("Total pages printed: " + this.pagesPrinted);
        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    // ✅ toString() method for easier status display
    @Override
    public String toString() {
        return String.format(
                "Printer [Toner Level: %d%%, Pages Printed: %d, Duplex: %b]",
                tonerLevel, pagesPrinted, duplex
        );
    }

    // ✅ Main method for testing
    public static void main(String[] args) {
        // Create a duplex printer with initial toner level of 50%
        Printer printer = new Printer(50, true);
        
        // Display initial printer status
        System.out.println("\n== Printer Status ==");
        System.out.println(printer);

        // Add toner
        System.out.println("\n== Adding Toner ==");
        printer.addToner(30);
        printer.addToner(25);  // This should fail (overflow)

        // Print pages in duplex mode
        System.out.println("\n== Printing Pages ==");
        printer.printPages(5);  // Should print 3 pages in duplex mode
        printer.printPages(10);  // Should print 5 pages in duplex mode

        // Display final printer status
        System.out.println("\n== Final Printer Status ==");
        System.out.println(printer);
    }
}
