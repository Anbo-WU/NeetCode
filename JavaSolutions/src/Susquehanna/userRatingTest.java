package Susquehanna;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class userRatingTest {

    @Test
    public void testBeginnerLevel() {
        int initial = 1500;
        int[] changes = {-100, -300, 450, 500, -500, -600};
        String result = userRating.solution1(initial, changes);
        assertEquals("beginner", result);
    }

    @Test
    public void testAdvanced() {
        int initial = 1000;
        int[] changes = {100, 200, 300, 400, -500};
        String result = userRating.solution1(initial, changes);
        assertEquals("advanced", result);
    }

    @Test
    public void testNotExceed2500() {
        int initial = 2490;
        int[] changes = {10, 20};
        String result = userRating.solution1(initial, changes);
        assertEquals("pro", result);
    }
}