package Susquehanna;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class wordSuffixTest {

    @Test
    public void test1() {
        String[] words = {"back", "backdoor", "gammon", "backgammon", "comeback", "come", "door"};
        assertEquals(3, wordSuffix.solution4(words));
    }

    @Test
    public void test2() {
        String[] words = {"cba", "a", "a", "b", "ba", "ca"};
        assertEquals(8, wordSuffix.solution4(words));
    }

    @Test
    public void test3() {
        String[] words = {"al", "gnal", "signal", "designal", "codesignal"};
        assertEquals(10, wordSuffix.solution4(words));
    }

    @Test
    public void test4() {
        String[] words = {"codesignal"};
        assertEquals(0, wordSuffix.solution4(words));
    }

    @Test
    public void test5() {
        String[] words = {"a", "a", "a", "a"};
        assertEquals(6, wordSuffix.solution4(words));
    }

    @Test
    public void test6() {
        String[] words = {"cba", "a", "ca", "g", "ag", "ga"};
        assertEquals(4, wordSuffix.solution4(words));
    }

    @Test
    public void test7() {
        String[] words = {"y", "z", "z", "zzzzz", "zzzzz", "zzzzzzzzzz", "y"};
        assertEquals(11, wordSuffix.solution4(words));
    }

}