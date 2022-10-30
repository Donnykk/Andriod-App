package com.example.iMoney.ui.primepage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentNewsCBinding;

public class NewsFragment_C extends Fragment {
    private FragmentNewsCBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsCBinding.inflate(inflater, container, false);
        TextView title = binding.title;
        TextView text = binding.text;
        title.setText("美联储将联邦基金利率目标区间上调,加剧全球金融市场不确定性");
        text.setText("        美联储上周宣布将联邦基金利率目标区间上调75个基点到3%至3.25%之间。" +
                "这是美联储今年连续第三次加息75个基点。美联储本次加息前后，一些国家和地区央行均大幅加息。" +
                "本周，印度央行在官网宣布，将基准利率上调50个基点至5.9%；墨西哥央行宣布，" +
                "将基准利率提高0.75个百分点至9.25%；匈牙利央行则加息125个基点，将基准利率从11.75%上调至13%，" +
                "创2000年以来该国基准利率新高。美联储激进加息导致新兴经济体输入性通胀高企，拖累经济增长。" +
                "此举令美元走强，但加剧全球金融市场不确定性，从而抑制投资流动，增加新兴市场货币维稳压力。");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
