package com.example.waleed.flagsquiz.Model;

public class Question {
    private int id;
    private String image;
    private String AnswerA;
    private String AnswerB;
    private String Answerc;
    private String AnswerD ;
    private String correctanswer;

    public Question(int id, String image, String answerA, String answerB, String answerc, String answerD, String correctanswer) {
        this.id = id;
        this.image = image;
        AnswerA = answerA;
        AnswerB = answerB;
        Answerc = answerc;
        AnswerD = answerD;
        this.correctanswer = correctanswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String answerB) {
        AnswerB = answerB;
    }

    public String getAnswerc() {
        return Answerc;
    }

    public void setAnswerc(String answerc) {
        Answerc = answerc;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String answerD) {
        AnswerD = answerD;
    }

    public String getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }
}

