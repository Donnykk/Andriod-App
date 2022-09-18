package com.example.iMoney.ui.find;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.iMoney.R;
import com.example.iMoney.databinding.FragmentFlowBinding;

public class FlowFragment extends Fragment {
    private FragmentFlowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFlowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button search_btn=binding.bt1;
        EditText searchText=binding.et1;
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("search_content",searchText.getText().toString());
                Navigation.findNavController(requireView()).navigate(R.id.fragment_search,bundle);
            }
        });
        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (v == null || v.getWindowToken() == null) {
                        return;
                    }
                    InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (im != null) {
                        im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        });

        return root;
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv1 = binding.tv1;
        TextView tv2 = binding.tv2;
        TextView tv3 = binding.tv3;
        TextView tv4 = binding.tv4;
        TextView tv5 = binding.tv5;
        TextView tv6 = binding.tv6;
        TextView tv7 = binding.tv7;
        TextView tv8 = binding.tv8;
        tv1.setText("在中国股市中，一般“北”指的是沪深两市的股票，“南”指的是香港股票，因此，北向资金就是指从香港股票中流入大陆股市的资金，同时南向资金指的是内地的股票流入香港股市的资金。");
        tv2.setText("在股市中，“南”代表中国香港，“北”代表中国大陆。这是从沪港通衍生出来的概念。\n" +
                "香港买沪市叫北上，沪市买香港叫南下。");
        tv3.setText("当日已成交的净总额，即买入已成交金额-卖出成交金额。");
        tv4.setText("得出来的数值是正数，说明当日主动性买入多，多方占主导地位；反之如果是负数，说明空方在当日占领主导地位。");
        tv5.setText("当日净流入-当日净流出。");
        tv6.setText("净流入的数额里面包含挂单未成交的委托单。\n" +
                "如果净流入为正数，说明当日资金流入多，反之，说明当日资金流出多。");
        tv7.setText("大盘资金流向分当日实时资金流向和多日资金流向路线图两部分。大盘实时资金流向统计当日实时资金流向，包括资金流入、资金流出、资金差、资金比、主力散户流入、资金异动监控等数据。");
        tv8.setText("大盘多日资金流向数据统计大盘近1日、3日、5日和10日的总资金流向、主力资金流向、散户资金流向数据，并根据时间序列，将资金流向增量数据和累计数据以图表的形式展现出来，形成资金路线图。");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
