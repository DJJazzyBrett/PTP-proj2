/*
 * $Header: /PTP-proj2/README.txt,v 1.0, 23 March 2021 $
 * $Author: DJJazzyBrett $
 * $Version: 1.0 $
 * $Date: 23 March 2021 $
 *
 * =============================================================================
 *
 * A RECURSIVE SOLUTION FOR ARITHMETIC EXPRESSION CONVERSION
 *
 * PreToPost
 *
 * =============================================================================
 *
 */


////////////////////////////////////////////////////////////////////////////////
//      PreToPost 2.0  -  $Date: 23 March 2021 $
////////////////////////////////////////////////////////////////////////////////


----> ALERT! <----

      PLEASE READ OR SKIM THIS FILE BEFORE OR AFTER USING THIS MODULE






0. Table of Contents
================================================================================

0. Table of Contents
1. PreToPost Introduction
2. Command Line Arguments
3. Sample I/O Files
4. Setup Specifications
5. Miscellaneous


1. PreToPost Introduction
================================================================================
The following module contains the necessary tools to assist in the conversion of
arithmetic expressions: from prefix notation to postfix notation. At the center
of the accompanying infrastructure lies a linked-list data structure. Built
from a collection of one or more nodes, this linked implementation can
dynamically adjust to hold, re-order, truncate, or evaluate expressions of
any form. For the purposes of this module, though, we limit our focus to the
realm of prefix-to-postfix notation.

ExpLinkBased is a container class that can store and manipulate expression
or notation data. It's an abstract encapsulation of a syntactical ledger
of sorts:

   - it is an immutable data structure but has a cloneExp() method that allows
     its users to copy all [or a particular subset] of the elements of any exp

   - it has internal mechanisms that allow it to dynamically adjust its size
     depending upon the current runtime conditions

   - it comes fully-loaded with add, remove, clear, clone, and search methods

The PreToPost class accepts any number of expressions as input from a .txt file.
The expression is limited to one line, or row; it cannot span across multiple
lines. Whitespace characters within a line are ignored by default, although
private options exist that could potentially allow a user to incorporate
whitespace in a delimiting fashion.

A major constraint of the PreToPost class that should be noted is its inability
to account for compound values; singular values that are comprised of more
than one character or digit. Thus, the processing that occurs within the
PreToPost class regards all input as a single character, regardless of any
delimiters or whitespace characters that may be present.


2. Command Line Arguments
================================================================================
A graphical representation of the module's file structure is included in
dir-tree.txt. This text file is in the same folder as this README document.

After having unzipped the PTP-proj2.zip folder and stored its contents
somewhere on your system, navigate to the './PTP-proj2/src/' directory
using your terminal of choice. Both the source files and compiled versions of
these files are situated in this locale.

To re-compile the source files, execute the following command:

djjazzy@home: ~/PTP-proj2/src$ javac *.java


To run the required .txt file, input the following command into your terminal
console:

$ java PreToPost    /required/input.txt /required/output.txt


----> Nomenclature note <----

      No such /required/output.txt file is included in the contents of the
      module. The PreToPost class offers the user an interactive experience
      by which one can select one of three options via the command line
      when prompted by the program. This will primarily occur when the
      PreToPost class does not recognize a particular element being read
      from the input file. Here is an example of what you might encounter.

      // Begin command line selection example //

      PreToPost does not recognize the element ^ from prefix expression.

      What would you like to do?

           1. Exclude element from further processing
           2. Treat element as operand
           3. Manually change the element

      // End command line selection example //

      By selecting option 3, you will then be directed to input a single
      character through the command line interface. Make a mistake and
      you will be shamed/mocked/scorned! Just kidding ... you'll just
      be prompted again to make a valid selection.

      To demonstrate this functionality, I have included three output
      files that are all derived from the same /required/input.txt file.

      /required/output-option1.txt
      /required/output-option2.txt
      /required/output-option3.txt

      Each output file corresponds to the option I selected during their
      processing. Note that for option3, I input "Z" and "$" respectively
      when directed by the prompt.

      Finally, please observe that both an input file and an output file must be declared as
      arguments. The input file must already be in existence; however, the
      output file need not already have been created. One will be created in the
      appropriate directory if it does not exist prior to program execution.


3. Sample I/O Files
==================================================================================
To assist in the evaluation of the PreToPost class, seven sample
I/O files are included in the module. The seven files are organized as follows:


Input files --- > /PTP-proj2/input/PreToPost/

             dblPlusGood.txt
                 holyExp.txt
              lengthyExp.txt
            makeUpUrMind.txt
             rWeThereYet.txt
              switcheroo.txt
    WeTalkinBoutPractice.txt

The titles of the files - however whimsical and irreverent they may be - are
meant to reflect the type of scenario being tested by their contents.


Output files --- > /PTP-proj2/output/PreToPost/

      same names as input

Please remember that any output file can be specified; the ones included in the
module are meant to assist in program evaluation.


4. Setup Specifications
================================================================================
The design and construction of java programs in this module utilized the
following tools / software / components:


----> Emacs Java IDE using Eclipse JDT Language Server <----

      https://emacs-lsp.github.io/lsp-java/
      https://projects.eclipse.org/projects/eclipse.jdt.ls


----> GNU Emacs <----

      GNU Emacs 27.1
      Copyright (C) 2020 Free Software Foundation, Inc.
      GNU Emacs comes with ABSOLUTELY NO WARRANTY.
      You may redistribute copies of GNU Emacs
      under the terms of the GNU General Public License.
      For more information about these matters, see the file named COPYING.


----> Java <----

      openjdk 11.0.10 2021-01-19
      OpenJDK Runtime Environment (build 11.0.10+9-Ubuntu-0ubuntu1.18.04)
      OpenJDK 64-Bit Server VM (build 11.0.10+9-Ubuntu-0ubuntu1.18.04,
                                mixed mode, sharing)


5. Miscellaneous
================================================================================
Looking for a good time? Try redirecting the console output to a text file and
read through some of the output. See if you can connect the dots and trace the
recursive pattern of method calls.

java PreToPost > ../output/PreToPost/log.txt lengthyExp.txt lengthyExp.txt
