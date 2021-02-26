package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreA=0,scoreB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Aadd3(View view){
        scoreA+=3;
        dispteamA(scoreA);
    }
    public void Aadd2(View view){
        scoreA+=2;
        dispteamA(scoreA);
    }
    public void Aadd1(View view){
        scoreA+=1;
        dispteamA(scoreA);
    }
    public void Badd3(View view){
        scoreB+=3;
        dispteamB(scoreB);
    }
    public void Badd2(View view){
        scoreB+=2;
        dispteamB(scoreB);
    }
    public void Badd1(View view){
        scoreB+=1;
        dispteamB(scoreB);
    }
    public void reset(View view){
        scoreA=0;
        scoreB=0;
        dispteamA(scoreA);
        dispteamB(scoreB);
    }

    public void dispteamA(int scoreA){
        TextView scoreviewA=(TextView) findViewById(R.id.teamscoreA);
        scoreviewA.setText(String.valueOf(scoreA));
    }
    public void dispteamB(int scoreB){
        TextView scoreviewB=(TextView) findViewById(R.id.teamscoreB);
        scoreviewB.setText(String.valueOf(scoreB));
    }
}