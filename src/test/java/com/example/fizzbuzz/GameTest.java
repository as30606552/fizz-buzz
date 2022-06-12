package com.example.fizzbuzz;

import com.example.fizzbuzz.game.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameTest {

    @Autowired
    private Game game;

    @Test
    void correctValues() {
        var sequence = List.of(
                "1", "2", "fizz", "4", "buzz",
                "fizz", "7", "8", "fizz", "buzz",
                "11", "fizz", "13", "14", "fizz buzz",
                "16"
        );

        for (var value : sequence) {
            try {
                assertTrue(game.turn(value), "Incorrect response (false) to the value: " + value);
                assertTrue(game.isActive(), "The game must be active with correct turns");
            } catch (Exception e) {
                fail("The game did not take the correct value: " + value);
            }
        }
    }

    @Test
    void incorrectValue() {
        var value = "abc";

        assertFalse(game.turn(value), "Incorrect response (true) to the value: " + value);
        assertFalse(game.isActive(), "The game must be deactivated after an incorrect turn");
    }
}
