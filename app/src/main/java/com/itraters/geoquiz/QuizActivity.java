package com.itraters.geoquiz;

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
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;
    private Question[] questionBank= new Question[]{
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };
    private int currentIndex=-1;
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
        nextButton=(ImageButton) findViewById(R.id.next_button);
        prevButton=(ImageButton) findViewById(R.id.prev_button);
        questionTextView=(TextView) findViewById(R.id.question_text_view);
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
        updateAnswerButtons();
        if(questionBank[currentIndex].isAnswertTrue()==userPressedTrue)
        {
            toast.setText(R.string.correct_toast);
        }
        else
        {
            toast.setText(R.string.incorrect_toast);
        }
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
