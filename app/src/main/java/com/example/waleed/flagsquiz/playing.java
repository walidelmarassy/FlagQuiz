package com.example.waleed.flagsquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.waleed.flagsquiz.DbHelper.DbHelper;
import com.example.waleed.flagsquiz.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class playing extends AppCompatActivity implements View.OnClickListener {
    public static long Interval=1000000;
    public static long Timeout=7000;
    int progressvalue=0;
    CountDownTimer countDownTimer;
    List<Question>questionplay=new ArrayList<>();
    DbHelper db;

int index=0,score=0,thisquestion=0,totalquestion,correctanswer;
String mode="";
ProgressBar progressBar;
ImageView imageView;
TextView textQuestion,textscore;
Button btnA,btnB,btnC,btnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        //Get Dta from main Activity
        Bundle extra=getIntent().getExtras();// need to understand this also
        if (extra!=null)

            mode=extra.getString("Mode");
        db=new DbHelper(this);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        imageView=(ImageView) findViewById(R.id.question_flag);
        textQuestion=(TextView) findViewById(R.id.question_text);
        textscore=(TextView) findViewById(R.id.Scorebtn);


        btnA=(Button)findViewById(R.id.AnswerA_btn);
        btnB=(Button)findViewById(R.id.AnswerB_btn);
        btnC=(Button)findViewById(R.id.AnswerC_btn);
        btnD=(Button)findViewById(R.id.AnswerD_btn);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);











    }

    @Override
    protected void onResume() {
        super.onResume();



        questionplay=db.getQuestionMode(mode);

        totalquestion=questionplay.size();
        countDownTimer=new CountDownTimer(Interval,Timeout) {
            @Override
            public void onTick(long millisUntilFinished) {


                progressBar.setProgress(progressvalue);
                progressvalue++;


            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                showQuestion(++index);//need to understand this

            }
        };
        showQuestion(index);

    }

    private void showQuestion(int index) {
        if (index<totalquestion){
            thisquestion++;
            textQuestion.setText(String.format("%d/%d",thisquestion,totalquestion));
            progressBar.setProgress(0);
            progressvalue=0;
            int ImageId=this.getResources().getIdentifier(questionplay.get(index).getImage().toLowerCase(),"drawable",getPackageName());
            imageView.setBackgroundResource(ImageId);
            btnA.setText(questionplay.get(index).getAnswerA());
            btnB.setText(questionplay.get(index).getAnswerB());
            btnC.setText(questionplay.get(index).getAnswerc());
            btnD.setText(questionplay.get(index).getAnswerD());
            countDownTimer.start();




        }
        else {
            Intent intent=new Intent(this,Done.class);
            Bundle datasend=new Bundle();
            datasend.putInt("Score",score);
            datasend.putInt("Total",totalquestion);
            datasend.putInt("CorrectAnswer",correctanswer);
            intent.putExtras(datasend);
            startActivity(intent);
            finish();



        }

    }

    @Override
    public void onClick(View v) {
        countDownTimer.cancel();
        if (index<totalquestion){
            Button clickedbutton=(Button)v;
            if (clickedbutton.getText().equals(questionplay.get(index).getCorrectanswer()))
            {
                score+=10;
                correctanswer++;
                showQuestion(++index);


            }
            else


                showQuestion(++index);
                textscore.setText(String.format("%d",score));





        }

    }
}
