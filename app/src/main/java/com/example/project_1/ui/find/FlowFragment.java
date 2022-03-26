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
import com.example.project_1.databinding.FragmentFlowBinding;

public class FlowFragment extends Fragment {
    private FragmentFlowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFlowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bt1 = binding.bt2;
        bt1.setOnClickListener(v-> Navigation.findNavController(getView()).navigate(R.id.FlowtoAshare));

        TextView tv1 = binding.tv1;
        TextView tv2 = binding.tv2;
        TextView tv3 = binding.tv3;
        TextView tv4 = binding.tv4;
        TextView tv5 = binding.tv5;
        TextView tv6 = binding.tv6;
        tv1.setText("从南方来的资金,通常指通过香港市场流入A股的资金,也就是所谓外资。");
        tv2.setText("在股市中，“南”代表中国香港，“北”代表中国大陆。这是从沪港通衍生出来的概念。\n" +
                "香港买沪市叫北上，沪市买香港叫南下。");
        tv3.setText("当日已成交的净总额，即买入已成交金额-卖出成交金额。");
        tv4.setText("而得出来的数值是正数，说明当日主动性买入多，多方占主导地位；反之如果是负数，说明空方在当日占领主导地位。");
        tv5.setText("当日净流入-当日净流出。");
        tv6.setText("净流入的数额里面包含挂单未成交的委托单。\n" +
                "如果净流入为正数，说明当日资金流入多，反之，说明当日资金流出多。");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
