package com.example.hasee.myapplication;

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

    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);
        final Button bt1 = (Button) findViewById(R.id.button);
        final ListView lv1 = (ListView)findViewById(R.id.lv1);
        final EditText bj=(EditText)findViewById(R.id.editText1);
        final EditText xh=(EditText)findViewById(R.id.editText2);
        final EditText xm=(EditText)findViewById(R.id.editText3);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        List<String> list=new ArrayList<String>();
        list.add("ID" + "         " + "班级"+"                   "+"学号"+"                   "+"姓名");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv1.setAdapter(adapter);
        Student[] stu = dbAdapter.queryAllData();
        for(int i =0 ; i< stu.length;i++)
       {
           list.add(stu[i].id + "  " +stu[i].classes + "  "+ stu[i].sno + "  "+ stu[i].sname);
       }
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Student ss = new Student();
                String sno = xh.getText().toString();
                String sname = xm.getText().toString();
                String classes = bj.getText().toString();
                ss.setSno(sno);
                ss.setSname(sname);
                ss.setClasses(classes);
                ArrayAdapter temp_adp=(ArrayAdapter) lv1.getAdapter();
                temp_adp.add(dbAdapter.insert(ss) + "  " + classes + "  " + sno + "   " + sname);
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
