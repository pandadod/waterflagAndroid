package com.example.waterflag;

import java.util.Scanner;
public class Heros extends Persos {
    private int nbPotion;
    private int pm;

    public Heros (String name, int pv, int pm, int attack) {
        super (name, pv, attack);
        this.pm = pm;
        this.nbPotion = 3;
        this.weapon = "sword"; //à supprimer

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
        System.out.println("Select your weapon: [1] : staff, [2] : axe or [3] : sword");
        int index;
        index = Integer.parseInt(myChoice.next());
        if(index == 1) {
            setWeapon("staff");
            System.out.println("You pick staff, strong against sword but weak against axe");
        }
        else if(index == 2) {
            setWeapon("axe");
            System.out.println("You pick axe, strong against staff but weak against sword");
        }
        else if(index == 3) {
            setWeapon("sword");
            System.out.println("You pick sword, strong against axe but weak against staff");
        }
    }

    //methode of hero fight against monsters
    public void attack(Persos m) {

        Scanner myChoice = new Scanner(System.in);
        //Choice
        System.out.println("Select your attack: [1] : physical attack, [2] : magical attack or [3] : take a potion");
        int value;
        value = Integer.parseInt(myChoice.next());
        //attaque de base héro
        if(value == 1){
            //cas particulier du mage
            if(m.getClass().equals(Mage.class)) {
                System.out.println(getName() + " attacks ! ");
                System.out.println(m.getName() + " is a wizard and takes no damage ! ");
            }
            else {
                damage(m);
                System.out.println(getName() + " attacks ! ");
                System.out.println(m.getName() + " takes damages ! ");
                System.out.println(m.isKo() ? m.getName() + " is dead" : m.getName() + " is still alive and its life is: " + m.getPv());
            }
        }
        //attaque de magie
        else if(value == 2) {
            if(this.getPm() > 5){
                this.setPm(this.getPm() - 5);
                damage(m);
                damage(m);
                System.out.println(getName() + " attacks ! ");
                System.out.println(m.getName() + " takes damages ! ");
                System.out.println(m.isKo() ? m.getName() + " is dead " : m.getName() + " is still alive and its life is: " + m.getPv());
                System.out.println(" your MP is: " + this.getPm());
            }
            else {
                System.out.println("You haven't enough MP, skip your turn");
            }
        }
        //popo
        else if(value == 3) {
            if(this.getPotion() > 0){
                this.setPv(this.getPv() + 1500);
                this.setPotion(this.getPotion() - 1);
                System.out.println(" you take a potion, your current life is : " + this.getPv());
                System.out.println("Still get " + this.getPotion() + " potion(s)");
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
