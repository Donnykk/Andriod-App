package com.example.iMoney.ui.primepage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentNewsEBinding;

public class NewsFragment_E extends Fragment {
    private FragmentNewsEBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsEBinding.inflate(inflater, container, false);
        TextView title = binding.title;
        TextView text = binding.text;
        title.setText("广州数据交易所正式成立");
        text.setText("        9月30日，广州数据交易所揭牌仪式在广州市南沙区举行，这标志着广东省级数据交易机构的正式成立。" +
                "为应对数据供给难、定价难、入场难等关键共性难题，交易所采用市场化运作方式，" +
                "为市场主体提供合规安全、集约高效的数据流通交易综合性服务，包含数据资产登记、交易清结算、信息披露、" +
                "数据保险、数据托管、人才培训等内容，并探索成立数据合规委员会，建立合规会审机制，保护数据资产权益。");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
