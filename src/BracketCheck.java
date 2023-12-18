import java.util.Stack;

/**
 * A utility class for checking the validity of brackets in a given string.
 */
public class BracketCheck {
    private static Stack<Character> bracketStack;
    private static final char[] openers = new char[]{'(', '[', '{'};
    private static final char[] closers = new char[]{')', ']', '}'};

    /**
     * Checks whether the given string contains properly matched brackets.
     *
     * @param s The input string to be validated.
     * @return {@code true} if the brackets are balanced, {@code false} otherwise.
     */
    public static boolean isValid(String s) {
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

    /**
     * Checks if the given character is an opening bracket.
     *
     * @param c The character to be checked.
     * @return {@code true} if the character is an opening bracket, {@code false} otherwise.
     */
    private static boolean isOpener(char c) {
        for (char letter : openers) {
            if (c == letter)
                return true;
        }

        return false;
    }

    /**
     * Checks if the given character is a closing bracket.
     *
     * @param c The character to be checked.
     * @return {@code true} if the character is a closing bracket, {@code false} otherwise.
     */
    private static boolean isCloser(char c) {
        for (char letter : closers) {
            if (c == letter)
                return true;
        }

        return false;
    }

    /**
     * Checks if the given opening bracket matches the corresponding closing bracket.
     *
     * @param rBracket The opening bracket.
     * @param lBracket The closing bracket to be checked against.
     * @return {@code true} if the brackets match, {@code false} otherwise.
     */
    private static boolean isBracketMatch(char rBracket, char lBracket) {
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
