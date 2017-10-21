package com.itraters.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
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
        setContentView(R.layout.activity_quiz);
        trueButton=(Button)findViewById(R.id.true_button);
        falseButton=(Button)findViewById(R.id.false_button);
        nextButton=(Button) findViewById(R.id.next_button);
        questionTextView=(TextView) findViewById(R.id.question_text_view);
        toast=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        updateQuestion();
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

                updateQuestion();
            }
        });
    }
    private  void updateQuestion()
    {
        currentIndex=++currentIndex%questionBank.length;
        questionTextView.setText(questionBank[currentIndex].getTextResId());
    }
    private void checkAnswer(boolean userPressedTrue)
    {

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
}
