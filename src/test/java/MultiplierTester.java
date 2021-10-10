

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

class MultiplierTester {

    Multiplier multiplier;

    @BeforeEach
    void setUp() {
        multiplier = new Multiplier();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testMultiply() {
        assertEquals(20, multiplier.multiply(4, 5), "Regular multiplication should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, multiplier.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, multiplier.multiply(5, 0), "Multiple with zero should be zero");
    }
}