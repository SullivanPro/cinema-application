package com.example.cinema_application.model;

public class Review {

    private int idReview;
    private String title;
    private String dateReview;
    private int scoreScenario;
    private int scoreProduction;
    private int scoreMusic;
    private String commentary;

    public Review(int idReview, String title, String dateReview, int scoreScenario, int scoreProduction, int scoreMusic, String commentary) {
        this.idReview = idReview;
        this.title = title;
        this.dateReview = dateReview;
        this.scoreScenario = scoreScenario;
        this.scoreProduction = scoreProduction;
        this.scoreMusic = scoreMusic;
        this.commentary = commentary;
    }

    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateReview() {
        return dateReview;
    }

    public void setDateReview(String dateReview) {
        this.dateReview = dateReview;
    }

    public int getScoreScenario() {
        return scoreScenario;
    }

    public void setScoreScenario(int scoreScenario) {
        this.scoreScenario = scoreScenario;
    }

    public int getScoreProduction() {
        return scoreProduction;
    }

    public void setScoreProduction(int scoreProduction) {
        this.scoreProduction = scoreProduction;
    }

    public int getScoreMusic() {
        return scoreMusic;
    }

    public void setScoreMusic(int scoreMusic) {
        this.scoreMusic = scoreMusic;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
