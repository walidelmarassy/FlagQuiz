package com.example.waleed.flagsquiz.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.waleed.flagsquiz.Common.Common;
import com.example.waleed.flagsquiz.Model.Question;
import com.example.waleed.flagsquiz.Model.Ranking;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "MyDB.db";
    private static String DB_path = "";
    private SQLiteDatabase mDataBase;
    private Context mContext = null;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        DB_path = context.getApplicationInfo().dataDir + "/databases/";
        openDataBase();
        this.mContext = context;
    }





    public void openDataBase() {
        String mypath = DB_path + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);//need to understand it also

    }

    public void copyDataBase() throws IOException {
        try {
            InputStream myinputs = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_path + DB_NAME;
            OutputStream myoutput = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myinputs.read(buffer)) > 0)
                myoutput.write(buffer, 0, length);
            myoutput.flush();// cant understand it
            myoutput.close();
            myinputs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String mypath = DB_path + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {
            e.printStackTrace();

        }
        if (tempDB != null)
            tempDB.close();
            return tempDB != null?true:false;// need to understand this



    }
    public void CreateDataBase() throws IOException {
        boolean isDBExists=checkDataBase();
        if (isDBExists){

        }
        else {
            this.getReadableDatabase();
            try {
                copyDataBase();

            }
            catch (IOException e){
                e.printStackTrace();

            }
        }


    }

    @Override
    public synchronized void close() {
        if (mDataBase!=null)
            mDataBase.close();
        super.close();
    }

    @Override
        public void onCreate (SQLiteDatabase sqLiteDatabase){

        }

        @Override
        public void onUpgrade (SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion){


        }
        public List<Question> getAllQuestions(){
        List<Question>listQuestion=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
            Cursor c;
            try {
                c=db.rawQuery("SELECT*FROM Question Order By Random()",null);
                if (c==null)return null;
                c.moveToFirst();
                do {
                    int Id= c.getInt(c.getColumnIndex("ID"));
                    String Image= c.getString(c.getColumnIndex("Image"));
                    String AnswerA= c.getString(c.getColumnIndex("AnserA"));
                    String AnswerB= c.getString(c.getColumnIndex("AnswerB"));
                    String AnswerC= c.getString(c.getColumnIndex("Answerc"));
                    String AnswerD= c.getString(c.getColumnIndex("AnserD"));
                    String CorrectAnswer= c.getString(c.getColumnIndex("CorrectAnswe"));
                    Question question=new Question(Id,Image,AnswerA,AnswerB,AnswerC,AnswerD,CorrectAnswer);
                    listQuestion.add(question);
                }
                while (c.moveToNext());
                    c.close();

            }
            catch (Exception e){
                e.printStackTrace();
            }
            db.close();
            return listQuestion;




    }
    public List<Question> getQuestionMode(String mode){
        List<Question>listQuestion=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c;
        int limit=0;
        if (mode.equals(Common.Mode.Easy.toString()))
            limit=30;
        else if (mode.equals(Common.Mode.Medium.toString()))
            limit=50;
        else if (mode.equals(Common.Mode.Hard.toString()))
            limit=100;
        else if (mode.equals(Common.Mode.Hardest.toString()))
            limit=200;


        try {
            c=db.rawQuery(String.format("SELECT*FROM Question Order By Random()LIMIT %d",limit),null);
            if (c==null)return null;
            c.moveToFirst();
            do {
                int Id= c.getInt(c.getColumnIndex("ID"));
                String Image= c.getString(c.getColumnIndex("Image"));
                String AnswerA= c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB= c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC= c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD= c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer= c.getString(c.getColumnIndex("CorrectAnswer"));
                Question question=new Question(Id,Image,AnswerA,AnswerB,AnswerC,AnswerD,CorrectAnswer);
                listQuestion.add(question);
            }
            while (c.moveToNext());
            c.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listQuestion;




    }
    //insert score to ranking table
    public void insertScore(int Score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue=new ContentValues();
        contentValue.put("Score",Score);
        db.insert("Ranking",null,contentValue);

    }
    //Get score and sort ranking
    public List<Ranking>getRanking()
    {
        List<Ranking>listRanking=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c;
        try {
            c=db.rawQuery("Select* From Ranking ORDER By Score Desc;",null);
            if (c==null)return null;
            c.moveToNext();
            do {
                int Id=c.getInt(c.getColumnIndex("Id"));
                int Score=c.getInt(c.getColumnIndex("Score"));
                Ranking ranking=new Ranking(Id,Score);
                listRanking.add(ranking);

            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;

    }
    }

