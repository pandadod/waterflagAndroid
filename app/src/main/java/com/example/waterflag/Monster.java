package com.example.waterflag;

public class Monster extends Persos {

    public Monster (String name, int pv, int attack) {
        super (name, pv, attack);
        this.weapon = weaponShuffle();
    }

    public void attackMonster(Heros h) {
        damage(h);
        System.out.println(getName() + " attacks ! ");
        System.out.println(h.getName() + " takes damages ! ");
        System.out.println(h.isKo() ? h.getName() + " is dead" : h.getName() + " is still alive and its life is: " + h.getPv());
    }
}