package com.example.iMoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Entry> data;//创建私有的Bean类的data
    private final Context context;

    public MyAdapter(List<Entry> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();//获取data的长度
    }

    @Override
    public Entry getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {//获取id
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {//防止view不停的新建，
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }
        TextView textView = view.findViewById(R.id.tv);
        textView.setText(data.get(i).title);//系统会去R文件里面找type类型的值匹配String值
        textView = view.findViewById(R.id.entry_field1);
        textView.setText(data.get(i).field1);
        textView = view.findViewById(R.id.entry_field2);
        textView.setText(data.get(i).field2);

        return view;
    }

    public void setData(List<Entry> data) {
        this.data = data;
        notifyDataSetChanged();
    }

}

