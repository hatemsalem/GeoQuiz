package com.itraters.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    private static final String TAG="QuizActivity";
    private static final String INDEX_KEY="INDEX";
    private static final int REQUEST_CODE_CHEAT=0;
    private Button trueButton;
    private Button falseButton;
    private Button cheatButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;
    private TextView apiTextView;
    private Question[] questionBank= new Question[]{
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };
    private int currentIndex=-1;
    private boolean isCheater=false;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
            currentIndex=savedInstanceState.getInt(INDEX_KEY)-1;
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        trueButton=(Button) findViewById(R.id.true_button);
        falseButton=(Button)findViewById(R.id.false_button);
        cheatButton=(Button) findViewById(R.id.cheatButton);
        nextButton=(ImageButton) findViewById(R.id.next_button);
        prevButton=(ImageButton) findViewById(R.id.prev_button);
        questionTextView=(TextView) findViewById(R.id.question_text_view);
        apiTextView=(TextView) findViewById(R.id.apiTextView);
        toast=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        nextQuestion();
        trueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                nextQuestion();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                prevQuestion();
            }
        });
        questionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nextQuestion();
            }
        });
        cheatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(CheatActivity.newIntent(QuizActivity.this,questionBank[currentIndex].isAnswertTrue()),REQUEST_CODE_CHEAT);
            }
        });
        apiTextView.setText("API Level :"+Build.VERSION.SDK_INT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK&&requestCode==REQUEST_CODE_CHEAT&&data!=null)
        {
            isCheater=CheatActivity.wasAnswerShown(data);
        }
    }

    private  void nextQuestion()
    {
        currentIndex=++currentIndex%questionBank.length;
        updateAnswerButtons();
    }
    private  void prevQuestion()
    {
        currentIndex--;
        if(currentIndex<0)
            currentIndex=questionBank.length-1;
        updateAnswerButtons();
    }
    private void checkAnswer(boolean userPressedTrue)
    {
        questionBank[currentIndex].setAnswered(true);

        int msgResId;
        if (isCheater)
        {
            msgResId=R.string.judgement_toast;
        }
        else if(questionBank[currentIndex].isAnswertTrue()==userPressedTrue)
        {
            msgResId=R.string.correct_toast;
        }
        else
        {
            msgResId=R.string.incorrect_toast;
        }
        updateAnswerButtons();
        toast.setText(msgResId);
        toast.show();

    }
    private void updateAnswerButtons()
    {
        if(questionBank[currentIndex].isAnswered())
        {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
        }
        else
        {
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);
        }
        questionTextView.setText(questionBank[currentIndex].getTextResId());
        isCheater=false;

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG,"onStart() Called");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState() called");
        savedInstanceState.putInt(INDEX_KEY,currentIndex);

    }
}
