package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void baseTest() {

        String input = "~~";
        List<String> actual = parser.parse(input);

        assertEquals(1, actual.size());
        assertEquals("0: ~~", actual.get(0));

        input = "~aab~";
        actual = parser.parse(input);

        assertEquals(1, actual.size());
        assertEquals("0: ~aab~", actual.get(0));

        input = "~abb~";
        actual = parser.parse(input);

        assertEquals(1, actual.size());
        assertEquals("0: ~abb~", actual.get(0));
    }

    @Test
    public void combinedTest(){
        String input = "~aab~abb~";
        List<String> actual = parser.parse(input);

        assertEquals(2, actual.size());
        assertEquals("0: ~aab~", actual.get(0));
        assertEquals("4: ~abb~", actual.get(1));

        input = "~abbaaaabaaabb~";
        parser.parse(input);


    }
}