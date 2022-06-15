package com.example.fizzbuzz.game;

import com.example.fizzbuzz.exception.IllegalTurnException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Game {

    private volatile boolean active = true;
    private volatile int state = 0;

    public boolean isActive() {
        return active;
    }

    public synchronized void restart() {
        active = true;
        state = 0;
    }

    public synchronized String current() {
        if (isFizzBuzz()) {
            return "fizz buzz";
        }
        if (isFizz()) {
            return "fizz";
        }
        if (isBuzz()) {
            return "buzz";
        }
        return String.valueOf(state);
    }

    private boolean isFizzBuzz() {
        return state % 15 == 0;
    }

    private boolean isFizz() {
        return state % 3 == 0;
    }

    private boolean isBuzz() {
        return state % 5 == 0;
    }

    public synchronized boolean turn(String turn) {
        if (!isActive()) {
            throw new IllegalTurnException("It is impossible to process a turn - the game is already over");
        }
        state++;
        if (current().equalsIgnoreCase(turn.strip())) {
            return true;
        }
        active = false;
        return false;
    }
}
