package com.example.waterflag;

public class Heros extends Persos {
    private int nbPotion;
    private int pm;

    public Heros(String name, int pv, int pm, int attack, int imageId) {
        super(name, pv, attack, imageId);
        this.pm = pm;
        this.nbPotion = 3;
        this.weapon = null;


    }

    public int getPm() {
        return this.pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public int getPotion() {
        return this.nbPotion;
    }

    public void setPotion(int nbPotion) {
        this.nbPotion = nbPotion;
    }
}
