package com.example.waterflag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Salle1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle1);

        SharedPreferences sharedpref = Salle1.this.getSharedPreferences("com.example.waterflag", Context.MODE_PRIVATE);
        TextView tvPseudo = findViewById(R.id.tvpseudo);
        tvPseudo.setText(sharedpref.getString("PSEUDO", ""));

        final Heros hero = new Heros(tvPseudo.getText().toString(), 4000, 50, 200, R.drawable.hero);
        final Persos[] p = {monster()};

        final ProgressBar lifeBarHero = findViewById(R.id.lifebar);
        lifeBarHero.setMax(hero.getPv());
        lifeBarHero.setProgress(4000, true);

        final ProgressBar magicBar = findViewById(R.id.magicbar);
        magicBar.setMax(hero.getPm());
        magicBar.setProgress(hero.getPm(), true);

        final ProgressBar lifeBarMonstre = findViewById(R.id.lifeMonsterBar);
        lifeBarMonstre.setMax(p[0].getPv());
        lifeBarMonstre.setProgress(p[0].getPv(), true);

        final TextView tvNbPotion = findViewById(R.id.tvNbPotion);
        tvNbPotion.setText(String.valueOf(hero.getPotion()));

        final TextView tvPm = findViewById(R.id.tvPm);
        tvPm.setText(String.valueOf(hero.getPm()));

        final GifImageView ivMonster = findViewById(R.id.ivMonstre);
        ivMonster.setImageResource(p[0].getImageId());

        final GifImageView ivHero = findViewById(R.id.ivperso);
        ivHero.setImageResource(R.drawable.hero);

        final TextView tvPvMonstre = findViewById(R.id.tvPvMonstre);
        tvPvMonstre.setText(String.valueOf(p[0].getPv()));

        final TextView tvNameMonstre = findViewById(R.id.tvNameMonstre);
        tvNameMonstre.setText(p[0].getName());

        final TextView tvWeaponMonstre = findViewById(R.id.tvWeaponMonstre);
        tvWeaponMonstre.setText(p[0].getWeapon());

        final ConstraintLayout salle1Layout = findViewById(R.id.salle1_layout);

        final ImageButton ibMagicAttack = findViewById(R.id.ibMA);
        final ImageButton ibPotion = findViewById(R.id.ibPotion);
        final ImageButton ibPhysicAttack = findViewById(R.id.ibPa);

        final TextView tvPv = findViewById(R.id.tvPv);
        tvPv.setText(String.valueOf(hero.getPv()));

        final Button buttonNext = findViewById(R.id.btNext);

        final ToggleButton tbAxe = findViewById(R.id.tbAxe);
        final ToggleButton tbStaff = findViewById(R.id.tbStaff);
        final ToggleButton tbSword = findViewById(R.id.tbSword);


        tbSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbSword.isChecked()) {
                    tbSword.setChecked(true);
                    hero.setWeapon("sword");
                    tbAxe.setChecked(false);
                    tbStaff.setChecked(false);
                } else {
                    tbSword.setChecked(false);
                    hero.setWeapon(null);
                }
            }
        });
        tbAxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbAxe.isChecked()) {
                    tbAxe.setChecked(true);
                    hero.setWeapon("axe");
                    tbSword.setChecked(false);
                    tbStaff.setChecked(false);
                } else {
                    tbAxe.setChecked(false);
                    hero.setWeapon(null);
                }
            }
        });
        tbStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbStaff.isChecked()) {
                    tbStaff.setChecked(true);
                    hero.setWeapon("staff");
                    tbSword.setChecked(false);
                    tbAxe.setChecked(false);
                } else {
                    tbStaff.setChecked(false);
                    hero.setWeapon(null);
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p[0] = monster();
                lifeBarMonstre.setMax(p[0].getPv());
                lifeBarMonstre.setProgress(p[0].getPv(), true);
                tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                tvNameMonstre.setText(p[0].getName());
                tvWeaponMonstre.setText(p[0].getWeapon());
                ivMonster.setImageResource(p[0].getImageId());
                buttonNext.setVisibility(View.INVISIBLE);
            }
        });

        ibPhysicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getWeapon() == null) {
                    Snackbar.make(salle1Layout, "You must choose a weapon !", Snackbar.LENGTH_SHORT).show();
                } else {
                    if (p[0].getName().equals("Baba Yaga")) {
                        Snackbar.make(salle1Layout, "Wizard can't receive physical damage !", Snackbar.LENGTH_SHORT).show();
                    } else {
                        hero.damage(p[0]);
                        lifeBarMonstre.setProgress(p[0].getPv(), true);
                        tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                        if (p[0].isKo()) {
                            p[0].setPv(0);
                            lifeBarMonstre.setProgress(p[0].getPv(), true);
                            tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                            if (p[0].getName().equals("Mestiefiel")) {
                                ivMonster.setImageResource(R.drawable.you_win);
                                buttonNext.setVisibility(View.INVISIBLE);
                            } else {
                                buttonNext.setVisibility(View.VISIBLE);
                                ivMonster.setImageResource(R.drawable.you_win);
                            }
                        }
                    }

                    if (!p[0].isKo()) {
                        if (p[0].getName().equals("Mestiefiel")) {
                            int index;
                            Random r = new Random();
                            index = r.nextInt((1 - 0) + 1) + 0;
                            if (index == 0) {
                                p[0].damage(hero);
                                lifeBarHero.setProgress(hero.getPv(), true);

                            } else {
                                p[0].damage(hero);
                                p[0].damage(hero);
                                lifeBarHero.setProgress(hero.getPv(), true);
                            }
                        }
                        p[0].damage(hero);
                        lifeBarHero.setProgress(hero.getPv(), true);
                        tvPv.setText(String.valueOf(hero.getPv()));
                        if (hero.isKo()) {
                            ivMonster.setImageResource(R.drawable.game_over);
                            hero.setPv(0);
                            tvPv.setText(String.valueOf(hero.getPv()));
                            ibPhysicAttack.setEnabled(false);
                            ibMagicAttack.setEnabled(false);
                            ibPotion.setEnabled(false);
                            tbAxe.setEnabled(false);
                            tbStaff.setEnabled(false);
                            tbSword.setEnabled(false);
                        }
                    }
                }
            }
        });

        ibMagicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getPm() >= 5) {
                    p[0].setPv(p[0].getPv() - hero.getAttack() * 2);
                    hero.setPm(hero.getPm() - 5);
                    magicBar.setProgress(hero.getPm(), true);
                    tvPm.setText(String.valueOf(hero.getPm()));
                    lifeBarMonstre.setProgress(p[0].getPv(), true);
                    tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                    if (p[0].isKo()) {
                        p[0].setPv(0);
                        lifeBarMonstre.setProgress(p[0].getPv(), true);
                        tvPvMonstre.setText(String.valueOf(p[0].getPv()));
                        if (p[0].getName().equals("Mestiefiel")) {
                            ivMonster.setImageResource(R.drawable.you_win);
                            buttonNext.setVisibility(View.INVISIBLE);
                        } else {
                            buttonNext.setVisibility(View.VISIBLE);
                            ivMonster.setImageResource(R.drawable.you_win);
                        }
                    }
                    if (!p[0].isKo()) {

                        if (p[0].getName().equals("Mestiefiel")) {
                            int index;
                            Random r = new Random();
                            index = r.nextInt((1 - 0) + 1) + 0;
                            if (index == 0) {
                                hero.setPv(hero.getPv() - p[0].getAttack());
                                lifeBarHero.setProgress(hero.getPv(), true);
                            } else {
                                hero.setPv(hero.getPv() - p[0].getAttack());
                                hero.setPv(hero.getPv() - p[0].getAttack());
                                lifeBarHero.setProgress(hero.getPv(), true);
                            }
                        }
                        hero.setPv(hero.getPv() - p[0].getAttack());
                        lifeBarHero.setProgress(hero.getPv(), true);
                        tvPv.setText(String.valueOf(hero.getPv()));
                        if (hero.isKo()) {
                            ivMonster.setImageResource(R.drawable.game_over);
                            hero.setPv(0);
                            tvPv.setText(String.valueOf(hero.getPv()));
                            ibPhysicAttack.setEnabled(false);
                            ibMagicAttack.setEnabled(false);
                            ibPotion.setEnabled(false);
                            tbAxe.setEnabled(false);
                            tbStaff.setEnabled(false);
                            tbSword.setEnabled(false);
                        }

                    }
                } else {
                    Snackbar.make(salle1Layout, "You don't have enough MP", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        ibPotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hero.getPotion() > 0) {
                    hero.setPv(hero.getPv() + 1500);
                    if (hero.getPv() > 4000) {
                        lifeBarHero.setMax(hero.getPv());
                    }
                    lifeBarHero.setProgress(hero.getPv(), true);
                    hero.setPotion(hero.getPotion() - 1);
                    tvPv.setText(String.valueOf(hero.getPv()));
                    tvNbPotion.setText(String.valueOf(hero.getPotion()));
                } else {
                    Snackbar.make(salle1Layout, "You don't have potion !", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Persos monster() {
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
