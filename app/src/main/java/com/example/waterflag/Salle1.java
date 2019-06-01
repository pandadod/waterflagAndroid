package com.example.waterflag;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class Salle1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle1);
        SharedPreferences sharedpref = Salle1.this.getSharedPreferences("com.example.waterflag", Context.MODE_PRIVATE);
        TextView tvPseudo = findViewById(R.id.tvpseudo);
        tvPseudo.setText(sharedpref.getString("PSEUDO", ""));
        final Heros hero = new Heros(tvPseudo.getText().toString(), 4000, 50, 200, R.drawable.fond);
        final ImageView ivBackground = findViewById(R.id.ivMonstre);
        final TextView tvPvMonstre = findViewById(R.id.tvPvMonstre);
        final TextView tvNameMonstre = findViewById(R.id.tvNameMonstre);
        final TextView tvWeaponMonstre = findViewById(R.id.tvWeaponMonstre);
        final ToggleButton tbAxe = findViewById(R.id.tbAxe);
        final ToggleButton tbStaff = findViewById(R.id.tbStaff);
        final ConstraintLayout salle1Layout = findViewById(R.id.salle1_layout);
        final ToggleButton tbSword = findViewById(R.id.tbSword);
        tbSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbSword.isChecked()){
                    tbSword.setChecked(true);
                    hero.setWeapon("sword");
                    tbAxe.setChecked(false);
                    tbStaff.setChecked(false);
                }
                else {
                    tbSword.setChecked(false);
                    hero.setWeapon(null);
                }
            }
        });
        tbAxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbAxe.isChecked()){
                    tbAxe.setChecked(true);
                    hero.setWeapon("axe");
                    tbSword.setChecked(false);
                    tbStaff.setChecked(false);
                }
                else {
                    tbAxe.setChecked(false);
                    hero.setWeapon(null);
                }
            }
        });
        tbStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbStaff.isChecked()){
                    tbStaff.setChecked(true);
                    hero.setWeapon("staff");
                    tbSword.setChecked(false);
                    tbAxe.setChecked(false);
                }
                else {
                    tbStaff.setChecked(false);
                    hero.setWeapon(null);
                }
            }
        });

        final Persos[] p = {monstre()};
        tvPvMonstre.setText(String.valueOf(p[0].getPv()));
        tvNameMonstre.setText(p[0].getName());
        tvWeaponMonstre.setText(p[0].getWeapon());
        ivBackground.setImageResource(p[0].getImageId());

        final ImageButton ibMagicAttack = findViewById(R.id.ibMA);

        final TextView tvPv = findViewById(R.id.tvPv);
        tvPv.setText(String.valueOf(hero.getPv()));
        final ImageButton ibPotion = findViewById(R.id.ibPotion);
        final ImageButton ibPhysicAttack = findViewById(R.id.ibPa);

        ibPhysicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getWeapon()==null) {
                    Snackbar.make(salle1Layout, "You must choose a weapon !", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    hero.damage(p[0]);
                    tvPvMonstre.setText(String.valueOf(p[0].getPv()));

                    if (p[0].isKo()) {
                        p[0].setPv(0);
                        tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                    }
                    if (!p[0].isKo()) {
                        if(p[0].equals(Boss.class)) {
                            int index;
                            Random r = new Random();
                            index = r.nextInt((1 - 0) + 1) + 0;
                            if(index == 0) {
                                p[0].damage(hero);
                            }
                            else {
                                p[0].damage(hero);
                                p[0].damage(hero);
                            }
                        }
                        p[0].damage(hero);


                        tvPv.setText(String.valueOf(hero.getPv()));
                        if (hero.isKo()) {
                            hero.setPv(0);
                            tvPv.setText(String.valueOf(hero.getPv()));
                            ibPhysicAttack.setEnabled(false);
                            ibMagicAttack.setEnabled(false);
                            ibPotion.setEnabled(false);
                        } else {

                        }

                    } else {
                        ivBackground.setImageResource(R.drawable.victory);
                    }
                }

            }
        });

        ibMagicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p[0] = monstre();
                tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                tvNameMonstre.setText(p[0].getName());
                tvWeaponMonstre.setText(p[0].getWeapon());
                ivBackground.setImageResource(p[0].getImageId());
            }
        });


        //test sur les potions à supprimer ? voir avec la méthode dé la classe Heros
        final TextView tvNbPotion = findViewById(R.id.tvNbPotion);
        tvNbPotion.setText(String.valueOf(3));

        ibPotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nbPotion = Integer.parseInt(tvNbPotion.getText().toString());

                if (nbPotion > 0) {
                    hero.setPv(hero.getPv() + 1500);
                    tvPv.setText(String.valueOf(hero.getPv()));
                    tvNbPotion.setText(String.valueOf(nbPotion - 1));


                } else {
                    tvNbPotion.setText(String.valueOf(0));

                }
            }
        });

    }
    public Persos monstre() {
        Random r = new Random();
        int index = r.nextInt((10 - 0) + 1) + 0;
        if (index < 5) {
            Monster gobelin = new Monster("gobelin", 500, 50, R.drawable.monstre);
            return gobelin;
        } else if (index >= 5 && index <= 8) {
            Mage merlin = new Mage("Baba Yaga", 500, 50, 200, R.drawable.mage);
            return merlin;
        } else {
            Boss ganon = new Boss("Mestiefiel", 3000, 400, R.drawable.boss);
            return ganon;
        }
    }


}
