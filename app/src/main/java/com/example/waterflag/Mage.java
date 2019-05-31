package com.example.waterflag;

public class Mage extends Persos {
    private int pm;
    public Mage (String name, int pv, int pm, int attack, int imageId ) {
        super (name, pv, attack, imageId );
        this.pm = pm;
        this.weapon = weaponShuffle();
    }
    public int getPm() {
        return this.pm;
    }
    public void setPm(int pm) {
        this.pm = pm;
    }
    public void attackMage(Heros h) {
        if(this.getPm() > 5){
            this.setPm(this.getPm() - 5);
            damage(h);
        }
        else {

        }
    }
}
