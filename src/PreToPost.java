/*
 * PreToPost
 *
 * DJJazzyBrett
 * 
 * PTP-proj2
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;

import java.util.NoSuchElementException;

/**
 * <h1>PreToPost</h1>
 *
 * This is a recursive implementation of program to convert mathematical
 * expressions from prefix notation to postfix notation. The
 * PreToPost class is designed to accept single character operands;
 * that is, a single letter [A-Z]. However, the interactive
 * component of the program allows the user to designate other
 * non-letter [single] characters to represent operands in certain
 * instances.
 *
 * @author PTP-proj2
 * @version 2.0 23 March 2021
 * @since 1.0
 */
public class PreToPost {

    /**
     * Helper method to facilitate identifying mathematical type
     * of each input character
     *
     * @param op Character that will be evaluated as an operand,
     *           operator, end-of-line character, or other
     * @return Operand, operator, EOL character, or null
     */
    private static String isOp(char op) {
        if (op == '+' || op == '-' || op == '*' || op == '/' || op == '$') {
            return "operator";
        } else if (Character.isLetter(op)) {
            return "operand";
        } else if (op == '\n' || op == '\r') {
            return "eol";
        } else {
            return null;
        } // END if
    } // END isOp


    /**
     * Let the games begin!
     *
     * @param args[] Provide input and output filenames from command line
     *               Input file must already be in existence
     *               Output file need not already be in existence
     *
     */
    public static void main(String[] args) throws IOException {

        String runIn = "KB: "
            + (double)
            (Runtime.getRuntime().totalMemory()
             - Runtime.getRuntime().freeMemory()) / 1024;

        BufferedReader br;
        BufferedWriter bw;

        if (args.length != 2) {
            System.out.println("Insufficient args!"
                               + "\n"
                               + "Please use: java PreToPost input output");
            System.exit(0);
        }

        try {
            br = new BufferedReader(new
                         FileReader("../input/PreToPost/" + args[0]));
            bw = new BufferedWriter(new
                         FileWriter("../output/PreToPost/" + args[1]));

        } catch (Exception e) {
            System.err.println(e.toString());
            return;
        }

        bw.write("Prefix expressions read from " + args[0] + "\n");

        ExpLinkBased<Character> prefixExp = new ExpLinkBased<Character>();
        //ExpLinkBased<String> prefixExpCheck = new ExpLinkBased<String>();
        String postfixExp;

        char prefixOpChar = '\0';
        int prefixExpNum = 0;
        int convFlag;

        for (int readVal = br.read(); readVal != -1; readVal = br.read()) {
            prefixOpChar = (char) readVal;
            convFlag = 0;

            if (prefixExp.size() == 0) {
                System.out.println("\n---- Begin processing of prefix" +
                                   "expression #" +
                                   (prefixExpNum+1) + " ----\n");
                bw.newLine();
                bw.write("Expression #" + (prefixExpNum+1) + ": \n");
            } // END if

            System.out.println("\nmain() : prefixOpChar = (char) readVal = "
                               + prefixOpChar);

            if (isOp(prefixOpChar) == "operator"
                || isOp(prefixOpChar) == "operand") {
                  System.out.println("\nmain() : "
                  + "prefixExp.appendOp(prefixOpChar) = prefixExp.appendOp("
                  + prefixOpChar + ")");
                prefixExp.appendOp(prefixOpChar);
                System.out.println("\nmain() : prefixExp.toString() = "
                                   + prefixExp.toString());

            } else if (Character.isSpaceChar(prefixOpChar)) {
                System.out.println("\n"
                + " . .. ... ignoring whitespace delimiter ... .. . " + "\n");

            } else if (isOp(prefixOpChar) != "eol") {
                userInput(prefixOpChar, prefixExp);

            } else if (prefixExp.size() == 0) {
                prefixExpNum++;
                System.out.println("EMPTY ROW! Nothing to see here."
                                   + " Move along.");
                bw.write("Orig prefix expression: <missing>\n");
                bw.write("Fnl postfix expression: <missing>\n");

            } else { //prefixExp.size() != 0 && isOp(prefixOpChar) == "eol"

                try {
                    String prefixExpCheck = isExp(prefixExp.cloneExp(),
                                                  prefixExp.size()-1);
                    System.out.println("\n\n--- !"
                                       + " Welcome back to main() ! ---");
                    System.out.println("\nmain() : prefixExp == "
                                  + prefixExp.toString()
                                  + " : prefixExpCheck == " + prefixExpCheck);

                    if (prefixExpCheck == "too few operators") {

                        System.out.println(
                                   "\n/////////////////////////////////////");
                        System.out.println(
                                     "/////////// INVALID PREFIX //////////");
                        System.out.println(
                                   "/////////////////////////////////////\n");
                        System.out.println(
                   "OPERATOR ERROR! Too few operators in prefix expression!");

                        bw.write("Orig prefix expression: ");
                        bw.write(prefixExp.toString());
                        bw.write("\nFnl postfix expression: <invalid>\n");
                        bw.write("ERROR ---> too few operators in prefix"
                                 + "expression\n");

                        prefixExp.removeAll();

                    } else if (prefixExpCheck == "too few operands") {
                        System.out.println(
                                   "\n/////////////////////////////////////");
                        System.out.println(
                                     "/////////// INVALID PREFIX //////////");
                        System.out.println(
                                   "/////////////////////////////////////\n");
                        System.out.println(
                     "OPERAND ERROR! Too few operands in prefix expression!");

                        bw.write("Orig prefix expression: ");
                        bw.write(prefixExp.toString());
                        bw.write("\nFnl postfix expression: <invalid>\n");
                        bw.write("ERROR ---> too few operands in prefix"
                                 + "expression\n");

                        prefixExp.removeAll();

                    } else {
                        convFlag = 1;
                    } // END if

                } catch (NoSuchElementException e) {
                     System.out.println("u sHoUlDn't bE sEeInG tHiS mEsSaGe");
                    // this try-catch-finally section is a remnant
                    // from earlier logic; will revisit later --- BDJ
                    // 22 MAR 2021

                } finally {
                    prefixExpNum++;
                    System.out.println(
                                "\n---- End processing of prefix expression #"
                                + prefixExpNum + " ----\n");

                } // END try-catch-finally

            } // END if

            if (convFlag != 1) {
                continue;

            } // END if

            System.out.println("\n\n--- ! Welcome back to main() ! ---");
            System.out.println("\nmain() : convert(prefixExp) = convert("
                               + prefixExp.toString() + ")\n");
            System.out.println(
             "\n////////////// COMMENCE CONVERSION PROCESS //////////////\n");

            bw.write("Orig prefix expression: ");
            bw.write(prefixExp.toString());
            bw.newLine();

            //postfixExp = convert(prefixExp);
            postfixExp = convert(prefixExp.cloneExp());

            System.out.print("\nOrig prefix expression: ");
            System.out.println(prefixExp.toString());
            System.out.print("\nFnl postfix expression: ");
            System.out.println(postfixExp.toString());
            System.out.println(
             "\n////////////// CONCLUDE CONVERSION PROCESS //////////////\n");

            bw.write("Fnl postfix expression: ");
            bw.write(postfixExp.toString());
            bw.newLine();

            prefixExp.removeAll();

        } // END for

        String runOut = "KB: " + (double)
            (Runtime.getRuntime().totalMemory()
             - Runtime.getRuntime().freeMemory()) / 1024;
        String runAvail = "" + Runtime.getRuntime().availableProcessors();

        System.out.println("\n --- END OF PROGRAM");
        System.out.println(" --------------- runIn = " + runIn);
        System.out.println(" -------------- runOut = " + runOut);
        System.out.println(" ------------ runAvail = " + runAvail);
        System.out.println(" --- END OF PROGRAM\n");

        br.close();
        bw.close();

    } // END main


