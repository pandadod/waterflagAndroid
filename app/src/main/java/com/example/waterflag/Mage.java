package com.example.waterflag;

public class Mage extends Persos {
    private int pm;
    public Mage (String name, int pv, int pm, int attack) {
        super (name, pv, attack);
        this.pm = pm;
        this.weapon = weaponShuffle();
    }
    public int getPm() {
        return this.pm;
    }
    public void setPm(int pm) {
        this.pm = pm;
    }
    //TODO : changer la methode pour retourner un string
    public void attackMage(Heros h) {
        if(this.getPm() > 5){
            this.setPm(this.getPm() - 5);
            damage(h);
            System.out.println(getName() + " attacks ! ");
            System.out.println(h.getName() + " takes damages ! ");
            System.out.println(h.isKo() ? h.getName() + " is dead" : h.getName() + " is still alive and its life is: " + h.getPv());
        }
        else {
            System.out.println(getName() + " hasn't enough MP, it's your chance !");
        }
    }
}
