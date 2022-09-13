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

import com.example.iMoney.databinding.FragmentCommonBinding;

public class CommonFragment extends Fragment {
    private FragmentCommonBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv1 = binding.tv1;
        TextView tv2 = binding.tv2;
        TextView tv3 = binding.tv3;
        tv1.setText("股票指数能够体现大盘整体情况，对于投资者具有重要的指导意义。在有效性较强的市场中，股票价格反映了很多信息，按照消极投资策略，只需要对于分散化极强的大盘指数进行跟踪，即可获得最佳的投资收益。");
        tv2.setText("沪深300指数由沪深市场中规模大、流动性好的最具代表性的300只证券组成，于2005年4月8日正式发布，以反映沪深市场上市公司证券的整体表现。由于沪深300指数以规模和流动性作为选样的根本标准，可以给投资者提供权威的投资方向，也便于投资者进行跟踪和进行投资组合，保证了指数的稳定性、代表性和可操作性。较上证综指而言，沪深300指数更具代表性。");
        tv3.setText("上海证券综合指数简称“上证综指”，其样本股是在上海证券交易所全部上市股票，包括A股和B股，反映了上海证券交易所上市股票价格的变动情况，自1991年7月15日起正式发布。");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
