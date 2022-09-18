package com.example.iMoney.ui.find;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.Entry;
import com.example.iMoney.databinding.FragmentEntryBinding;

import java.util.ArrayList;
import java.util.List;

public class EntryFragment extends Fragment {
    private FragmentEntryBinding binding;
    private Entry data = new Entry();

    @SuppressLint("Range")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEntryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        int id=getArguments().getInt("ID");

        String dbpath=getActivity().getDatabasePath("knowledge.db").toString();
        SQLiteDatabase mydb=SQLiteDatabase.openDatabase(dbpath,null, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING);

        Cursor cursor = mydb.rawQuery("select * from KNOWLEDGE where num="+Integer.toString(id),null);
        data.id=id;
        cursor.moveToFirst();
        data.content=cursor.getString(cursor.getColumnIndex("content"));
        data.field1=cursor.getString(cursor.getColumnIndex("field1"));
        data.field2=cursor.getString(cursor.getColumnIndex("field2"));
        data.title="    "+cursor.getString(cursor.getColumnIndex("title"))+"    ";
        binding.tvTitle.setText(data.title);
        binding.tvField.setText(data.field1+" | "+data.field2);
        binding.tvContent.setText(data.content);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
