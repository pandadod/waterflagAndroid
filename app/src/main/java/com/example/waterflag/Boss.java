package com.example.waterflag;

public class Boss extends Persos {

    public Boss(String name, int pv, int attack, int imageId) {
        super(name, pv, attack, imageId);
        this.weapon = weaponShuffle();
    }
}
