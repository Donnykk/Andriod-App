package com.example.iMoney.ui.primepage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentNewsABinding;

public class NewsFragment_A extends Fragment {
    private FragmentNewsABinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsABinding.inflate(inflater, container, false);
        TextView title = binding.title;
        TextView text = binding.text;
        title.setText("国务院常务会议决定对养老金实行个人所得税优惠");
        text.setText("        国务院总理李克强9月26日主持召开国务院常务会议。会议决定，对政策支持、" +
                "商业化运营的个人养老金实行个人所得税优惠：对缴费者按每年12000元的限额予以税前扣除，投资收益暂不征税，领取收入的实际税负由7.5%降为3%。");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
