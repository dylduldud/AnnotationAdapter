package com.dyl.library;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dengyulin on 2017/3/28.
 */

public class DBaseAdapter<T> {
    protected Context context;
    protected Object obj;
    private SparseArray<HashMap<Field,Integer>> childViewAnnotation =new SparseArray<>();
    private int[] contents = new int[]{};
    private List list;
    public Context getContext(){
        return context;
    }

    public DBaseAdapter(Context context, Object obj, List<T> list) {
        this.context = context;
        this.obj=obj;
        this.list=list;
        contents=obj.getClass().getAnnotation(AdapterContentView.class).value();
        for (Field field : MyReflectUtil.getFields(obj.getClass())) {
            if (View.class.isAssignableFrom(field.getType())) {
                AdapterChildView annotation = field.getAnnotation(AdapterChildView.class);
                int[] type=annotation.type();
                for(int i=0;i<type.length;i++){
                    HashMap<Field, Integer> fieldIntegerHashMap = childViewAnnotation.get(type[i]);
                    if(fieldIntegerHashMap==null){
                        fieldIntegerHashMap = new HashMap<>();
                        fieldIntegerHashMap.put(field,annotation.value());
                        childViewAnnotation.put(type[i],fieldIntegerHashMap);
                    }else{
                        fieldIntegerHashMap.put(field,annotation.value());
                    }

                }
            }
        }
    }

    public SparseArray<View> getSparseArray(View v,int type){
        HashMap<Field, Integer> fieldIntegerHashMap = childViewAnnotation.get(type);
        Iterator<Map.Entry<Field, Integer>> iterator = fieldIntegerHashMap.entrySet().iterator();
        SparseArray<View> sp=new SparseArray<>();
        while(iterator.hasNext()){
            Map.Entry<Field, Integer> next = iterator.next();
            sp.put(next.getValue(),v.findViewById(next.getValue()));
        }
        return sp;
    }


    public void initField(SparseArray<View> sp,int type){
        HashMap<Field, Integer> fieldIntegerHashMap = childViewAnnotation.get(type);
        Iterator<Map.Entry<Field, Integer>> iterator = fieldIntegerHashMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Field, Integer> next = iterator.next();
            try {
                Field f=next.getKey();
                f.setAccessible(true);
                f.set(obj,sp.get(next.getValue()));
            } catch (IllegalAccessException e) {
            }
        }
    }

    public int getTypeLayoutId(int type){
        return contents[type];
    }

    public int getTypeLayoutCount(){
        return contents.length;
    }

    public List<T> getList(){
        return list;
    }

    public void setList(List list) {
        this.list=list;
    }
}
