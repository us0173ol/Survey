package com.bignerdranch.android.survey;

import android.content.Intent;
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
    private static final int REQUEST_RESULTS = 0;

    private Button mYesButton;
    private Button mNoButton;
    private Button mResultsButton;
    private Button mResetButton;
    private TextView mQuestionTextView;
    private static int yes = 0;
    private static int no = 0;
//display question
    private Question [] mQuestionBank = new Question[]{
            new Question (R.string.question_1),
            //new Question (R.string.question 2),


    };
    //added for future use when there is more than one question
    private int mCurrentIndex = 0;
    //method for updating the question for future use
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedYes){
        boolean answerIsYes = mQuestionBank[mCurrentIndex].isAnswerYes();

        int messageResId = 0;

        if (userPressedYes == answerIsYes){
            messageResId = Integer.parseInt("Yes");

        }else{
            messageResId = Integer.parseInt("No");

        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

//called when activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_survey);
        //what to do when the textview is clicked(nothing yet)
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        //what to do when the yes button is clicked
        mYesButton = (Button) findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //show toast of selection
                Toast.makeText(SurveyActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                yes++;//add to counter for results display
                updateQuestion();
            }
        });
        //what to do when the no button is clicked
        mNoButton = (Button) findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //show toast of selevtion
                Toast.makeText(SurveyActivity.this, "No", Toast.LENGTH_SHORT).show();
                no++;//add to no counter for results display
                updateQuestion();
            }
        });
        //what to do when the results button is clicked
        mResultsButton = (Button) findViewById(R.id.results_button);
        mResultsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int results = 0;
                //show toast of results to survey so far
                Intent i = ResultsActivity.newIntent(SurveyActivity.this,results );
                //Toast.makeText(SurveyActivity.this, "Yes:"+yes+" No:"+no,Toast.LENGTH_LONG).show();
                startActivityForResult(i, REQUEST_RESULTS);
            }
        });
        //what to do when reset button is clicked
        mResetButton = (Button) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                yes=0;//reset yes and no totals back to zero
                no=0;
                //display toast acknowledging that results have been reset
                Toast.makeText(SurveyActivity.this, "Results have been reset", Toast.LENGTH_LONG).show();
            }
        });
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        }
        updateQuestion();
    }
    @Override
    //save all data when the phone or tablet is rotated
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt("Yes:", yes);//save results totals
        savedInstanceState.putInt("No:", no);

    }//tags to be logged to see when each is called
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
