package com.dyl.annotationadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List list=new ArrayList();
        for(int i=0;i<30;i++){
            list.add("data:"+i);
        }
        ListView lv= (ListView) findViewById(R.id.lv);
        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        //
//        ContrastTestAdapter ada=new ContrastTestAdapter(this,list);
        ListViewAdapter adapter=new ListViewAdapter(this,list);
        lv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter.convertAdapter());

    }
}
