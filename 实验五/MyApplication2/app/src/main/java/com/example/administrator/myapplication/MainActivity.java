package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    static  private int x = 0;
    private TextView mint;
    private TextView sec;
    private TextView milli;
    private Button start;
    private Button reset;
    private Button pause;
    private long timeusedinsec;
    private boolean isstop = false;
    private Handler mHandler = new Handler() {
        /*
         * edit by yuanjingchao 2014-08-04 19:10
         */
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // 添加更新ui的代码
                    if (!isstop) {
                        updateView();
                        mHandler.sendEmptyMessageDelayed(1, 10);
                    }
                    break;
                case 0:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);
        initViews();
    }
    private void initViews() {
        mint = (TextView) findViewById(R.id.mint);
        sec = (TextView) findViewById(R.id.sec);
        milli = (TextView)findViewById(R.id.milli);
        reset = (Button) findViewById(R.id.reset);
        start = (Button) findViewById(R.id.start);
        pause = (Button)findViewById(R.id.pause);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                mint.setText("00");
                sec.setText("00");
                milli.setText("00");
                timeusedinsec=0;
                isstop=true;
                x=0;
            }
        });
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessage(1);
                isstop = false;
            }
        });
        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.sendEmptyMessage(0);
                isstop = true;
            }
        });
    }
    private void updateView() {
        if(x<60){
            int milliminute = x;
            if (milliminute < 10)
                milli.setText("0" + milliminute);
            else
                milli.setText("" + milliminute);
            x += 1;
        }
        else{
        timeusedinsec += 1;
        int minute = (int) (timeusedinsec / 60)%60;
        int second = (int) timeusedinsec%60;

        if (minute < 10)
            mint.setText("0" + minute);
        else
            mint.setText("" + minute);
        if (second < 10)
            sec.setText("0" + second);
        else
            sec.setText("" + second);
            x=0;

        }
    }
}
