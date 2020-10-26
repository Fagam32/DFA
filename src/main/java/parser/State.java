package parser;

import java.util.function.Function;

public enum State {
    Q0(Parser::q0),
    Q1(Parser::q1),
    Q2(Parser::q2),
    Q3(Parser::q3),
    Q4(Parser::q4),
    Q5(Parser::q5),
    Q6(Parser::q6), // q out
    Q7(Parser::q7), // q skip
    Q8(Parser::terminate);

    public final Function<Parser, State> type;

    State(Function<Parser, State> type) {
        this.type = type;
    }
}
