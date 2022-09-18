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
import com.example.iMoney.databinding.FragmentRankingBinding;

public class RankingFragment extends Fragment {
    private FragmentRankingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRankingBinding.inflate(inflater, container, false);
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
        tv1.setText("计算公式：股票涨跌幅=（收盘价/开盘价-1）*100%");
        tv2.setText("注：为了减少股市交易中的投机行为，规定每个股票每个交易日的涨跌幅度。" + "就中国股市来说，每个交易日涨跌限幅为10%。");
        tv3.setText("五分钟快速涨幅榜就是最近五分钟之内涨速最快的股票排行");
        tv4.setText("注：①看快速涨幅榜的意义就是及时发现异动的个股，从而决定是否参与买卖。\n" +
                "②一般判断是：上涨的时间越短、涨幅越大，主力资金的实力就越强。");
        tv5.setText("成交量是指当天（已）成交股票的数量。");
        tv6.setText("注：①成交额=成交量*成交价格\n" +
                "②股市中的VOL是成交量指标，在形态上用一根立柱来表示。如当天收盘价大于等于当天的开盘价，成交柱呈红色；反之，成交柱呈绿色。");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
