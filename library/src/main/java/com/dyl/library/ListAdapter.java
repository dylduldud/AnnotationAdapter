package com.dyl.library;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dengyulin on 16/6/17.
 */
public abstract class ListAdapter<T> extends android.widget.BaseAdapter implements View.OnClickListener,AnnotationInitView{
    private DBaseAdapter<T> dBaseAdapter;
    public ListAdapter(Context context, List<T> list) {
         dBaseAdapter=new DBaseAdapter(context,ListAdapter.this,list);
    }
    public ListAdapter(DBaseAdapter<T> baseAdapter) {
        dBaseAdapter=baseAdapter;
    }
    public RecyclerViewAdapter convertAdapter(){
        return new RecyclerViewAdapter(dBaseAdapter) {
            @Override
            public void initView(View v,int position,int type) {
                ListAdapter.this.initView(v,position,type);
            }

            @Override
            public int getItemViewType(int position) {
                return ListAdapter.this.getItemViewType(position);
            }
        };
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        int type=getItemViewType(position);
        if (convertView == null) {
            convertView=LayoutInflater.from(dBaseAdapter.getContext()).inflate(dBaseAdapter.getTypeLayoutId(type),null);
            viewHolder=new ViewHolder(convertView,type);
            viewHolder.initField();
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
            if(viewHolder!=null){
                viewHolder.initField();
            }else{
                viewHolder=new ViewHolder(convertView,type);
                viewHolder.initField();
                convertView.setTag(viewHolder);
            }
        }
        initView(convertView, position,type);
        return convertView;
    }


    public void notifyDataSetChanged(List<T> list) {
        setList(list);
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class ViewHolder {
        private SparseArray<View> sparseArray;
        private int type=0;
        public ViewHolder(View v,int type) {
            this.type=type;
            this.sparseArray = dBaseAdapter.getSparseArray(v,type);
        }
        public void initField(){
            dBaseAdapter.initField(sparseArray,type);
        }
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return getData(position);
    }

    @Override
    public int getCount() {
        return getList().size();
    }


    @Override
    public void onClick(View v) {

    }
    public List<T> getList(){
        return dBaseAdapter.getList();
    }
    public void setList(List list) {
        dBaseAdapter.setList(list);
    }
    public T getData(int position){
        return dBaseAdapter.getList().get(position);
    }
    @Override
    public int getViewTypeCount() {
        return dBaseAdapter.getTypeLayoutCount();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
