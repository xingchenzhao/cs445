package cs445.a2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.EmptyStackException;


/**
 * This class uses two stacks to evaluate an infix arithmetic expression from an
 * InputStream. It should not create a full postfix expression along the way; it
 * should convert and evaluate in a pipelined fashion, in a single pass.
 */
public class InfixExpressionEvaluator {
    // Tokenizer to break up our input into tokens
    StreamTokenizer tokenizer;

    // Stacks for operators (for converting to postfix) and operands (for
    // evaluating)
    StackInterface<Character> operatorStack;
    StackInterface<Double> operandStack;
    StringBuilder postfix = new StringBuilder();

    /**
     * Initializes the evaluator to read an infix expression from an input
     * stream.
     *
     * @param input the input stream from which to read the expression
     */
    int CurrentToken = 0;
    int OperandToken = 1;
    int OperatorToken = 2;
    int OpenBracketToken = 3;
    int CloseBracketToken = 4;

    boolean isThereBracket = false;
    int numOfOpenBracket = 0;
    int numOfCloseBracket = 0;
    boolean isBalance = true;

    public InfixExpressionEvaluator(InputStream input) {
        // Initialize the tokenizer to read from the given InputStream
        tokenizer = new StreamTokenizer(new BufferedReader(
                new InputStreamReader(input)));

        // StreamTokenizer likes to consider - and / to have special meaning.
        // Tell it that these are regular characters, so that they can be parsed
        // as operators
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');

        // Allow the tokenizer to recognize end-of-line, which marks the end of
        // the expression
        tokenizer.eolIsSignificant(true);

        // Initialize the stacks
        operatorStack = new ArrayStack<Character>();
        operandStack = new ArrayStack<Double>();
    }

    /**
     * Parses and evaluates the expression read from the provided input stream,
     * then returns the resulting value
     *
     * @return the value of the infix expression that was parsed
     */
    public Double evaluate() throws InvalidExpressionException {
        // Get the first token. If an IO exception occurs, replace it with a
        // runtime exception, causing an immediate crash.
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Continue processing tokens until we find end-of-line
        while (tokenizer.ttype != StreamTokenizer.TT_EOL) {

            // Consider possible token types
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    // If the token is a number, process it as a double-valued
                    // operand
                    handleOperand((double) tokenizer.nval);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    // If the token is any of the above characters, process it
                    // is an operator

                    handleOperator((char) tokenizer.ttype);
                    break;
                case '(':
                case '{':
                    // If the token is open bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleOpenBracket((char) tokenizer.ttype);
                    break;
                case ')':
                case '}':
                    // If the token is close bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleCloseBracket((char) tokenizer.ttype);
                    break;
                case StreamTokenizer.TT_WORD:
                    // If the token is a "word", throw an expression error
                    throw new InvalidExpressionException("Unrecognized symbol: " +
                            tokenizer.sval);
                case ' ':
                default:
                    // If the token is any other type or value, throw an
                    // expression error
                    throw new InvalidExpressionException("Unrecognized symbol: " +
                            String.valueOf((char) tokenizer.ttype));
            }

            // Read the next token, again converting any potential IO exception
            try {
                tokenizer.nextToken();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Almost done now, but we may have to process remaining operators in
        // the operators stack
        handleRemainingOperators();

        //Check brackets balanced
        isBalanced(numOfOpenBracket, numOfCloseBracket);

        String strPostFix = postfix.toString();
        double value = getValueFromPostFix(strPostFix);
        System.out.println(strPostFix);
        // Return the result of the evaluation
        // TODO: Fix this return statement
        return value;
    }

    /**
     * This method is called when the evaluator encounters an operand. It
     * manipulates operatorStack and/or operandStack to process the operand
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     *
     * @param operand the operand token that was encountered
     */
    void handleOperand(double operand) throws InvalidExpressionException {
        // TODO: Complete this method
        if (CurrentToken != OperandToken) {
            postfix.append(operand);
            postfix.append(" ");
            CurrentToken = OperandToken;
        } else {
            throw new InvalidExpressionException("Consecutive Number:" + operand);
        }
    }

    /**
     * This method is called when the evaluator encounters an operator. It
     * manipulates operatorStack and/or operandStack to process the operator
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     *
     * @param operator the operator token that was encountered
     */
    void handleOperator(char operator) throws InvalidExpressionException {
        // TODO: Complete this method
        if (CurrentToken == 0) {
            throw new InvalidExpressionException("operator cannot be the first in postfix");
        }
        if (operator == '^' || operator == '+' || operator == '-' || operator == '*' || operator == '/') {
            if (CurrentToken != OperatorToken) {
                while (!operatorStack.isEmpty() &&
                        precedenceLevel(operator) <= precedenceLevel(operatorStack.peek())) {
                    postfix.append(operatorStack.peek());
                    postfix.append(" ");
                    operatorStack.pop();
                }
                operatorStack.push(operator);
                CurrentToken = OperatorToken;
            } else {
                throw new InvalidExpressionException("Consecutive operator:" + operator);
            }
        }
    }

    int precedenceLevel(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '^':
                return 2;
            default:
                return -1;
        }
    }

    /**
     * This method is called when the evaluator encounters an open bracket. It
     * manipulates operatorStack and/or operandStack to process the open bracket
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     *
     * @param openBracket the open bracket token that was encountered
     */

