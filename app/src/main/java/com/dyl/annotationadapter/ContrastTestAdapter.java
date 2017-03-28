package com.dyl.annotationadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengyulin on 16/6/18.
 */
public class ContrastTestAdapter extends android.widget.BaseAdapter{
    protected List<String> list=new ArrayList<>();
    protected LayoutInflater mInflater;
    protected Context context;
    public ContrastTestAdapter(Context context,List<String> list){
        this.list=list;
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        ViewHolder1 viewHolder1=null;
        ViewHolder2 viewHolder2=null;
        if(convertView==null){


            if(getItemViewType(position)==0){
                convertView=mInflater.inflate(android.R.layout.simple_list_item_1,null);
                viewHolder=new ViewHolder();
                viewHolder.tv=(TextView)convertView.findViewById(android.R.id.text1);
                convertView.setTag(viewHolder);
            }else if(getItemViewType(position)==1){
                convertView=mInflater.inflate(R.layout.toast_view,null);
                viewHolder1=new ViewHolder1();
                viewHolder1.tv1=(TextView)convertView.findViewById(R.id.message);
                convertView.setTag(viewHolder1);
            }else if(getItemViewType(position)==2){
                convertView=mInflater.inflate(R.layout.toast_view1,null);
                viewHolder2=new ViewHolder2();
                viewHolder2.tv2=(TextView)convertView.findViewById(R.id.message);
                convertView.setTag(viewHolder2);
            }

        }else{

            if(getItemViewType(position)==0){
                viewHolder=(ViewHolder)convertView.getTag();

            }else if(getItemViewType(position)==1){
                viewHolder1=(ViewHolder1)convertView.getTag();
            }else if(getItemViewType(position)==2){
                viewHolder2=(ViewHolder2)convertView.getTag();
            }
        }
        if(getItemViewType(position)==0){
            String name=list.get(position);
            viewHolder.tv.setText(name);
        }else if(getItemViewType(position)==1){
            String name=list.get(position);
            viewHolder1.tv1.setText(name);
        }else if(getItemViewType(position)==2){
            String name=list.get(position);
            viewHolder2.tv2.setText(name);
        }


        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        TextView tv;
    }
    static class ViewHolder1{
        TextView tv1;
    }
    static class ViewHolder2{
        TextView tv2;
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

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
