package com.narxoz.rpg.battle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {}

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        System.out.println("Battle engine state reset.");
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result = new EncounterResult();
        int round = 0;
        List<Combatant> sideA = new ArrayList<>(teamA);
        List<Combatant> sideB = new ArrayList<>(teamB);

        while (!sideA.isEmpty() && !sideB.isEmpty()) {
            round++;
            result.addLog("--- Round " + round + " ---");

            executeAttackPhase(sideA, sideB, result);
            if (sideB.isEmpty()) break;
            executeAttackPhase(sideB, sideA, result);
        }

        result.setRounds(round);
        result.setWinner(sideA.isEmpty() ? "Enemies" : "Heroes");
        return result;
    }

    private void executeAttackPhase(List<Combatant> attackers, List<Combatant> defenders, EncounterResult result) {
        for (Combatant attacker : attackers) {
            if (defenders.isEmpty()) break;
            Combatant target = defenders.get(0); 
            int damage = attacker.getAttackPower();
            target.takeDamage(damage);
        
            result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage.");
            if (!target.isAlive()) {
                result.addLog(target.getName() + " has fallen!");
                defenders.remove(target); 
            }
        }
    }
}