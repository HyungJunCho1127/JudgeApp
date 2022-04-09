package com.company;

public class Score {
    int competitorRank;
    int competitorPosition;
    int competitorScore;

    public Score(){

    }

    public int getCompetitorRank() {
        return competitorRank;
    }

    public int setCompetitorRank(int rank) {
        competitorRank = rank;
        return rank;
    }

    public int getCompetitorPosition() {
        return competitorPosition;
    }

    public int getCompetitorScore() {
        return competitorScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "Rank :" + competitorRank +
                ", Position :" + competitorPosition +
                ", Score :" + competitorScore +
                '}';
    }

    public Score(int rank, int position, int score){
        competitorRank = rank;
        competitorPosition = position;
        competitorScore = score;

    }


}
