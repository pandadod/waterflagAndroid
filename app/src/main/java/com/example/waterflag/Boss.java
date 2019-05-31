package com.example.waterflag;

import java.util.Random;

public class Boss extends Persos {

    public Boss (String name, int pv, int attack, int imageId) {
        super (name, pv, attack, imageId );
        this.weapon = weaponShuffle();
    }

    //TODO : changer la methode pour afficher les textes
    //Methode attaque boss:
    public void attackBoss(Heros h) {

    }
}
