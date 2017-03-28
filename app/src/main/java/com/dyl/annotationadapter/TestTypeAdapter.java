package com.dyl.annotationadapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dyl.library.AdapterChildView;
import com.dyl.library.AdapterContentView;
import com.dyl.library.ListAdapter;

import java.util.List;

/**
 * Created by dengyulin on 2017/3/28.
 */

@AdapterContentView(value={android.R.layout.simple_list_item_1,R.layout.toast_view,R.layout.toast_view1})
public class TestTypeAdapter extends ListAdapter {
    @AdapterChildView(value =android.R.id.text1,type = {0})
    private TextView tv;
    @AdapterChildView(value =R.id.message,type = {1,2})
    private TextView message;
    public TestTypeAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public void initView(View v, int position,int type) {
        if(type==0){
            String name= (String) getData(position);
            tv.setText(name);
        }else if(type==1){
            String name= (String) getData(position);
            message.setText(name+":"+type);
        }else if(type==2){
            String name= (String) getData(position);
            message.setText(name+":"+type);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position%7==0){
            return  1;
        }else if(position%7==3){
            return 2;
        }else{
            return 0;
        }
    }
}
