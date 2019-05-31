package com.example.waterflag;

import java.util.Scanner;
public class Heros extends Persos {
    private int nbPotion;
    private int pm;

    public Heros (String name, int pv, int pm, int attack, int imageId) {
        super (name, pv, attack, imageId);
        this.pm = pm;
        this.nbPotion = 3;
        this.weapon=null;


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

    public void weaponChoice() {
        Scanner myChoice = new Scanner(System.in);
        int index;
        index = Integer.parseInt(myChoice.next());
        if(index == 1) {
            setWeapon("staff");
        }
        else if(index == 2) {
            setWeapon("axe");
        }
        else if(index == 3) {
            setWeapon("sword");
        }
    }

    //methode of hero fight against monsters
    public void attack(Persos m) {

        Scanner myChoice = new Scanner(System.in);
        //Choice
        int value;
        value = Integer.parseInt(myChoice.next());
        //attaque de base héro
        if(value == 1){
            //cas particulier du mage
            if(m.getClass().equals(Mage.class)) {
            }
            else {
                damage(m);
            }
        }
        //attaque de magie
        else if(value == 2) {
            if(this.getPm() > 5){
                this.setPm(this.getPm() - 5);
                damage(m);
                damage(m);
            }
            else {
                //TODO : avertir qu'il n'y a plus de MP
            }
        }
        //popo
        else if(value == 3) {
            if(this.getPotion() > 0){
                this.setPv(this.getPv() + 1500);
                this.setPotion(this.getPotion() - 1);
            }
            else {
                System.out.println("You haven't enough potion, skip your turn");
            }
        }
        //cheat code
        else if(value == 0){
            m.setPv(m.getPv() - getAttack()*5);
            System.out.println(getName() + " attacks ! ");
            System.out.println(m.getName() + " takes damages but you are a cheater ! ");
            System.out.println(m.isKo() ? m.getName() + " is dead" : m.getName() + " is still alive and its life is: " + m.getPv());
        }
        //Punition
        else {
            System.out.println("Bad cheater ! It's not the good number. Skip your Turn boy ! ");
        }
    }
}
