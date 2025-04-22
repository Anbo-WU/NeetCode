package Susquehanna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class songAnimationTest {

    @Test
    public void testBasicMatch() {
        String[] songs = {"notion:180", "voyage:185", "sample:180"};
        String[] animations = {"circles:360", "squares:180", "lines:37"};
        String[] expected = {"squares:1", "lines:5", "squares:1"};
        assertArrayEquals(expected, songAnimation.solution5(songs, animations));
    }

    @Test
    public void testExactMultiple() {
        String[] songs = {"track1:100", "track2:200"};
        String[] animations = {"anim1:50", "anim2:25"};
        String[] expected = {"anim1:2", "anim1:4"};
        assertArrayEquals(expected, songAnimation.solution5(songs, animations));
    }

    @Test
    public void testOnlyOneValidMatch() {
        //FIXME: this test may be inappropriate
        String[] songs = {"track:240"};
        String[] animations = {"a1:100", "a2:80", "a3:60"};
        String[] expected = {"a2:3"};
        assertArrayEquals(expected, songAnimation.solution5(songs, animations));
    }

    @Test
    public void testSameNameDifferentLength() {
        String[] songs = {"x:300"};
        String[] animations = {"x:150", "x:100"};
        String[] expected = {"x:2"};
        assertArrayEquals(expected, songAnimation.solution5(songs, animations));
    }
}