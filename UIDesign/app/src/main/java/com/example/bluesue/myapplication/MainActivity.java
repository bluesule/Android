package com.example.bluesue.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);
        final Button bt1 = (Button) findViewById(R.id.button);
        final ListView lv1 = (ListView)findViewById(R.id.lv1);
        List<String> list=new ArrayList<String>();
        list.add("班级"+"                   "+"学号"+"                   "+"姓名");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv1.setAdapter(adapter);
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                EditText bj,xh,xm;
            bj=(EditText)findViewById(R.id.editText1);
            xh=(EditText)findViewById(R.id.editText2);
            xm=(EditText)findViewById(R.id.editText3);
            ArrayAdapter temp_adp=(ArrayAdapter) lv1.getAdapter();
            temp_adp.add(bj.getText().toString()+"   "+xh.getText().toString()+"     "+xm.getText().toString());
            }
        });

        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (id > 0)
                {
                    PopupMenu popup = new PopupMenu(MainActivity.this, view);
                    popup.getMenuInflater().inflate(R.menu.popupmenu1, popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                    {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu1:
                                    ArrayAdapter temp_adp = (ArrayAdapter) lv1.getAdapter();
                                    temp_adp.remove(temp_adp.getItem(position));
                                    return true;
                                    default:
                                        return false;
                            }
                        }
                    });
                }
                return true;
            }
        });

    }
}
