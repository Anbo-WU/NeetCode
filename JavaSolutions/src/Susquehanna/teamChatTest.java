package Susquehanna;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class teamChatTest {

    @Test
    public void test1() {
        String[] members = {"id42", "id158", "id23"};
        String[][] events = {
                {"MESSAGE", "0", "ALL id158 id42"},
                {"OFFLINE", "1", "id158"},
                {"MESSAGE", "2", "id158 1d158"},
                {"OFFLINE", "3", "id23"},
                {"MESSAGE", "60", "HERE id158 id42 id23"},
                {"MESSAGE", "61", "HERE"}
        };

        String[] expected = {"id158=4", "id23=2", "id42=3"};
        String[] actual = teamChat.solution3(members, events);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void test2() {
        String[] members = {"id5", "id555"};
        String[][] events = {{"MESSAGE", "80", "id5"}};

        String[] expected = {"id5=1", "id555=0"};
        String[] actual = teamChat.solution3(members, events);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void test3() {
        String[] members = {"id8", "id2", "id5", "id569"};
        String[][] events = {
                {"MESSAGE", "0", "id2 id5"},
                {"MESSAGE", "1", ""},
                {"MESSAGE", "5", "id569"},
                {"MESSAGE", "6", "ALL ALL HERE"}

        };

        String[] expected = {"id2=2", "id5=2", "id569=2", "id8=1"};
        String[] actual = teamChat.solution3(members, events);

        assertArrayEquals(expected, actual);
    }

}