package com.example.project_1.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project_1.databinding.FragmentFindBinding;

public class FindFragment extends Fragment {
    private FragmentFindBinding binding;
    private EditText et1;
    private Button bt1;
    private TextView tv1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private TextView tv2;
    private Button bt5;
    private Button bt6;
    private Button bt7;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFindBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //事件处理
        Button bt1 = binding.bt2;
        bt1.setOnClickListener(v -> {

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}