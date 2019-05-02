package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow , 1 = red , 2 = empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},
            {1,5,9},{3,5,7}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        Log.i("TAG", counter.getTag().toString());  //logging tag

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

        counter.setTranslationY(-1500);

        gameState[tappedCounter] = activePlayer;

        if(activePlayer == 0){
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }


        counter.animate().translationYBy(1500).setDuration(300);

        for (int[] winningPosition: winningPositions
             ) {
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
            gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){

                Button btn = findViewById(R.id.restart);

                btn.setVisibility(View.VISIBLE);

                gameActive = false;

                String winner = "";

                if(activePlayer == 1){
                    winner = "Yellow!";
                }else
                    winner = "Red!";

                Toast.makeText(this, winner+ " has won",Toast.LENGTH_LONG).show();




            }
        }
      }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button restartButton = findViewById(R.id.restart);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button btn = (Button) v;
                btn.setVisibility(View.INVISIBLE);

                android.support.v7.widget.GridLayout grid = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
                for(int i=0; i<grid.getChildCount(); i++) {
                    ImageView counter = (ImageView) grid.getChildAt(i);
                    counter.setImageDrawable(null);

                }

                for (int i = 0; i < 10 ; i++) {
                    gameState[i] = 2;
                }

                activePlayer = 0;

                gameActive = true;

            }
        };
        restartButton.setOnClickListener(btnListener);
    }


}
