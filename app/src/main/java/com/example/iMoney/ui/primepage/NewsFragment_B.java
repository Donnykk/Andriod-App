package com.example.iMoney.ui.primepage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentNewsBBinding;

public class NewsFragment_B extends Fragment {
    private FragmentNewsBBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsBBinding.inflate(inflater, container, false);
        TextView title = binding.title;
        TextView text = binding.text;
        title.setText("俄罗斯对欧洲主要输气管道发生多处泄漏");
        text.setText("        俄罗斯对欧洲主要输气管道——“北溪-1”和“北溪-2”天然气管道9月26日发生多处泄漏。" +
                "俄罗斯项目运营方——北溪天然气管道公司发言人10月1日表示，“北溪-2”天然气管道的天然气泄漏已经停止。" +
                "挪威大气研究所表示，“北溪”天然气管道泄漏后，该地区上空形成大片甲烷云并不断蔓延、扩散，" +
                "截至当天，已有至少8万吨甲烷气体扩散到海洋和大气中。联合国环境规划署表明，" +
                "这可能是有记录以来最严重的一起甲烷泄漏事件。");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
