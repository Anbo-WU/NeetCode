package Optiver25SDE;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 Input to the program is specified using a simple text format. The format and details of parsing are not relevant to answering the question but custom input can be used to help with development and debugging.
 The first line of input contains an integer N that denotes the number of instructions.Each of the N subsequent lines contains one of the instructions as in the format below:
 - CustomerEnter <CustomerId> <LineNumber> <NumItems>
 - BasketChange <CustomerId> <NewNumItems>
 - LineService <LineNumber> <NumProcessedItems>
 - LinesService
 Some example inputs and their expected outputs are described below.

Sample Case 1:
 Input:
 5
 CustomerEnter 123 1 5
 CustomerEnter 2 2 3
 LinesService
 CustomerEnter 3 1 2
 LineService 1 6

 Expected Output:
 123
 3

 Explanation:
 There are 2 customers (123 and 2) queued on two lines (1 and 2). When first LinesService is called on both lines, both
 queued customers still have some items to check out. Namely, customer 123 has still 4 items, and customer 2 has 2 items
 to checkout. Then customer 3 joins to the 1st line. After next LineService on the 1st line, both customers with 123 and
 both customers with 123 and 3 IDs are checked out (first 123, and then 3). Customer 2 is still in the line.

Sample Case 2:
 Input:
 5
 CustomerEnter 123 1 5
 CustomerEnter 3 1 2
 LinesService 1 4
 BasketChange 123 6
 LineService 1 5

 Expected Output:
 3
 123

 Explanation:
 Upon first LineService 4 out of 5 items of customer 123 are processed. However, customer then increases the number of
 items in their basket (namely adds 1 extra), this puts them at the back of the line. During the next LineService
 call customer 3 is checked out first, and customer 123 is checked out next (as they only had 2 items left to process).

 */
public class SupermarketCheckoutTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }


    @Test
    public void testSampleCase1() {
        SupermarketCheckout checkout = new SupermarketCheckout();

        checkout.onCustomerEnter(123, 1, 5);
        checkout.onCustomerEnter(2, 2, 3);
        checkout.onLinesService(); // each line -1
        checkout.onCustomerEnter(3, 1, 2);
        checkout.onLineService(1, 6); // line 1 processes 6 items

        String[] lines = outputStream.toString().trim().split("\\R");
        assertArrayEquals(new String[]{"123", "3"}, lines);
    }

    @Test
    public void testSampleCase2() {
        SupermarketCheckout checkout = new SupermarketCheckout();

        checkout.onCustomerEnter(123, 1, 5);
        checkout.onCustomerEnter(3, 1, 2);
        checkout.onLineService(1, 4);   // customer 123 has 1 item left
        checkout.onBasketChange(123, 6); // now customer 123 goes to back of the line with 6 items
        checkout.onLineService(1, 5); // process line 1: customer 3 (2 items) + 123 (4 left)

        String[] lines = outputStream.toString().trim().split("\\R");
        // System.out.println("Actual Output：" + Arrays.toString(lines));
        // originalOut.println("Actual Output：" + Arrays.toString(lines));
        assertArrayEquals(new String[]{"3", "123"}, lines);
    }
}