    void handleOpenBracket(char openBracket) {
        // TODO: Complete this method
        if (CurrentToken == OperandToken) {
            throw new InvalidExpressionException("There is no operator before open bracket");
        } else if (CurrentToken == CloseBracketToken) {
            throw new InvalidExpressionException("There is no operator between close bracket and open bracket");
        } else if (CurrentToken != OpenBracketToken) {
            operatorStack.push(openBracket);
            CurrentToken = OpenBracketToken;
            isThereBracket = true;
        } else if (CurrentToken == OpenBracketToken) {
            throw new InvalidExpressionException("Consecutive Open Bracket: " + openBracket);
        }
        numOfOpenBracket++;
    }

    /**
     * This method is called when the evaluator encounters a close bracket. It
     * manipulates operatorStack and/or operandStack to process the close
     * bracket according to the Infix-to-Postfix and Postfix-evaluation
     * algorithms.
     *
     * @param closeBracket the close bracket token that was encountered
     */
    void handleCloseBracket(char closeBracket) throws InvalidExpressionException {
        // TODO: Complete this method
        char topOperator;

        if (CurrentToken != CloseBracketToken || numOfOpenBracket > 1) {
            try {

            } catch (EmptyStackException e) {
                throw new InvalidExpressionException("Brackets are not balanced! ");
            }

            topOperator = operatorStack.pop();
            char openBracket = checkRoundOrCurlyBracket(closeBracket);

            while (topOperator != openBracket) {
                postfix.append(topOperator);
                postfix.append(" ");
                try {
                    topOperator = operatorStack.pop();
                    if (topOperator == openBracket) {
                        if (!isPairedBracket(topOperator, closeBracket)) {
                            throw new InvalidExpressionException("Brackets are not balanced! ");
                        }
                    }
                } catch (EmptyStackException e) {
                    throw new InvalidExpressionException("Brackets are not balanced! ");
                }

            }
            CurrentToken = CloseBracketToken;
        }
        numOfCloseBracket++;
    }

    boolean isPairedBracket(char openBracket, char closeBracket) {
        if (openBracket == '(') {
            if (closeBracket == ')') {
                return true;
            } else {
                return false;
            }
        } else if (openBracket == '{') {
            if (closeBracket == '}') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    char checkRoundOrCurlyBracket(char closeBracket) {
        char x = '(';
        if (closeBracket == ')') {
            x = '(';
        } else if (closeBracket == '}') {
            x = '{';
        }
        return x;
    }

    void isBalanced(int num1, int num2) throws InvalidExpressionException {
        if (isThereBracket) {
            if (num1 != num2) {
                throw new InvalidExpressionException("Brackets are not balanced!");
            }
        }
    }


    /**
     * This method is called when the evaluator encounters the end of an
     * expression. It manipulates operatorStack and/or operandStack to process
     * the operators that remain on the stack, according to the Infix-to-Postfix
     * and Postfix-evaluation algorithms.
     */
    void handleRemainingOperators() {
        // TODO: Complete this method

        while (!operatorStack.isEmpty()) {
            char topOperator = operatorStack.pop();
            postfix.append(topOperator);
            postfix.append(" ");
        }
    }

    double getValueFromPostFix(String postfix) throws InvalidExpressionException {
        String[] items = postfix.split(" ");
        Double operandOne;
        Double operandTwo;

        for (int i = 0; i < items.length; i++) {
            try {
                operandStack.push(Double.valueOf(items[i]));
            } catch (NumberFormatException e) {

                try {
                    operandOne = operandStack.pop();
                } catch (EmptyStackException c) {
                    throw new InvalidExpressionException("There is no operand");
                }

                try {

                    operandTwo = operandStack.pop();

                } catch (EmptyStackException v) {
                    throw new InvalidExpressionException("There is no next operand!");
                }
                double result = 1;
                switch (items[i]) {
                    case "+":
                        result = operandOne + operandTwo;
                        operandStack.push(result);
                        break;
                    case "-":
                        //result = operandOne-operandTwo; //Original
                        result = operandTwo - operandOne; //changed
                        operandStack.push(result);
                        break;
                    case "*":
                        result = operandOne * operandTwo;
                        operandStack.push(result);
                        break;
                    case "/":
                        if (operandOne == 0) {
                            throw new InvalidExpressionException("cannot divide by zero!");
                        } else {
                            result = operandTwo / operandOne;
                            operandStack.push(result);
                        }
                        break;
                    case "^":
//                        for (int x = 0; x < operandOne; x++) { //Original
//                            result = result * operandTwo;
//                        }
                        result = Math.pow(operandTwo, operandOne); //changed
                        operandStack.push(result);
                        break;
                    default:
                        break;
                }
            }
        }

        return operandStack.peek();
    }


    /**
     * Creates an InfixExpressionEvaluator object to read from System.in, then
     * evaluates its input and prints the result.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("Infix expression:");
        InfixExpressionEvaluator evaluator =
                new InfixExpressionEvaluator(System.in);
        Double value = null;

        try {
            value = evaluator.evaluate();
        } catch (InvalidExpressionException e) {
            System.out.println("Invalid expression: " + e.getMessage());
        }
        if (value != null) {
            System.out.println(value);
        }
    }

}

