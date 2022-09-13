package com.example.iMoney;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentPrimepageBinding;

public class PrimepageFragment extends Fragment {

    private FragmentPrimepageBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrimepageBinding.inflate(inflater, container, false);
        TextView tv = binding.helloWord;
        tv.setText("           Hi!");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
