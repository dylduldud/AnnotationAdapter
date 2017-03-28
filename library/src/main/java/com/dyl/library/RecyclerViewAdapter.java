package com.dyl.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dengyulin on 2017/1/5.
 */

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter implements AnnotationInitView{

    private DBaseAdapter<T> baseAdapter;
    public RecyclerViewAdapter(Context context, List<T> list) {
       baseAdapter= new DBaseAdapter(context,RecyclerViewAdapter.this,list);
    }
    public ListAdapter convertAdapter(){
        return new ListAdapter(baseAdapter) {
            @Override
            public void initView(View v, int position,int type) {
                RecyclerViewAdapter.this.initView(v,position,type);
            }

            @Override
            public int getItemViewType(int position) {
                return RecyclerViewAdapter.this.getItemViewType(position);
            }
        };
    }
    public RecyclerViewAdapter(DBaseAdapter baseAdapter) {
        this.baseAdapter= baseAdapter;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(baseAdapter.getContext()).inflate(baseAdapter.getTypeLayoutId(viewType),parent,false);
        return new ViewHolder(v,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).initField();
        initView(holder.itemView, position,((ViewHolder) holder).viewType);
    }



    public void addHeadView(View v) throws NoSuchMethodException {
        throw new NoSuchMethodException("\"addHeadView\" The current version is not implemented");
    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> viewHolder;
        private int viewType;
        public ViewHolder(View itemView,int viewType) {
            super(itemView);
            this.viewType=viewType;
            viewHolder=baseAdapter.getSparseArray(itemView,viewType);
        }
        public void initField(){
            baseAdapter.initField(viewHolder,viewType);
        }

    }

    public List<T> getList(){
        return baseAdapter.getList();
    }
    public void setList(List list) {
        baseAdapter.setList(list);
    }
    public T getData(int position){
        return baseAdapter.getList().get(position);
    }

    @Override
    public int getItemCount() {
        return baseAdapter.getList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
