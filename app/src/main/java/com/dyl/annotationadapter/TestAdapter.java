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

@AdapterContentView(android.R.layout.simple_list_item_1)
public class TestAdapter extends ListAdapter {
    @AdapterChildView(android.R.id.text1)
    private TextView tv;

    public TestAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public void initView(View v, int position, int type) {
        String name = (String) getData(position);
        tv.setText(name);
    }
}
