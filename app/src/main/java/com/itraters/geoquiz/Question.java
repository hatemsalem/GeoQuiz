package com.itraters.geoquiz;

/**
 * Created by hasalem on 10/21/2017.
 */

public class Question
{
    private int textResId;
    private boolean answertTrue;

    public Question(int textResId, boolean answertTrue)
    {
        this.textResId = textResId;
        this.answertTrue = answertTrue;
    }

    public int getTextResId()
    {
        return textResId;
    }

    public void setTextResId(int textResId)
    {
        this.textResId = textResId;
    }

    public boolean isAnswertTrue()
    {
        return answertTrue;
    }

    public void setAnswertTrue(boolean answertTrue)
    {
        this.answertTrue = answertTrue;
    }
}
