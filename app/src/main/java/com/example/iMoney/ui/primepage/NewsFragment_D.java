package com.example.iMoney.ui.primepage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentNewsDBinding;

public class NewsFragment_D extends Fragment {
    private FragmentNewsDBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsDBinding.inflate(inflater, container, false);
        TextView title = binding.title;
        TextView text = binding.text;
        title.setText("城市政府可自主决定当地新发放首套住房贷款利率下限");
        text.setText("        9月29日晚间，中国人民银行、银保监会发布最新通知，“符合条件的城市政府，" +
                "可自主决定在2022年底前阶段性维持、下调或取消当地新发放首套住房贷款利率下限。”" +
                "房地产政策利好或能提振市场信心。");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
