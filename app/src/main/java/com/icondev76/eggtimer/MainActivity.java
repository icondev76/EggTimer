package com.icondev76.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Button button;
    SeekBar seekBar;

    int setTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView= (ImageView)findViewById(R.id.imageView);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(120);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setTime=progress;
                textView.setText(String.valueOf(setTime)+"s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }

        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(setTime*1000+100,1000 ){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        textView.setText(String.valueOf(millisUntilFinished/1000)+"s");
                        button.setEnabled(false);
                        seekBar.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        textView.setText("0s");
                        MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                        mediaPlayer.start();
                        button.setEnabled(true);
                        seekBar.setEnabled(true);
                    }
                }.start();
            }
        });
    }
}