    /**
     * Determines if input is a valid prefix expression
     *
     * @param inputExp Input prefix expression
     * @param endIndx Size of the prefix expression
     *
     * @return Text that notifies program/user whether the
     *         given expression is valid and, if not, what
     *         is missing or extraneous in expression
     *
     */
    private static String isExp(ExpLinkBased<Character> inputExp,
                                int endIndx) {
        System.out.println("\n\n--- ! Welcome to isExp() ! ---");
        System.out.println("\nisExp()  : input parameter endIndx = "
                           + endIndx);

        int pSize = inputExp.size();
        System.out.println("\nisExp()  : variable pSize = "
                           + pSize);

        System.out.println(
               "\nisExp()  : pLast = endExp(inputExp, 0, pSize - 1) = endExp("
               + inputExp.toString() + ", 0, " + (pSize - 1) + ")");
        int pLast = endExp(inputExp, 0, pSize - 1);

        System.out.println("\n\n--- ! Welcome back to isExp() ! ---");
        System.out.println("\nisExp()  : variable pSize = " + pSize);
        System.out.println("\nisExp()  : variable pLast = " + pLast);

        if (pLast >= 0 && (pLast == pSize - 1)) {
            return "isExp";
        } else if (pLast < 0) {
            return "too few operands";
        } else {
            return "too few operators";
        } // END if

    } // END isExp


