package com.bignerdranch.android.survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.string.no;
import static android.R.string.yes;

public class ResultsActivity extends AppCompatActivity {

    private static final String EXTRA_RESULTS_SHOWN = "com.bignerdranch.android.results";

    private TextView mResultsTextView;
    private Button mResults;

    public static Intent newIntent (Context packageContext, int results){
        Intent i = new Intent(packageContext, ResultsActivity.class);
        i.putExtra(EXTRA_RESULTS_SHOWN, results);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mResults = (Button) findViewById(R.id.results_button);
        mResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mResultsTextView.setText("Yes: "+ yes + "No: " + no);

            }
        });


    }
}
