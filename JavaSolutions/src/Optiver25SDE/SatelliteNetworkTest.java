package Optiver25SDE;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 Input to the program is specified using a simple text format. The format and details of parsing are not relevant to answering the question but custom input can be used to help with development and debugging.
 The first line of input contains an integer N that denotes the number of instructions. Each of the N subsequent lines contains one of the instructions as in the format below:
 - SatelliteConnected <SatelliteId>
 - RelationshipEstablished<SatelliteId1><SatelliteId2>
 - MessageReceived <M><SatelliteId1><SatelliteId2>...<SatelliteIdM>
 Some example inputs and their expected outputs are described below.

 Sample Case 1:
 Input:
 5
 SatelliteConnected 1
 SatelliteConnected 2
 SatelliteConnected 3
 RelationshipEstablished 2 1
 MessageReceived 1 2

 Expected Output:
 SatelliteReportedBack: 1
 SatelliteReportedBack: 2

 Explanation:
 There are 3 satellites (1, 2 and 3) and there is one relationship between satellite 2and satellite 1. Only satellite 2 directly receives the message from Earth.
 Satellite 2 takes 10s to notify satellite 1, then 30s more to process the message and report back to Earth, totalling 40s.
 Satellite 1 doesn't need to forward the message to anyone, so it only takes 30s to process the message and report back to Earth. Since it received the message att=10s, their report arrives at Earth at t=40s.
 Satellite 1 ultimately arrives on Earth first because its SatelliteId is smaller.

 Sample Case 2:
 Input:
 12
 SatelliteConnected 1
 SatelliteConnected 2
 SatelliteConnected 3
 SatelliteConnected 4
 SatelliteConnected 5
 RelationshipEstablished 1 3
 RelationshipEstablished 1 2
 RelationshipEstablished 2 5
 RelationshipEstablished 3 2
 RelationshipEstablished 3 4
 RelationshipEstablished 3 5
 MessageReceived 2 1 3

 Expected Output:
 SatelliteReportedBack: 1
 SatelliteReportedBack: 2
 SatelliteReportedBack: 3
 SatelliteReportedBack: 4
 SatelliteReportedBack: 5


 Explanation:
 The message is sent to satellites 1 and 3, and because of this they don't try to notify each other (despite having a relationship).
 Satellite 1 takes 10s to notify satellite 2, then 30s to process the message and report back to Earth, totalling 40s.
 Satellite 2 takes 10s to notify satellite 5 (from t=10s), then 30s to process the message and report back to Earth on t=50s.
 Satellite 3 notifies satellite 2 on t=10s (simultaneously with satellite 1), then satellite 4 on t=20s. Satellite 5 is next, but it has just been notified on t=20s by satellite 2, so it is skipped. Satellite 3 then takes 30s more to process the message and report back to Earth, totalling 5Os since being notified on t=0.
 Satellite 4 is notified on t=20s by satellite 3, then process the message and report back to Earth on t=50s.
 Satellite 5 is notified on t=20s by satellite 2, then process the message and report back to Earth on t=50s.
 Since satellites 2, 3, 4 and 5 are all notified at the same time, on t=50s, they're reported in increasing order of ids.

 */
class SatelliteNetworkTest {
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
        SatelliteNetwork net = new SatelliteNetwork();

        net.satelliteConnected(1);
        net.satelliteConnected(2);
        net.satelliteConnected(3);
        net.relationshipEstablished(2, 1);
        net.messageReceived(List.of(2L));

        String[] output = outputStream.toString().trim().split("\\R");
        Arrays.sort(output); // sort by reported time, then satelliteId
        Assertions.assertArrayEquals(new String[]{
                "1",
                "2"
        }, output);
    }

    @Test
    public void testSampleCase2() {
        SatelliteNetwork net = new SatelliteNetwork();

        net.satelliteConnected(1);
        net.satelliteConnected(2);
        net.satelliteConnected(3);
        net.satelliteConnected(4);
        net.satelliteConnected(5);

        net.relationshipEstablished(1, 3);
        net.relationshipEstablished(1, 2);
        net.relationshipEstablished(2, 5);
        net.relationshipEstablished(3, 2);
        net.relationshipEstablished(3, 4);
        net.relationshipEstablished(3, 5);

        net.messageReceived(List.of(1L, 3L));

        String[] output = outputStream.toString().trim().split("\\R");
        Arrays.sort(output);
        Assertions.assertArrayEquals(new String[]{
                "1",
                "2",
                "3",
                "4",
                "5"
        }, output);
    }

}