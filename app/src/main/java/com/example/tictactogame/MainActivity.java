package com.example.tictactogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //    player representation:
    //    1 = o
    //    0 = x

    int activePlayer = 0;

    int[] gameState ={2,2,2,2,2,2,2,2,2};
    //    state meaning:
    //    0 = x;
    //    1 = o;
    //    2 = null;
    int [][] winPosition ={
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{6,4,2}
        };


    public  void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());

        if(!gameActive){
            gameResat(view);
        }
        if(gameState[tappedImg] == 2 && gameActive) {//check is that is empty cell
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - tap to pay");

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - tap to pay");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }


        //check if any player has on
        for (int[] winPosi :winPosition){
            if(gameState[winPosi[0]]== gameState[winPosi[1]] &&
                    gameState[winPosi[1]]== gameState[winPosi[2]] &&
                    gameState[winPosi[0]]!= 2)
            {
                //somebody has won -find who
                gameActive = false;
                String winerStr;
                if(gameState[winPosi [0]]==0){
                    winerStr =" X has won";
                }else {
                    winerStr = " O has won";
                }
                //update the satus bar for winner position
                TextView status = findViewById(R.id.status);
                status.setText(winerStr);

            }
        }
    }

    public  void gameResat(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn - tap to pay");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}