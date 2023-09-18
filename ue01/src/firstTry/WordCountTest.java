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
    
   @Test
   void easyHTML(){
        assertEquals(1, count(" eins  <html> "));
        assertEquals(1, count(" eins  < html> "));
        assertEquals(1, count(" eins  <html > "));
        assertEquals(1, count(" eins  < html > "));
        assertEquals(4, count(" eins <html> zwei<html>drei <html> vier"));

        assertEquals(2, count(" eins <html> zwei "));
        assertEquals(2, count(" eins <html>zwei "));
        assertEquals(2, count(" eins<html> zwei "));
        assertEquals(2, count(" eins<html>zwei "));
        assertEquals(2, count(" eins<img alt=\\\"xxx\\\" > zwei"));

        assertEquals(2, count(" eins \\\"zwei\\\" "));
        assertEquals(2, count(" eins\\\"zwei\\\" "));
        assertEquals(2, count(" eins \\\"zwei\\\""));
        assertEquals(3, count(" eins \\\"zwei\\\"drei"));
   }
    @Test
    void hardHTML(){
        assertEquals(2, count(" eins<img alt=\"<bild>\" > zwei"));
        assertEquals(2, count(" eins<img alt=\\\"bild>\\\" > zwei"));
        assertEquals(2, count(" eins<img alt=\\\"<bild>\\\" keinwort> zwei"));
        assertEquals(2, count(" eins<img alt=\\\"<bild>\\\" src=\\\"bild.png\\\" >zwei"));
        assertEquals(2, count(" eins<img alt=\\\"<bild\\\" keinwort>zwei"));

        assertEquals(1, count(" eins<img alt=\\\"<bild\\\" keinwort"));
        assertEquals(2, count(" eins<img alt=\\\"<bild\\\" keinwort> zwei"));
        assertEquals(1, count(" eins<img alt=\\\"<bild keinwort> keinwort"));
        assertEquals(2, count(" eins<img alt=\\\"<bild keinwort keinwort\\\">zwei"));
        assertEquals(2, count(" eins<img alt=\\\"<bild keinwort< keinwort\\\">zwei"));
    }

    @Test
    void insaneHardHTML(){
        assertEquals(2, count(" eins<img alt=\\\"<bild \\\\\\\" keinwort> keinwort\\\" keinwort>zwei"));
        assertEquals(2, count(" eins<img alt=\\\"<bild \\\\\\\" keinwort<keinwort\\\" keinwort>zwei"));
        assertEquals(2, count(" eins<img alt=\\\"<bild \\\\\\\" keinwort keinwort\\\" keinwort>zwei"));
    }

    @Test
    void invalidHTML(){
        assertEquals(1, count(" eins<html"));
    }

}