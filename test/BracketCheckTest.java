import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BracketCheckTest {
    BracketCheck bracketTester;

    @BeforeEach
    void setUp() {
        bracketTester = new BracketCheck();
    }

    @Test
    void bracketCheck_Normal(){
        assertTrue(bracketTester.isValid("()"), "Simple case returned false when it should not have");
        assertFalse(bracketTester.isValid("(}"), "Simple case returned true when it should not have");
    }
}