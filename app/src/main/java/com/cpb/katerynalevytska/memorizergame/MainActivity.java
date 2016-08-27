package com.cpb.katerynalevytska.memorizergame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mStepScreen;
    private Chronometer mTimeScreen;

    private Integer StepCount;

    private GridView mGrid;
    private com.cpb.katerynalevytska.memorizergame.GridAdapter mGridAdapter;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            mGrid.setNumColumns(9);
        }
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            mGrid.setNumColumns(6);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mGrid = (GridView)findViewById(R.id.field);
        mGrid.setNumColumns(6);
        mGrid.setEnabled(true);

        mGridAdapter = new GridAdapter(this, 6, 6);
        mGrid.setAdapter(mGridAdapter);


        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mGridAdapter.checkOpenCells();

                if(mGridAdapter.openCell(position)){
                    StepCount++;
                    mStepScreen.setText(StepCount.toString());
                }


                if(mGridAdapter.checkGameOver()){
                    mTimeScreen.stop();
                    showGameOver();
                }
            }
        });

        mTimeScreen = (Chronometer) findViewById(R.id.timeview);
        mStepScreen = (TextView) findViewById(R.id.stepview);

        Typeface type = Typeface.createFromAsset(getAssets(), "my-font.ttf");
        mTimeScreen.setTypeface(type);
        mStepScreen.setTypeface(type);

        StepCount = 0;
        mStepScreen.setText(StepCount.toString());
        mTimeScreen.start();
    }

    private void showGameOver() {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);

        alertBox.setTitle("Congratulations");
        String time = mTimeScreen.getText().toString();
        String TextToast = "You win! \nSteps: " + StepCount + " \nTime" + time;
        alertBox.setMessage(TextToast);

        alertBox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alertBox.show();
    }


}
