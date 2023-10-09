package firstTry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCountTest {
    WordCount wc = new WordCount();

    @Test
    void leicht() {
        assertEquals(0, wc.count(""));
        assertEquals(0, wc.count(" "));
        assertEquals(0, wc.count("  "));
    }

    @Test
    void normal() {
        assertEquals(1, wc.count("eins"));
        assertEquals(1, wc.count(" eins"));
        assertEquals(1, wc.count("eins "));
        assertEquals(1, wc.count(" eins "));
        assertEquals(1, wc.count(" eins  "));
        assertEquals(1, wc.count("  eins "));
        assertEquals(1, wc.count(" eins  "));

        assertEquals(1, wc.count("eins:"));
        assertEquals(1, wc.count(":eins"));
        assertEquals(1, wc.count(":eins:"));
        assertEquals(1, wc.count(" eins   "));
        assertEquals(1, wc.count(" eins : "));
        assertEquals(1, wc.count(": eins  :"));
        assertEquals(3, wc.count("ein erster Text"));
        assertEquals(3, wc.count(" ein  erster Text      "));
        assertEquals(3, wc.count("ein:erster.Text"));
    }
    
   @Test
   void easyHTML(){
        assertEquals(1, wc.count(" eins  <html> "));
        assertEquals(1, wc.count(" eins  < html> "));
        assertEquals(1, wc.count(" eins  <html > "));
        assertEquals(1, wc.count(" eins  < html > "));
        assertEquals(4, wc.count(" eins <html> zwei<html>drei <html> vier"));

        assertEquals(2, wc.count(" eins <html> zwei "));
        assertEquals(2, wc.count(" eins <html>zwei "));
        assertEquals(2, wc.count(" eins<html> zwei "));
        assertEquals(2, wc.count(" eins<html>zwei "));
        assertEquals(2, wc.count(" eins<img alt=\"xxx\" > zwei"));

        assertEquals(2, wc.count(" eins \"zwei\" "));
        assertEquals(2, wc.count(" eins\"zwei\" "));
        assertEquals(2, wc.count(" eins \"zwei\""));
        assertEquals(3, wc.count(" eins \"zwei\"drei"));
   }
    @Test
    void hardHTML(){
        assertEquals(2, wc.count(" eins<img alt=\"<bild>\" > zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"bild>\" > zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild>\" keinwort> zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild\" keinwort>zwei"));

        assertEquals(1, wc.count(" eins<img alt=\"<bild\" keinwort"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild\" keinwort> zwei"));
        assertEquals(1, wc.count(" eins<img alt=\"<bild keinwort> keinwort"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild keinwort keinwort\">zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild keinwort< keinwort\">zwei"));
    }

    @Test
    void insaneHardHTML(){
        assertEquals(2, wc.count(" eins<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>zwei"));
        assertEquals(2, wc.count(" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei"));
    }

    @Test
    void invalidHTML(){
        assertEquals(1, wc.count(" eins<html"));
    }

}