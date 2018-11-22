package com.example.hasee.intentdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SUBACTIVITY = 1;
    private TextView txt2;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =(Button)findViewById(R.id.button);
        txt2 = (TextView)findViewById(R.id.textView2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,SubActivity.class);
                startActivityForResult(intent,SUBACTIVITY);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SUBACTIVITY:
                if(resultCode==RESULT_OK){
                    Uri uriData = data.getData();
                    txt2.setText(uriData.toString());
                }
                break;
        }
    }
}
