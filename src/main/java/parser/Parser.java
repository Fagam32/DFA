package parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private int currentSymbolIndex;
    private String data;
    private int firstTilda;
    private int secondTilda;
    private StringBuilder currentOutputString;
    private ArrayList<String> result;

    private void init(String inputString) {
        currentSymbolIndex = 0;
        this.data = inputString;
        this.firstTilda = -1;
        this.secondTilda = -1;
        this.currentOutputString = new StringBuilder();
        this.result = new ArrayList<>();
    }

    public List<String> parse(String inputString) {
        init(inputString);
        State currentState = State.Q0.type.apply(this);
        try {
            while (!currentState.equals(State.Q8)) {
                currentState = currentState.type.apply(this);
            }
        } catch (IndexOutOfBoundsException ex) {
        }
        if (result.size() == 0)
            result.add("No output found");
        return result;
    }

    State q0() {
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            return State.Q0;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            return State.Q0;
        }
        if (currentSymbolIsTilda()) {
            firstTilda = currentSymbolIndex;
            currentSymbolIndex++;
            return State.Q1;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q1() {
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            currentOutputString.append('a');
            return State.Q2;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        if (currentSymbolIsTilda()) {
            changeTilda(currentSymbolIndex);
            currentSymbolIndex++;
            return State.Q6;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q2() {
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            currentOutputString.append('a');
            return State.Q3;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            currentOutputString.append('b');
            return State.Q4;
        }
        if (currentSymbolIsTilda()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q3() {
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            currentOutputString.append('a');
            return State.Q2;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            currentOutputString.append('b');
            return State.Q5;
        }
        if (currentSymbolIsTilda()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q4() {
        if (currentSymbolIsA()) {
            currentOutputString.append('a');
            return State.Q7;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            currentOutputString.append('b');
            return State.Q1;
        }
        if (currentSymbolIsTilda()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q5() {
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            currentOutputString.append('a');
            return State.Q2;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        if (currentSymbolIsTilda()) {
            changeTilda(currentSymbolIndex);
            currentSymbolIndex++;
            return State.Q6;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q6() {
        currentOutputString.insert(0, secondTilda + ": ~").append('~');
        result.add(currentOutputString.toString());
        currentOutputString = new StringBuilder();
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            currentOutputString.append('a');
            return State.Q2;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        if (currentSymbolIsTilda()) {
            changeTilda(currentSymbolIndex);
            currentSymbolIndex++;
            return State.Q6;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    State q7() {
        currentOutputString = new StringBuilder();
        if (currentSymbolIsA()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        if (currentSymbolIsB()) {
            currentSymbolIndex++;
            return State.Q7;
        }
        if (currentSymbolIsTilda()) {
            changeTilda(currentSymbolIndex);
            currentSymbolIndex++;
            return State.Q1;
        }
        throw new RuntimeException("Illegal character in input string");
    }

    public static State terminate(Parser parser) {
        return null;
    }

    private boolean currentSymbolIsB() {
        return data.charAt(currentSymbolIndex) == 'b';
    }

    private boolean currentSymbolIsA() {
        return data.charAt(currentSymbolIndex) == 'a';
    }

    private boolean currentSymbolIsTilda() {
        return data.charAt(currentSymbolIndex) == '~';
    }

    private void changeTilda(int newTilda) {
        secondTilda = firstTilda;
        firstTilda = newTilda;
    }
}
