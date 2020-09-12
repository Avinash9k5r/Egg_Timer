package com.example.eggtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean shouldRun;
    int time;
    Handler handler;
    Runnable run;
    MediaPlayer mediaPlayer;

    public void onClick(View view) {

        final TextView textView = findViewById(R.id.textView);

        final Button button = findViewById(R.id.button);

        final SeekBar seekBar = findViewById(R.id.seekBar);

        if(button.getText().length()==3) {
            handler = new Handler();

            run = new Runnable() {

                @Override
                public void run() {

                    if(time>0) {
                        time = time - 1;
                        int minute = time/60;
                        int second = time%60;

                        int minInt = minute;
                        int secInt = second;

                        String min = Integer.toString(minInt);
                        String sec = Integer.toString(secInt);

                        if(minInt<=9 && secInt<=9) {
                            textView.setText("0"+min+" : 0"+sec);
                        } else if(minInt<=9 && secInt>9) {
                            textView.setText("0"+min+" : "+sec);
                        } else if(minInt>9 && secInt<=9) {
                            textView.setText(min+" : 0"+sec);
                        } else {
                            textView.setText(min+" : "+sec);
                        }
                    } else {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                        mediaPlayer.start();
                        seekBar.setEnabled(true);
                        button.setText("go!");
                        return;
                    }

                    handler.postDelayed(this, 1000);
                }

            };
            handler.post(run);

            seekBar.setEnabled(false);
            button.setText("stop!");

        } else {
            handler.removeCallbacksAndMessages(null);
            seekBar.setEnabled(true);
            button.setText("go!");

        }
    }

    public void onReset(View view) {

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        SeekBar seekBar = findViewById(R.id.seekBar);
        button.setText("go!");
        seekBar.setProgress(0);
        seekBar.setEnabled(true);

        int minInt = 0;
        int secInt = 0;

        String min = Integer.toString(minInt);
        String sec = Integer.toString(secInt);

        if(minInt<=9 && secInt<=9) {
            textView.setText("0"+min+" : 0"+sec);
        } else if(minInt<=9 && secInt>9) {
            textView.setText("0"+min+" : "+sec);
        } else if(minInt>9 && secInt<=9) {
            textView.setText(min+" : 0"+sec);
        } else {
            textView.setText(min+" : "+sec);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);
        SeekBar seekBar = findViewById(R.id.seekBar);


        seekBar.setMin(0);
        seekBar.setMax(180);
        seekBar.setProgress(1);


        int minInt = 0;
        int secInt = 0;

        String min = Integer.toString(minInt);
        String sec = Integer.toString(secInt);

        if(minInt<=9 && secInt<=9) {
            textView.setText("0"+min+" : 0"+sec);
        } else if(minInt<=9 && secInt>9) {
            textView.setText("0"+min+" : "+sec);
        } else if(minInt>9 && secInt<=9) {
            textView.setText(min+" : 0"+sec);
        } else {
            textView.setText(min+" : "+sec);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                time = progress*10;
                int minute = time/60;
                int second = time%60;

                int minInt = minute;
                int secInt = second;

                String min = Integer.toString(minInt);
                String sec = Integer.toString(secInt);

                if(minInt<=9 && secInt<=9) {
                    textView.setText("0"+min+" : 0"+sec);
                } else if(minInt<=9 && secInt>9) {
                    textView.setText("0"+min+" : "+sec);
                } else if(minInt>9 && secInt<=9) {
                    textView.setText(min+" : 0"+sec);
                } else {
                    textView.setText(min+" : "+sec);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}