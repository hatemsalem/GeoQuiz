package com.itraters.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    private Button trueButton;
    private Button falseButton;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        trueButton=(Button)findViewById(R.id.true_button);
        falseButton=(Button)findViewById(R.id.false_button);
        toast=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        trueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toast.setText(R.string.correct_toast);
                toast.show();
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toast.setText(R.string.incorrect_toast);
                toast.show();

            }
        });
    }
}
