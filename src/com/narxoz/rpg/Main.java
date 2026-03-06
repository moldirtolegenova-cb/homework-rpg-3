package com.narxoz.rpg;
import com.narxoz.rpg.battle.*;
import com.narxoz.rpg.adapter.*;
import com.narxoz.rpg.hero.*;
import com.narxoz.rpg.enemy.*;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG BATTLE SYSTEM HW3 ===\n");
        BattleEngine engine1 = BattleEngine.getInstance();
        BattleEngine engine2 = BattleEngine.getInstance();
        System.out.println("[Singleton Check]");
        System.out.println("Engine Instance 1: " + engine1.hashCode());
        System.out.println("Engine Instance 2: " + engine2.hashCode());
        System.out.println("Are they the same instance? " + (engine1 == engine2));
        System.out.println();
        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(new Mage("Gandalf the Grey")));
        heroes.add(new HeroCombatantAdapter(new Warrior("Aragorn")));

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(new Goblin()));
        enemies.add(new EnemyCombatantAdapter(new Goblin()));

        System.out.println("[Battle Simulation Starting]");
        engine1.setRandomSeed(42L);
        EncounterResult result = engine1.runEncounter(heroes, enemies);

        System.out.println("\n--- BATTLE LOG ---");
        for (String logEntry : result.getBattleLog()) {
            System.out.println(logEntry);
        }
        System.out.println("\n--- SUMMARY ---");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Total Rounds: " + result.getRounds());
        System.out.println("==============================");
    }
}