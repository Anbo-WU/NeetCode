package Optiver25SDE;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 Input 1:
 10
 REQUIREMENT 1 100 3
 DATA 1 1
 DATA 1 110
 DATA 1 3
 DATA O O
 DATA 2 1
 DATA 2 90
 DATA 2 4
 DATA O 0
 DATA O 0

 output 1:
 0
 0
 0
 0
 0
 0
 2
 3
 0

 Explanation:
 The message with id 1 does not fulfill the requirement because the price of 110 is higher than the required 100.
 The message with id 2 does fulfill the requirement. The requirement is fulfilled once the number of available seats is received, so an order message for original message id 2 and 3 seats (as per the requirement) is sent. At all other times, no message is being sent (0).

 Input 2:
 10
 REQUIREMENT 1 100 2
 DATA 1 1
 DATA 2 1
 DATA 2 90
 DATA 1 110
 DATA 1 3
 DATA 2 3
 DATA 0 0
 DATA 0 0
 DATA 0 0

 output 2:
 0
 0
 0
 0
 0
 2
 2
 0
 0

 Input 3:
 12
 REQUIREMENT 1 100 1
 DATA 1 1
 DATA 1 110
 DATA 1 3
 DATA 0 0
 DATA 2 1
 DATA 2 90
 DATA 2 3
 DATA 0 0
 DATA 0 0
 DATA 0 0
 DATA 0 0

 output 3:
 0
 0
 0
 0
 0
 2
 1
 0
 0
 0
 0

 */
class ConcertTicketBuyerTest {
    private ConcertTicketBuyer buyer;

    @BeforeEach
    void setUp() {
        buyer = new ConcertTicketBuyer();
    }

    @Test
    void testSampleCase_CustomInput() {
        List<Integer> outputs = new ArrayList<>();

        // Step 1: Add requirement
        buyer.OnNewRequirement(1, 100, 3); // artistId = 1, price <= 100, seats >= 3

        // Step 2-10: Feed data packets and collect outputs
        outputs.add(buyer.ProcessData(1, 1));      // artistId = 1
        outputs.add(buyer.ProcessData(1, 110));    // price = 110 → too expensive
        outputs.add(buyer.ProcessData(1, 3));      // seats = 3 → not matched due to price
        outputs.add(buyer.ProcessData(0, 0));      // placeholder call
        outputs.add(buyer.ProcessData(2, 1));      // artistId = 1
        outputs.add(buyer.ProcessData(2, 90));     // price = 90 → matches
        outputs.add(buyer.ProcessData(2, 4));      // seats = 4 → matches → triggers order (step 1)
        outputs.add(buyer.ProcessData(0, 0));      // return number of seats (step 2)
        outputs.add(buyer.ProcessData(0, 0));      // nothing now

        // Expected outputs
        List<Integer> expected = Arrays.asList(
                0, 0, 0, 0, 0, 0, 2, 3, 0
        );

        assertEquals(expected, outputs);
    }

    @Test
    void testSampleCase2_DisorderedInput() {
        buyer = new ConcertTicketBuyer(); // reset
        List<Integer> outputs = new ArrayList<>();

        buyer.OnNewRequirement(1, 100, 2);

        outputs.add(buyer.ProcessData(1, 1));
        outputs.add(buyer.ProcessData(2, 1));
        outputs.add(buyer.ProcessData(2, 90));
        outputs.add(buyer.ProcessData(1, 110));
        outputs.add(buyer.ProcessData(1, 3));
        outputs.add(buyer.ProcessData(2, 3));  // should trigger order here
        outputs.add(buyer.ProcessData(0, 0));  // return seats
        outputs.add(buyer.ProcessData(0, 0));
        outputs.add(buyer.ProcessData(0, 0));

        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 2, 2, 0, 0);
        assertEquals(expected, outputs);
    }

    @Test
    void testSampleCase3_OnlyOneSeat() {
        buyer = new ConcertTicketBuyer(); // reset
        List<Integer> outputs = new ArrayList<>();

        buyer.OnNewRequirement(1, 100, 1);

        outputs.add(buyer.ProcessData(1, 1));
        outputs.add(buyer.ProcessData(1, 110)); // too expensive
        outputs.add(buyer.ProcessData(1, 3));   // ignored
        outputs.add(buyer.ProcessData(0, 0));
        outputs.add(buyer.ProcessData(2, 1));
        outputs.add(buyer.ProcessData(2, 90));
        outputs.add(buyer.ProcessData(2, 3));   // match here
        outputs.add(buyer.ProcessData(0, 0));   // return seats = 1
        outputs.add(buyer.ProcessData(0, 0));
        outputs.add(buyer.ProcessData(0, 0));
        outputs.add(buyer.ProcessData(0, 0));

        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0);
        assertEquals(expected, outputs);
    }
}