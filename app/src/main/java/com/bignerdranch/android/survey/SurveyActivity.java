package com.bignerdranch.android.survey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends AppCompatActivity {
    private static final String TAG = "SurveyActivity";
    private static final String KEY_INDEX = "index";

    private Button mYesButton;
    private Button mNoButton;
    private Button mResultsButton;
    private TextView mQuestionTextView;
    int yes = 0;
    int no = 0;

    private Question [] mQuestionBank = new Question[]{
            new Question (R.string.question_1),
            //new Question (R.string.question 2),


    };
    private int mCurrentIndex = 0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedYes){
        boolean answerIsYes = mQuestionBank[mCurrentIndex].isAnswerYes();

        int messageResId = 0;

        if (userPressedYes == answerIsYes){
            messageResId = Integer.parseInt("Yes");
            yes++;
        }else{
            messageResId = Integer.parseInt("No");
            no++;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_survey);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mYesButton = (Button) findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(SurveyActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
        });

        mNoButton = (Button) findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(SurveyActivity.this, "No", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
        });
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
