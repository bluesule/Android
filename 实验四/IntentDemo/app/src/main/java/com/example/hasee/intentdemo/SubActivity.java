package com.example.hasee.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SubActivity extends Activity {
    private EditText editText;
    private  Button back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

       editText =(EditText)findViewById(R.id.editText2);
       back = (Button)findViewById(R.id.button2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriString =editText.getText().toString();
                Uri data = Uri.parse(uriString);
                Intent result = new Intent(null,data);
                setResult(RESULT_OK,result);
                finish();
            }
        });
    }
}
