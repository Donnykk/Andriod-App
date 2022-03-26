package com.example.project_1.ui.find;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project_1.R;
import com.example.project_1.databinding.FragmentTigerBinding;

public class TigerFragment extends Fragment {
    private FragmentTigerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTigerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bt1 = binding.bt2;
        bt1.setOnClickListener(v-> Navigation.findNavController(getView()).navigate(R.id.TigertoAshare));

        TextView tv1 = binding.tv1;
        TextView tv2 = binding.tv2;
        TextView tv3 = binding.tv3;
        tv1.setText("游资就是投机性的短期资金，以短时间收获高收益为目的。A股市场中游资一般聚集在几个券商营业部之中，几个比较著 名的营业部就是一线游资。");
        tv2.setText("这些人在股票市场中的操作风格，有一定共同性：通常来说游资的交易手法是非常迅速且凶悍的，类似于战争时期的游击队，经常以迅雷不及掩耳盗铃之势获得一些胜利成果之后，快速地撤出股票市场的战斗。\n" +
                "同时，这样凶悍的投资做法也往往能够制造出极为吸引眼球的短线热点，游资参与到的个股板块也经常会成为股票市场当中的短线领涨龙头。不过，这种板块的热度来得快，去得也快。");
        tv3.setText("一般是游资、超大户、大户或团体等组成的。");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
