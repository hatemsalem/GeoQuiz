package com.itraters.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity
{
    private static final String TAG="CheatActivity";
    private static final String EXTRA_ANSWER_KEY="com.itraters.geoguiz.answerKey";
    private static final String EXTRA_ANSWER_SHOWN_KEY="com.itraters.geoquiz.answerShown";
    private Button showAnswerButton;
    private TextView answerTextView;
    private boolean answer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        showAnswerButton=(Button) findViewById(R.id.showAnswerButton);
        answerTextView=(TextView) findViewById(R.id.answerTextView);
        answer=getIntent().getBooleanExtra(EXTRA_ANSWER_KEY,false);
        showAnswerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(answer)
                {
                    answerTextView.setText(R.string.true_button);
                }
                else
                {
                    answerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }

        });
    }

    private void setAnswerShownResult(boolean shown)
    {
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN_KEY,shown);
        setResult(RESULT_OK,data);
    }


    public static Intent newIntent(Context packageCtx,boolean answer)
    {
        Intent intent=new Intent(packageCtx,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_KEY,answer);
        return intent;
    }
    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN_KEY,false);
    }
}
