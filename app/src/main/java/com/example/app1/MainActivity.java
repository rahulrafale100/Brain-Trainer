package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button playAgainBtn;
    ConstraintLayout gameL;
    int active=0;
    ArrayList<Integer> answers=new ArrayList<>();
    int l;
    int score=0;
    int question=0;
    TextView response;
    TextView scoreText;
    TextView timer;
    public void start(View view){
        goButton= (Button) findViewById(R.id.button);
        goButton.setVisibility(View.INVISIBLE);
        gameL.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));

    }
    public void playAgain(View view){
        playAgainBtn.setVisibility(View.INVISIBLE);
        score=0;
        question=0;
        active=0;
        timer.setText("30s");
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(question));
        response.setText("Choose");
        game();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                int p=(int)(l/1000);
                timer.setText(Integer.toString(p)+"s");

            }

            @Override
            public void onFinish() {
                response.setText(" Done!!");
               MediaPlayer play=MediaPlayer.create(getApplicationContext(),R.raw.horn);
                play.start();
                playAgainBtn.setVisibility(View.VISIBLE);
                active=1;
            }
        }.start();

    }
    public void chooseAnswer(View view){
        if(active==0) {
            if (Integer.toString(l).equals(view.getTag().toString())) {
                response.setText("Correct✔");
                score++;
            } else {
                response.setText("Wrong❌");
            }
            question++;
            scoreText.setText(Integer.toString(score) + "/" + Integer.toString(question));
            game();
        }
    }
    public void game(){
       // response.setText("Choose");
        Random rand=new Random();
        int a=rand.nextInt(101);
        int b=rand.nextInt(101);
        TextView sum=findViewById(R.id.sumTextView);
        sum.setText(Integer.toString(a)+"+"+Integer.toString(b));
        l=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==l){
                answers.add(a+b);
            }
            else{
                int wrongAnswer=rand.nextInt(201) ;
                while(wrongAnswer==(a+b)){
                    wrongAnswer=rand.nextInt(201);
                }
                answers.add(wrongAnswer);
            }
        }
        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.button);
        response=findViewById(R.id.responseTextView);
        scoreText=findViewById(R.id.scoreTextView);
         btn1=findViewById(R.id.button1);
         btn2=findViewById(R.id.button2);
         btn3=findViewById(R.id.button3);
         btn4=findViewById(R.id.button4);
         timer=findViewById(R.id.timerTextView);
        playAgainBtn=findViewById(R.id.playAgainButton);
        gameL=findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);
        gameL.setVisibility(View.INVISIBLE);
    }
}