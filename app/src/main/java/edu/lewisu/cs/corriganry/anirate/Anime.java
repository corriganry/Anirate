package edu.lewisu.cs.corriganry.anirate;

public class Anime {
    private String uid;
    private String animeName;
    private String comment;
    private String favoriteCharacter;
    private int recommendation;
    private int rating;

    public Anime(String uid, String animeName, String comment, String favoriteCharacter, int recommendation, int rating){
        this.uid = uid;
        this.animeName = animeName;
        this.comment = comment;
        this.favoriteCharacter = favoriteCharacter;
        this.recommendation = recommendation;
        this.rating = rating;
    }

    public Anime(String uid) {
        this.uid = uid;
    }

    public Anime() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFavoriteCharacter() {
        return favoriteCharacter;
    }

    public void setFavoriteCharacter(String favoriteCharacter) {
        this.favoriteCharacter = favoriteCharacter;
    }

    public int getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(int recommendation) {
        this.recommendation = recommendation;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
