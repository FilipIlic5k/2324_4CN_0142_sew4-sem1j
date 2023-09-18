package firstTry;

import org.junit.jupiter.api.Test;

import static firstTry.WordCount.count;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCountTest {

    @Test
    void leicht() {
        assertEquals(0, count(""));
        assertEquals(0, count(" "));
        assertEquals(0, count("  "));
    }

    @Test
    void normal() {
        assertEquals(1, count("eins"));
        assertEquals(1, count(" eins"));
        assertEquals(1, count("eins "));
        assertEquals(1, count(" eins "));
        assertEquals(1, count(" eins  "));
        assertEquals(1, count("  eins "));
        assertEquals(1, count(" eins  "));

        assertEquals(1, count("eins:"));
        assertEquals(1, count(":eins"));
        assertEquals(1, count(":eins:"));
        assertEquals(1, count(" eins   "));
        assertEquals(1, count(" eins : "));
        assertEquals(1, count(": eins  :"));
        assertEquals(3, count("ein erster Text"));
        assertEquals(3, count(" ein  erster Text      "));
        assertEquals(3, count("ein:erster.Text"));
    }

}