package com.example.waleed.flagsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.waleed.flagsquiz.DbHelper.DbHelper;

public class Done extends AppCompatActivity {
    Button btnTryAgain;
    TextView textscore,texttotalquestion;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        DbHelper db=new DbHelper(this);
        btnTryAgain=(Button)findViewById(R.id.tryagain);
        textscore=(TextView)findViewById(R.id.totalscore);
        texttotalquestion=(TextView)findViewById(R.id.totalquestions);
        progressBar=(ProgressBar)findViewById(R.id.progress);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Get data from playing
        Bundle extra=getIntent().getExtras();
        if (extra!=null)
        {
            int score=extra.getInt("Score");
            int totalquestion=extra.getInt("Total");
            int correctAnswer=extra.getInt("CorrectAnswer");
            textscore.setText(String.format("Score:%d",score));
            texttotalquestion.setText(String.format("passed:%d/%d",correctAnswer,totalquestion));
            progressBar.setMax(totalquestion);
            progressBar.setProgress(correctAnswer);
            //insert score in data base
            db.insertScore(score);






        }
    }
}
