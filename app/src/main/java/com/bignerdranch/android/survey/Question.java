package com.bignerdranch.android.survey;

/**
 * Created by miked on 9/11/2016.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerYes;

    public int getTextResId(){ return mTextResId; }

    public void setTextResId(int textResId){ mTextResId = textResId; }

    public boolean isAnswerYes() { return  mAnswerYes; }

    public void setAnswerYes(boolean answerYes) { mAnswerYes = answerYes;}

    public Question(int textResId){

        mTextResId = textResId;

        mAnswerYes = isAnswerYes();
    }
}
