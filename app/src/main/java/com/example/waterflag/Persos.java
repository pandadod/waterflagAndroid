package com.example.waterflag;

import java.util.Random;

public abstract class Persos {
    protected String weapon;
    protected int imageId;
    private int pv;
    private int attack;
    private String name;


    public Persos(String name, int pv, int attack, int imageId) {
        this.imageId = imageId;
        this.name = name;
        this.pv = pv;
        this.attack = attack;
        this.weapon = weaponShuffle();
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPv() {
        return this.pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public String getWeapon() {
        return this.weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public boolean isKo() {
        return getPv() <= 0;
    }

    public String weaponShuffle() {
        int index;
        Random r = new Random();
        index = r.nextInt((2 - 0) + 1);
        switch (index) {
            case 0:
                weapon = "staff";
                break;
            case 1:
                weapon = "axe";
                break;
            case 2:
                weapon = "sword";
                break;
        }
        return weapon;
    }

    public void damage(Persos p) {

        switch (this.weapon) {
            case "sword":
                if (p.weapon.equals("axe")) {
                    p.setPv(p.getPv() - this.getAttack() * 2);
                } else if (p.weapon.equals("staff")) {
                    p.setPv(p.getPv() - (this.getAttack() / 2));
                } else {
                    p.setPv(p.getPv() - this.getAttack());
                }
                break;
            case "axe":
                if (p.weapon.equals("staff")) {
                    p.setPv(p.getPv() - this.getAttack() * 2);
                } else if (p.weapon.equals("sword")) {
                    p.setPv(p.getPv() - (this.getAttack() / 2));
                } else {
                    p.setPv(p.getPv() - this.getAttack());
                }
                break;
            case "staff":
                if (p.weapon.equals("sword")) {
                    p.setPv(p.getPv() - this.getAttack() * 2);
                } else if (p.weapon.equals("axe")) {
                    p.setPv(p.getPv() - (this.getAttack() / 2));
                } else {
                    p.setPv(p.getPv() - this.getAttack());
                }
                break;
        }
    }
}
