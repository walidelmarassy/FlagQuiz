package com.example.waleed.flagsquiz.Model;

public class Ranking {
    private int id;
    private int Score;

    public Ranking(int id, int score) {
        this.id = id;
        Score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
