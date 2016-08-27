package com.cpb.katerynalevytska.memorizergame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by katerynalevytska on 8/13/16.
 */
public class StartApp extends Activity {

    TextView title;

    Button start;
    Button settings;
    Button records;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        title = (TextView) findViewById(R.id.memoTitleId);

        Typeface type = Typeface.createFromAsset(getAssets(), "my-font.ttf");
        title.setTypeface(type);


        start = (Button) findViewById(R.id.btnStart);
        settings = (Button) findViewById(R.id.btnSettings);
        records = (Button) findViewById(R.id.btnRecords);
        exit = (Button) findViewById(R.id.btnExit);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startSettings();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void startSettings () {
        Intent i = new Intent(this, Settings.class);
        startActivity (i);
    }
}
