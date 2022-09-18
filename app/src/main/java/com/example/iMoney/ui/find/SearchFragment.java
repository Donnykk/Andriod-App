package com.example.iMoney.ui.find;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.iMoney.Entry;
import com.example.iMoney.MainActivity;
import com.example.iMoney.MyAdapter;
import com.example.iMoney.MyDatabaseHelper;
import com.example.iMoney.R;
import com.example.iMoney.databinding.FragmentSearchBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private List<Entry> data = new ArrayList<>();
    private MyAdapter myAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String searchContent=getArguments().getString("search_content");
        EditText searchText=binding.et1;
        ListView entryList= binding.lv;
        searchText.setText(searchContent);

        //处理搜索框焦点问题
        searchText.setOnFocusChangeListener((view,hasfocus)->{
            if(!hasfocus){
                if (view == null || view.getWindowToken() == null) {
                    return;
                }
                InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (im != null) {
                    im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });


        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry t=(Entry) entryList.getAdapter().getItem(position);
                Bundle bundle=new Bundle();
                bundle.putInt("ID",t.id);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);

                //Toast.makeText(getContext(),t.id+" "+t.title,Toast.LENGTH_SHORT).show();
                /*
                Intent intent = new Intent(ListActivity.this,EntryActivity.class);
                Bundle bundle=new Bundle();
                intent.putExtra("entry",t);
                startActivity(intent);

                 */
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(searchText.getText().toString());
                myAdapter.setData(data);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(searchContent.isEmpty()){
            data=getEntry();
            myAdapter=new MyAdapter(data,getContext());
            entryList.setAdapter(myAdapter);//Adapter作用将各种数据以合适的形式显示在View中给用户看

        }else{
            search(searchContent);
            myAdapter=new MyAdapter(data,getContext());
            entryList.setAdapter(myAdapter);//Adapter作用将各种数据以合适的形式显示在View中给用户看
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    @SuppressLint("Range")
    private void search(String s){
        data.clear();

        String dbpath=getActivity().getDatabasePath("knowledge.db").toString();
        SQLiteDatabase mydb=SQLiteDatabase.openDatabase(dbpath,null, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING);


        //System.out.println(dbpath);
        //Cursor cursor=mydb.query("1.db",null,null,null,null,null,null);
        Cursor cursor = mydb.rawQuery("select * from KNOWLEDGE where title like '%"+s+"%'",null);

        boolean[] a=new boolean[50000];

        int count=cursor.getCount();
        if(count>0){
            cursor.moveToFirst();
            for (int i=0; i<count; i++){
                cursor.moveToPosition(i);
                Entry temp=new Entry();
                temp.id=cursor.getInt(cursor.getColumnIndex("num"));
                a[temp.id]=true;
                temp.field1=cursor.getString(cursor.getColumnIndex("field1"));
                temp.field2=cursor.getString(cursor.getColumnIndex("field2"));
                temp.title=cursor.getString(cursor.getColumnIndex("title"));
                temp.content=cursor.getString(cursor.getColumnIndex("content"));
                data.add(temp);
            }
        }

        cursor = mydb.rawQuery("select * from KNOWLEDGE where field1 like '%"+s+"%'",null);
        count=cursor.getCount();
        if(count>0){
            cursor.moveToFirst();
            for (int i=0; i<count; i++){
                cursor.moveToPosition(i);
                Entry temp=new Entry();
                temp.id=cursor.getInt(cursor.getColumnIndex("num"));
                if(a[temp.id]){
                    continue;
                }else {
                    a[temp.id] = true;
                }
                temp.field1=cursor.getString(cursor.getColumnIndex("field1"));
                temp.field2=cursor.getString(cursor.getColumnIndex("field2"));
                temp.title=cursor.getString(cursor.getColumnIndex("title"));
                temp.content=cursor.getString(cursor.getColumnIndex("content"));
                data.add(temp);
            }
        }
        cursor = mydb.rawQuery("select * from KNOWLEDGE where field2 like '%"+s+"%'",null);
        count=cursor.getCount();
        if(count>0){
            cursor.moveToFirst();
            for (int i=0; i<count; i++){
                cursor.moveToPosition(i);
                Entry temp=new Entry();
                temp.id=cursor.getInt(cursor.getColumnIndex("num"));
                if(a[temp.id]){
                    continue;
                }else {
                    a[temp.id] = true;
                }
                temp.field1=cursor.getString(cursor.getColumnIndex("field1"));
                temp.field2=cursor.getString(cursor.getColumnIndex("field2"));
                temp.title=cursor.getString(cursor.getColumnIndex("title"));
                temp.content=cursor.getString(cursor.getColumnIndex("content"));
                data.add(temp);
            }
        }

        //myAdapter.setData(data);
    }

    @SuppressLint("Range")
    public List<Entry> getEntry(){
        List<Entry> entryList=new ArrayList<Entry>();
        String dbpath=getActivity().getDatabasePath("knowledge.db").toString();
        SQLiteDatabase mydb=SQLiteDatabase.openDatabase(dbpath,null, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING);
        Cursor cursor = mydb.rawQuery("select * from KNOWLEDGE",null);

        int count=cursor.getCount();
        if(count>0){
            cursor.moveToFirst();
            for (int i=0; i<count; i++){
                cursor.moveToPosition(i);
                Entry temp=new Entry();
                temp.id=cursor.getInt(cursor.getColumnIndex("num"));
                temp.field1=cursor.getString(cursor.getColumnIndex("field1"));
                temp.field2=cursor.getString(cursor.getColumnIndex("field2"));
                temp.title=cursor.getString(cursor.getColumnIndex("title"));
                temp.content=cursor.getString(cursor.getColumnIndex("content"));
                entryList.add(temp);
            }
        }

        return entryList;
    }

}
