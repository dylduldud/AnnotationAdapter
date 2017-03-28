package com.dyl.library;

import android.view.View;

import java.util.List;

/**
 * Created by dengyulin on 2017/3/28.
 */

public interface AnnotationInitView<T> {
    void initView(View v,int position,int type);
    void setList(List<T> list);
    List<T> getList();
    T getData(int position);
    int getItemViewType(int position);
}
