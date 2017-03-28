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
        TestAdapter testAdapter=new TestAdapter(this,list);
        lv.setAdapter(testAdapter);
        //
//        ContrastTestAdapter ada=new ContrastTestAdapter(this,list);


        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        TestTypeAdapter adapter=new TestTypeAdapter(this,list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter.convertAdapter());

    }
}