    /**
     * Recursive method that is called by isExp()
     * when evaluating validity of prefix expression
     *
     * @param inputExp Input prefix expression
     * @param bgnIndx  Index available for more complicated parsing
     * @param endIndx  Size of the prefix expression
     *
     * @return Recursive enumeration through expression
     *
     */
    private static int endExp(ExpLinkBased<Character> inputExp,
                              int bgnIndx, int endIndx) {
        System.out.println("\n\n--- ! Welcome to endExp() ! ---");
        System.out.println("\nendExp() : input param inputExp = "
                           + inputExp.toString() + " : input param bgnIndx = "
                           + bgnIndx + " : input param endIndx = " + endIndx);

        if (bgnIndx < 0 || bgnIndx > endIndx) {
            return -1;
        } // END if

        char inputChar = inputExp.removeOp();
        System.out.println("\nendExp() : inputChar = inputExp.removeOp() = "
                           + inputChar);

        if (isOp(inputChar) != "operator") {
            System.out.println(
                "\nendExp() : isOp(inputChar) == operand  : inputChar = "
                + inputChar
                + " : bgnIndx = " + bgnIndx
                + " endIndx = "  + endIndx);

            return bgnIndx;

        } else if (isOp(inputChar) == "operator") {
            System.out.println(
                    "\nendExp() : isOp(inputChar) == operator  : inputChar = "
                    + inputChar
                    + " : bgnIndx = " + bgnIndx
                    + " endIndx = " + endIndx);
            int midIndx = endExp(inputExp, bgnIndx + 1, endIndx);

            System.out.println("\n\n--- ! Welcome back to endExp() ! ---");

            if (midIndx > -1) {
                System.out.println(
                     "\nendExp() : isOp(inputChar) == operand  : inputChar = "
                     + inputChar
                     + " : bgnIndx = " + bgnIndx
                     + " midIndx = " + midIndx
                     + " : endIndx = " + endIndx);

                return endExp(inputExp, midIndx + 1, endIndx);

            } else {
                System.out.println("\nendExp() : RETURN -1");

                return -1;
            } // END if

        } else {
            System.out.println("\nendExp() : inputChar = " + inputChar
                               + " : bgnIndx = " + bgnIndx
                               + " endIndx = " + endIndx);

            return -1;
        } // END if

    } // END endExp


    /**
     * Converts validated prefix expression to corresponding
     * postfix equivalent
     *
     * @param inputExp Input prefix expression
     *
     * @return Postfix expression
     *
     */
    private static String convert(ExpLinkBased<Character> inputExp) {
       System.out.println("\n\n--- ! Welcome to convert() ! ---");
       char inputOpChar = inputExp.removeOp();

        if (isOp(inputOpChar) != "operator") {
            System.out.println(
                "\nconvert() : isOp(inputOpChar) == operand : inputOpChar == "
                + inputOpChar);
            System.out.println("\nconvert() : RETURN inputOpChar");
            System.out.println("\nconvert() : RETURN " + inputOpChar);
            return "" + inputOpChar;

        } else if (isOp(inputOpChar) == "operator") {
            System.out.println(
               "\nconvert() : isOp(inputOpChar) == operator : inputOpChar == "
               + inputOpChar);

            System.out.println(
                      "\nconvert() : postfix1 = convert(inputExp) == convert("
                      + inputExp + ")");
            String postfix1 = convert(inputExp);

            System.out.println(
                      "\nconvert() : postfix2 = convert(inputExp) == convert("
                      + inputExp + ")");
            String postfix2 = convert(inputExp);

            System.out.println(
                    "\nconvert() : RETURN postfix1 + postfix2 + inputOpChar");
            System.out.println("\nconvert() : RETURN "
                               + postfix1 + postfix2 + inputOpChar);
            return "" + postfix1 + postfix2 + inputOpChar;

        } else {
            System.out.println("\nconvert() : Alert! Alert! Alert!");
            return null;

        } // END if

    } // END convert


    /**
     * Lets user select from options during runtime
     * from command line interface; used to decide
     * upon course of action when input char is
     * undetermined or otherwise ambiguous
     *
     * @param pOp  Character of interest
     * @param pExp Input prefix expression
     *
     */
    private static void userInput(char pOp,
                        ExpLinkBased<Character> pExp) throws IOException {

        BufferedReader brSys = new BufferedReader(
                                     new InputStreamReader(System.in));
        int intInput = -1;
        char charInput = pOp;

        System.out.println("\n/////////////////////////////////////");
        System.out.println("//////////// INPUT ERROR ////////////");
        System.out.println("/////////////////////////////////////\n");
        System.out.println("PreToPost does not recognize the element "
                           + pOp + " from prefix expression.");
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("     1. Exclude element from further processing");
        System.out.println("     2. Treat element as operand");
        System.out.println("     3. Manually change the element\n");

        while (intInput < 1 || intInput > 3) {

            try {
                intInput = Integer.parseInt(brSys.readLine());
            } catch (NumberFormatException e) {
            }

            if (intInput == 1) {
                // don't do anything ... logic should take me out of loop
                System.out.println();

            } else if (intInput == 2) {
                pExp.appendOp(pOp);
                System.out.println("\nAppending operand to expression: "
                                   + pOp);

            } else if (intInput == 3) {
                System.out.println("\nPlease input single character:\n");

                String strInput = brSys.readLine();
                pOp = strInput.charAt(0);
                pExp.appendOp(pOp);
                System.out.println("\nChanging " + charInput + " to "
                                   + pOp + ".");
                System.out.println("\nAppending element to stack: "
                                   + pOp);

            } else {
                System.out.println("\nHmmmmm .... no me gusto.");
                System.out.println("Please select a valid option.\n");

            } // END if

        } // END while

        } // END userInput

} // END PreToPost
