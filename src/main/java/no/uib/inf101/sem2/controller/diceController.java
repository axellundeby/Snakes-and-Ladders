package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.DiceState;

public interface diceController {
    int rollDice();
    DiceState getDiceState();
}
