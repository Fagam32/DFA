import parser.Parser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        List<String> output = parser.parse("~aab~");
        output.forEach(System.out::println);

    }
}
