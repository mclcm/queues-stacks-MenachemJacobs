import java.util.Stack;

public class BracketCheck {
    private Stack<Character> bracketStack;
    private final char[] openers = new char[]{'(', '[', '{'};
    private final char[] closers = new char[]{')', ']', '}'};

    public boolean isValid(String s) {
        bracketStack = new Stack<>();

        //look through every char of the string
        for (char c : s.toCharArray()) {
            //if it's an opener add it to the stack
            if (isOpener(c))
                bracketStack.push(c);

            if (isCloser(c)) {
                //if it's a closer, but the stack is empty, or it doesn't match the most recent opener, return false
                if (bracketStack.isEmpty() || !isBracketMatch(bracketStack.peek(), c))
                    return false;
                    //if it's a closer and it matches the most recent opener, pop the stack
                else if (isBracketMatch(bracketStack.peek(), c))
                    bracketStack.pop();
            }
        }

        return bracketStack.isEmpty();
    }

    private boolean isOpener(char c) {
        for (char letter : openers) {
            if (c == letter)
                return true;
        }

        return false;
    }

    private boolean isCloser(char c) {
        for (char letter : closers) {
            if (c == letter)
                return true;
        }

        return false;
    }

    private boolean isBracketMatch(char rBracket, char lBracket) {
        int counter = 0;

        for (char opener : openers) {
            if (opener == rBracket)
                break;
            counter++;
        }
        return counter != openers.length && closers[counter] == lBracket;

//        return switch (rBracket) {
//            case '(' -> lBracket == ')';
//            case '[' -> lBracket == ']';
//            case '{' -> lBracket == '}';
//            default -> false;
//        };
    }
}
