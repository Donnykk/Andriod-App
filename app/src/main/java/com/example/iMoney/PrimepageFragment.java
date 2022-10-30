package com.example.iMoney;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.iMoney.databinding.FragmentPrimepageBinding;

public class PrimepageFragment extends Fragment {

    private FragmentPrimepageBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrimepageBinding.inflate(inflater, container, false);
        TextView tv = binding.helloWord;
        tv.setText("           Hi!");
        Button bt1 = binding.bt1;
        Button bt2 = binding.bt2;
        Button bt3 = binding.bt3;
        Button bt4 = binding.bt4;
        Button bt5 = binding.bt5;
        bt1.setText("国务院常务会议决定对养老金实行个人所得税优惠");
        bt1.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.toNewsA);
        });
        bt2.setText("俄罗斯对欧洲主要输气管道发生多处泄漏");
        bt2.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.toNewsB);
        });
        bt3.setText("美联储将联邦基金利率目标区间上调,加剧全球金融市场不确定性");
        bt3.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.toNewsC);
        });
        bt4.setText("城市政府可自主决定当地新发放首套住房贷款利率下限");
        bt4.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.toNewsD);
        });
        bt5.setText("广州数据交易所正式成立");
        bt5.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.toNewsE);
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
