package com.example.iMoney.ui.find;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentTigerBinding;

public class TigerFragment extends Fragment {
    private FragmentTigerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTigerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv1 = binding.tv1;
        TextView tv2 = binding.tv2;
        TextView tv3 = binding.tv3;
        tv1.setText("游资又叫做热钱，即短期投机的资金，包括资金量较大的个人或私募、一些券商营业部等。其目的在于用尽量少的时间以钱生钱，追求高回报。资本流通速度的提高促进了柚子的产生与扩大。");
        tv2.setText("这些人在股票市场中的操作风格，有一定共同性：通常来说游资的交易手法是非常迅速且凶悍的，类似于战争时期的游击队，经常以迅雷不及掩耳盗铃之势获得一些胜利成果之后，快速地撤出股票市场的战斗。\n" +
                "同时，这样凶悍的投资做法也往往能够制造出极为吸引眼球的短线热点，游资参与到的个股板块也经常会成为股票市场当中的短线领涨龙头。不过，这种板块的热度来得快，去得也快。");
        tv3.setText("敢死队指的是由几个投资人组成，聚集大量资金，在季末、年末或相关衍生品结算日等敏感期进行蓄意拉抬打压股价、通过虚假申报影响开盘价等异常行为。\n" +
                "操作手法上，敢死队会选择小盘股，分析散户心理，通过资金制造一些技术图形吸引散户上钩，因此投资者需要注意避免买入基本面不好的小公司。\"\n");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
