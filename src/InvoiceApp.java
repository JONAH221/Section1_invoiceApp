import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an Invoice Calculator.
 *
 * @author <Jonah Zuehlke, Kathryn Wemhoff>
 * @version 2.0
 * @link https://github.com/JONAH221/Section1_invoiceApp
 */
public class InvoiceApp {


    /**
     * Scanner for all user input
     **/
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Standard double dash line for display output
     */
    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    /**
     * Standard single dash line for display output
     */
    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    /**
     * Prompt the user for a double value
     *
     * @param prompt Input label to display
     * @return User input as a double
     */
    private static double getDouble(String prompt) {
        String userInput = "?";
        double validInput = 0.0;

        // keep looping until valid input
        while (true) {
            try {
                System.out.print(prompt);
                userInput = InvoiceApp.sc.nextLine();
                validInput = Double.parseDouble(userInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again...");
            }
        } // end of validation while loop

        return validInput;

    } // end of getDouble method

    /**
     * Prompt the user for a double value
     *
     * @param prompt Input label to display
     * @return User input as a double
     */
    private static int getInteger(String prompt) {
        String userInput = "?";
        int validInput = 0;

        // keep looping until valid input
        while (true) {
            try {
                System.out.print(prompt);
                userInput = InvoiceApp.sc.nextLine();
                validInput = Integer.parseInt(userInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again...");
            }
        } // end of validation while loop

        return validInput;

    } // end of getInteger method

    /**
     * Prompt the user for a string value
     *
     * @param prompt Input label to display
     * @return User input as a string
     */
    private static String getString(String prompt) {
        String userInput = "?";

        // keep looping until valid input
        while (true) {
            System.out.print(prompt);
            userInput = InvoiceApp.sc.nextLine();
            if (!userInput.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input. Please try again...");
            }
        } // end of validation while loop

        return userInput;
    } // end of getString method

    /**
     * Get user's input for y/n question
     *
     * @param prompt Y/N Prompt
     * @return true for y and false for n
     */
    private static boolean getYesNo(String prompt) {
        String userInput = "?";
        boolean ynAnswer = false;

        // keep looping until y or n input
        while (true) {
            System.out.print(prompt);
            userInput = InvoiceApp.sc.nextLine().toLowerCase();

            if (userInput.equals("n")) {
                break;
            } else if (userInput.equals("y")) {
                ynAnswer = true;
                break;
            } else {
                System.out.println("Invalid input. Please try again...");
            }
        } // end y/n validation

        return ynAnswer; // true = yes | false = no

    } // end of getYesNo method


    /**
     * Print the user's receipt
     * @param descriptionList All descriptions for items purchased
     * @param qtyList All qualities for items purchased
     * @param priceList All prices for items purchased
     */

            
    /**
     * New doubles (subTotal, disCountPercent)
     * new method named getDiscountPercent
     * wrote inside the method an if else statement to determine the discount
     * based on the subTotal of the users item's
     */

    private static double subTotal;
    private static double disCountPercent;

    private static double getDiscountPercent(double subTotal) {
        if (subTotal >= 50.0) {
            disCountPercent = 0.25;
        } else if (subTotal >= 40.0) {
            disCountPercent = 0.20;
        } else if (subTotal >= 30) {
            disCountPercent = 0.15;
        } else if (subTotal >= 20) {
            disCountPercent = 0.10;
        } else if (subTotal >= 10) {
            disCountPercent = 0.05;
        } else {
            disCountPercent = 0.0;
        }
        return disCountPercent;
    }

    private static void printReceipt(List<String> descriptionList, List<Integer> qtyList, List<Double> priceList) {
        String description = "?";
        int qty = 0;
        double price = 0.0;

        double subTotal = 0.0;
        double receiptTotal = 0.0;
        /**
         * Added double variables discountPercent, discountAmount, savingsTotal
         */

        double discountPercent = 0.0;
        double discountAmount = 0.0;
        double savingsTotal = 0.0;

        System.out.println(InvoiceApp.DOUBLE_DASH_LINE);
        System.out.println("Customer Receipt");
        System.out.print(InvoiceApp.DOUBLE_DASH_LINE);

        for (int i = 0; i < descriptionList.size(); i++) {

            description = descriptionList.get(i);
            qty = qtyList.get(i);
            price = priceList.get(i);

            subTotal = qty * price;

            System.out.println();
            System.out.printf("%-20s  %3d  @  $%,6.2f = $%,8.2f\n", description, qty, price, subTotal);

            discountPercent = InvoiceApp.getDiscountPercent(subTotal);
            if (discountPercent > 0.0) {
                discountAmount = subTotal - discountPercent;
                System.out.printf("%20s %2.0f%% -$%,6.2f = $%,8.2f\n", "Discount", (discountPercent * 100), discountAmount, savingsTotal);
            }

            receiptTotal += subTotal;

        } // end of for loop

        System.out.println(InvoiceApp.SINGLE_DASH_LINE);

        if (discountPercent > 0.0) { //begin if statement
            discountAmount = subTotal * discountPercent;
            System.out.printf("Discount (%.1f%%): $%.2f\n", discountPercent * 100, discountAmount);
            savingsTotal += discountAmount;
        } // end of if statement

        if (receiptTotal > 0.0) { //begin if statement
            System.out.printf("Savings Total: $%,8.2f\n", savingsTotal);
        } //end of if statement

        System.out.printf("Receipt Total: $%,8.2f\n", receiptTotal);
        System.out.println(InvoiceApp.SINGLE_DASH_LINE);

    } // end of printReceipt method

    /**
     * Prompt the user for the item description, quantity, and price. Keep looping until the user is done.
     * @param args No runtime arguments are used for this program.
     */
    public static void main(String[] args) {

        List<String> descriptionList = new ArrayList<String>();
        List<Integer> qtyList = new ArrayList<Integer>();
        List<Double> priceList = new ArrayList<Double>();

        String description = "?";
        int qty = 0;
        double price = 0.0;

        boolean keepLooping = true;

        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Invoice Total Calculator");
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println();

        while (keepLooping) {

            description = InvoiceApp.getString("Item Description: ");
            qty         = InvoiceApp.getInteger("Item Quantity...: ");
            price       = InvoiceApp.getDouble("Item Price......: ");

            descriptionList.add(description);
            qtyList.add(qty);
            priceList.add(price);

            System.out.println();
            System.out.println(InvoiceApp.SINGLE_DASH_LINE);

            keepLooping = InvoiceApp.getYesNo("Do you want to enter another line item (y/n): ");

            System.out.println(InvoiceApp.SINGLE_DASH_LINE);
            System.out.println();

        } // end keepLooping while loop

        InvoiceApp.printReceipt(descriptionList, qtyList, priceList);

        System.out.println("Thank you for shopping with us!");

    } // end of main method

} // end of InvoiceApp