# AnnotationAdapter
一个基于注解的Adapter 灵活适配ListView GridView RecylerView
<h4>继承ListAdapter或RecylerView</h4>
<p>AdapterContentView注解的value可以接受多个布局，一个布局为一个类别，每个布局会拥有一个type值，默认从0开始</p>
<p>AdapterChildView注解的value表示控件ID，type表示传入布局的下标值，用于确定该控件属于哪个布局，可传入多个</p>
<pre>
  <code>
//注解
@AdapterContentView(value={android.R.layout.simple_list_item_1,R.layout.toast_view,R.layout.toast_view1})
public class ListViewAdapter extends ListAdapter {
    @AdapterChildView(value =android.R.id.text1,type = {0})
    private TextView tv;
    @AdapterChildView(value =R.id.message,type = {1,2})
    private TextView message;
    public ListViewAdapter(Context context, List<String> list) {
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
  </code>
</pre>
<h3>使用</h3>
<pre>
  <code>
ListView lv= (ListView) findViewById(R.id.lv);
RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
rv.setLayoutManager(new LinearLayoutManager(this));
//初始化Adapter
ListViewAdapter adapter=new ListViewAdapter(this,list);
lv.setAdapter(adapter);
//灵活的切换Adapter类型
rv.setAdapter(adapter.convertAdapter());
  </code>
</pre>



