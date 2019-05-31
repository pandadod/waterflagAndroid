package com.example.waterflag;

public class Monster extends Persos {

    public Monster (String name, int pv, int attack, int imageId ) {
        super (name, pv, attack, imageId );
        this.weapon = weaponShuffle();
    }

    public void attackMonster(Heros h) {
        damage(h);
    }
}