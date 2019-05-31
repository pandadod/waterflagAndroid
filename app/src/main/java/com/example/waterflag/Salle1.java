package com.example.waterflag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Salle1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle1);

        SharedPreferences sharedpref = Salle1.this.getSharedPreferences("com.example.waterflag", Context.MODE_PRIVATE);
        TextView tvPseudo = findViewById(R.id.tvpseudo);
        tvPseudo.setText(sharedpref.getString("PSEUDO", ""));
        final ImageView ivBackground = findViewById(R.id.ivBackground);
        final Heros hero = new Heros(tvPseudo.getText().toString(), 4000, 50, 200);
        final Monster gobelin = new Monster("gobelin", 500, 50);
        final TextView tvPv = findViewById(R.id.tvPv);
        tvPv.setText(String.valueOf(hero.getPv()));
        final TextView tvDialog = findViewById(R.id.tvDialog);
        tvDialog.setMovementMethod(new ScrollingMovementMethod());
        tvDialog.append("A goblin appears with a " + gobelin.weaponShuffle()+"\n");


        ImageButton ibPotion = findViewById(R.id.ibPotion);
        ImageButton ibPhysicAttack = findViewById(R.id.ibPa);
        ibPhysicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    hero.damage(gobelin);
                    tvDialog.append(" attacks !\n");
                    tvDialog.append(gobelin.getName() + " takes damages !\n");
                    tvDialog.append(gobelin.isKo() ? gobelin.getName() + " is dead\n" : gobelin.getName() + " is still alive and its life is: " + gobelin.getPv()+"\n");
                    if(!gobelin.isKo()){
                        gobelin.damage(hero);
                        tvDialog.append(gobelin.getName() + " attacks !\n");
                        tvDialog.append(hero.getName() + " takes damages !\n");
                        tvPv.setText(String.valueOf(hero.getPv()));
                        tvDialog.append(hero.isKo() ? hero.getName() + " is dead\n" : hero.getName() + " is still alive and its life is: " + hero.getPv()+"\n");
                    }
                    else {
                        ivBackground.setImageResource(R.drawable.dead);                    }
            }
        });
        ImageButton ibMagicAttack = findViewById(R.id.ibMA);
        ibMagicAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDialog.append("A goblin appears with a " + gobelin.weaponShuffle()+"\n");
                gobelin.setPv(500);
            }
        });





        //test sur les potions à supprimer ? voir avec la méthode dé la classe Heros
        final TextView tvNbPotion = findViewById(R.id.tvNbPotion);
        tvNbPotion.setText(String.valueOf(3));

        ibPotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nbPotion = Integer.parseInt(tvNbPotion.getText().toString());

                if(nbPotion > 0){
                    hero.setPv(hero.getPv() + 1500);
                    tvPv.setText(String.valueOf(hero.getPv()));
                    tvNbPotion.setText(String.valueOf(nbPotion - 1));
                    tvDialog.append("You take a potion\n");
                    tvDialog.append("still get "+ tvNbPotion.getText()+" potion(s)\n");

                }
                else {
                    tvNbPotion.setText(String.valueOf(0));
                    tvDialog.append("you don't have any potion\n");
                }
            }
        });
    }
}
