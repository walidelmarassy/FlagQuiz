package com.example.waleed.flagsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.waleed.flagsquiz.Common.Common;
import com.example.waleed.flagsquiz.DbHelper.DbHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
SeekBar seekBar;
Button playbtn,scorebtn;
TextView textmode;
DbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar)findViewById(R.id.seekbar);
        playbtn=(Button)findViewById(R.id.playbtn);
        scorebtn=(Button)findViewById(R.id.Scorebtn);
        textmode=(TextView)findViewById(R.id.textmode);
        db=new DbHelper(this);
        try {
            db.CreateDataBase();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress==0)
                    textmode.setText(Common.Mode.Easy.toString());
                else if (progress==1)
                    textmode.setText(Common.Mode.Medium.toString());
                else if (progress==2)
                    textmode.setText(Common.Mode.Hard.toString());
                else if (progress==3)
                    textmode.setText(Common.Mode.Hardest.toString());



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Score.class);
                startActivity(intent);
                finish();

            }
        });
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),playing.class);
                intent.putExtra("Mode",getplayMode());//Send Mode to playing page
                startActivity(intent);
                finish();

            }
        });
        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Score.class);
                startActivity(intent);
                finish();
            }
        });




    }

    private  String getplayMode() {

        if (seekBar.getProgress()==0)
            return Common.Mode.Easy.toString();
        else if (seekBar.getProgress()==1)
            return Common.Mode.Medium.toString();
        else if (seekBar.getProgress()==2)
            return Common.Mode.Hard.toString();
        else
            return Common.Mode.Hardest.toString();

        }

}
