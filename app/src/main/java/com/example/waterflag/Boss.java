package com.example.waterflag;

import java.util.Random;

public class Boss extends Persos {

    public Boss (String name, int pv, int attack) {
        super (name, pv, attack);
        this.weapon = weaponShuffle();
    }

    //TODO : changer la methode pour afficher les textes
    //Methode attaque boss:
    public void attackBoss(Heros h) {
        int index;
        Random r = new Random();
        index = r.nextInt((1 - 0) + 1) + 0;
        if(index == 0) {
            damage(h);
            System.out.println(getName() + " attacks ! ");
            System.out.println(h.getName() + " takes damages ! ");
            System.out.println(h.isKo() ? h.getName() + " is dead" : h.getName() + " is still alive and its life is: " + h.getPv());
        }
        else {
            damage(h);
            damage(h);
            System.out.println(getName() + " gets a critical attack ! ");
            System.out.println(h.getName() + " takes damages ! ");
            System.out.println(h.isKo() ? h.getName() + " is dead: GAME OVER " : h.getName() + " is still alive and its life is: " + h.getPv());
        }
    }
}
