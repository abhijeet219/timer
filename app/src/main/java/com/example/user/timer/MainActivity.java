package com.example.user.timer;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.Period;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;

public class MainActivity extends AppCompatActivity {

    private Timer mytimer;
    int i=0,x=0;
    int j=0;
    int h=0,m=0;
    TimePicker alarmtime;
    Button btn;
    MediaPlayer mediaplayer;
    Button end;
    TextView text;
    EditText dur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaplayer = MediaPlayer.create(MainActivity.this, R.raw.alarm);
        alarmtime=findViewById(R.id.picker);
        btn = findViewById(R.id.btn);
        end=findViewById(R.id.end);
        text=findViewById(R.id.time);
        dur = findViewById(R.id.duration);

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                j=0;
                h=alarmtime.getHour();
                m=alarmtime.getMinute();

                if(dur.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please Input duration",Toast.LENGTH_SHORT).show();
                    Log.d("abc","duration is 0");
                }
                else
                    x = Integer.parseInt(dur.getText().toString());

                mytimer = new Timer();
                mytimer.schedule(new TimerTask() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        TimerTask();
                        Calendar cal = Calendar.getInstance();

                        j+=1;

                        if(h>12)
                            h=h-12;
                        if(h==cal.get(Calendar.HOUR) && m<=cal.get(Calendar.MINUTE) && m+x >=cal.get(Calendar.MINUTE)){
                            mediaplayer.start();
                            Log.d("abc","working");

                        if(m>=(60-x)){
                            if((h+1)==cal.get(Calendar.HOUR) && (m-(60-x))==cal.get(Calendar.MINUTE) ){
                                mediaplayer.stop();
                                Log.d("abc","stopped");
                            }
                        }
                        if(m<(60-x)){
                            if(h==cal.get(Calendar.HOUR) && m+x==cal.get((Calendar.MINUTE)))
                                mediaplayer.stop();
                                Log.d("abc","stopped");
                        }

                        }

                    }

                },0,1000);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.stop();
                Log.d("abc","stopped");
                text.setText(""+j);
            }
        });

    }

    private void TimerTask(){

    }
}
