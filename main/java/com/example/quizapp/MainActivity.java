package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private ImageView Image;
    private TextView questionTextView;
    private int correct = 0;
    private int currentQuestionIndex = 0;

    private final Question[] questionBank = new Question[] {
            new Question(R.string.a, true),
            new Question(R.string.b, false),
            new Question(R.string.c, true),
            new Question(R.string.d, true),
            new Question(R.string.e, true),
            new Question(R.string.f, false),
            new Question(R.string.g, true),
            new Question(R.string.h, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        questionTextView= findViewById(R.id.answer_text_view);
        Image = findViewById(R.id.myimage);
        Toast.makeText(this, "Welcome to my Quiz App!!!", Toast.LENGTH_SHORT).show();
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.false_button) {
            checkAnswer(false);
        } else if (viewId == R.id.true_button) {
            checkAnswer(true);
        } else if (viewId == R.id.next_button) {
            if (currentQuestionIndex < 9) {
                currentQuestionIndex = currentQuestionIndex + 1;
                if (currentQuestionIndex == 8) {
                    questionTextView.setText(getString(R.string.correct, correct));
                    nextButton.setVisibility(View.INVISIBLE);
                    prevButton.setVisibility(View.INVISIBLE);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                    if (correct > 3) {
                        questionTextView.setText("CORRECTNESS IS " + correct + " " + "OUT OF 8");
                        Image.setImageResource(R.drawable.f7);
                    } else {
                        Image.setImageResource(R.drawable.sad);
                    }
                } else {
                    updateQuestion();
                }
            }
        } else if (viewId == R.id.prev_button) {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                updateQuestion();
            }
        }
    }


    private void updateQuestion()
    {
        Log.d("Current","onClick: " + currentQuestionIndex);

        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        switch (currentQuestionIndex) {
            case 1:
                Image.setImageResource(R.drawable.f2);
                break;
            case 2:
                Image.setImageResource(R.drawable.f3);
                break;
            case 3:
                Image.setImageResource(R.drawable.f4);
                break;
            case 4:
                Image.setImageResource(R.drawable.f5);
                break;
            case 5:
                Image.setImageResource(R.drawable.f9);
                break;
            case 6:
                Image.setImageResource(R.drawable.f10);
                break;
            case 7:
                Image.setImageResource(R.drawable.f8);
                break;
        }
    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;
        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, toastMessageId,Toast.LENGTH_SHORT).show();
    }
}
