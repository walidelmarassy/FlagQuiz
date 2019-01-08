package com.example.waleed.flagsquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.waleed.flagsquiz.Common.CustomAdapter;
import com.example.waleed.flagsquiz.DbHelper.DbHelper;
import com.example.waleed.flagsquiz.Model.Ranking;

import java.util.List;

public class Score extends AppCompatActivity {
  ListView lstview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lstview=(ListView)findViewById(R.id.listRanking);
        DbHelper dbHelper=new DbHelper(this);
        List<Ranking>rankings=dbHelper.getRanking();
        if (rankings.size()>0)
        {
            CustomAdapter adapter=new CustomAdapter(this,rankings);
            lstview.setAdapter(adapter);
        }

    }
}
