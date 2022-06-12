package com.example.fizzbuzz.controller;

import com.example.fizzbuzz.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@RestController
@Scope("session")
@RequestMapping("/simple")
public class SimpleGameController {

    private final Game game;

    @Autowired
    public SimpleGameController(Game game) {
        this.game = game;
    }

    @GetMapping("/turn/{turn}")
    public boolean turn(@PathVariable String turn) {
        return game.turn(turn);
    }

    @PostMapping("/restart")
    public void restart() {
        game.restart();
    }
}
