  // leicht
  count("") == 0
  count(" ") == 0
  count("  ") == 0

  // normal
  count("eins") == 1
  count(" eins") == 1
  count("eins ") == 1
  count(" eins ") == 1
  count(" eins  ") == 1
  count("  eins ") == 1
  count("  eins  ") == 1

  count("eins:") == 1
  count(":eins") == 1
  count(":eins:") == 1
  count(" eins  ") == 1
  count(" eins : ") == 1
  count(": eins :") == 1
  count("ein erster Text") == 3
  count(" ein  erster   Text      ") == 3
  count("ein:erster.Text") == 3

  // vielleicht falsch
  count("a") == 1
  count(" a") == 1
  count("a ") == 1
  count(" a ") == 1

  // mit html
  count(" eins  <html> ") == 1
  count(" eins  < html> ") == 1
  count(" eins  <html > ") == 1
  count(" eins  < html > ") == 1
  count(" eins <html> zwei<html>drei <html> vier") == 4

  count(" eins <html> zwei ") == 2
  count(" eins <html>zwei ") == 2
  count(" eins<html> zwei ") == 2
  count(" eins<html>zwei ") == 2
  count(" eins<img alt=\"xxx\" > zwei") == 2
  count(" eins<img alt=\"xxx yyy\" > zwei") == 2

  count(" eins \"zwei\" ") == 2
  count(" eins\"zwei\" ") == 2
  count(" eins \"zwei\"") == 2
  count(" eins \"zwei\"drei") == 3
  count(" eins \"zwei\" drei") == 3

  // html - trickreich
  // Achtung: das ist teilweise nicht ganz legales HTML
  count(" eins<html") == 1 // kein >

  count(" eins<img alt=\"<bild>\" > zwei") == 2 // <> innerhalb ""
  count(" eins<img alt=\"bild>\" > zwei") == 2 // <> innerhalb ""
  count(" eins<img alt=\"<bild>\" keinwort> zwei") == 2
  count(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei") == 2
  count(" eins<img alt=\"<bild\" keinwort>zwei") == 2

  count(" eins<img alt=\"<bild\" keinwort") == 1
  count(" eins<img alt=\"<bild\" keinwort> zwei") == 2
  count(" eins<img alt=\"<bild keinwort> keinwort") == 1
  count(" eins<img alt=\"<bild keinwort keinwort\">zwei") == 2
  count(" eins<img alt=\"<bild keinwort< keinwort\">zwei") == 2

  // ganz ganz fies -- \ entwertet das nächste Zeichen
  count(" eins<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>zwei") == 2
  count(" eins<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>zwei") == 2
  count(" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei") == 2
  
  count(" \\\"null\\\" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei \"drei\"") == 4
 
