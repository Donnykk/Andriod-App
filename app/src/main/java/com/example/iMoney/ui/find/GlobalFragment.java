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

import com.example.iMoney.databinding.FragmentGlobalBinding;

public class GlobalFragment extends Fragment {
    private FragmentGlobalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGlobalBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
        tv1.setText("道琼斯工业平均指数是根据美国股票市场上30只可靠而且重要的蓝筹股的加权平均数计算出来的，这30只股票来自不同的领域，目前包括迪士尼、苹果、微软、英特尔、可口可乐、麦当劳等一众知名公司股票。\n");
        tv2.setText("标准普尔指数是美国最大的证券研究机构标准·普尔公司编制的股票价格指数。最初采选了230种股票，编制两种股票价格指数。从1976年7月1日开始，改为 400种工业股票，20种运输业股票，40种公用事业股票和40种金融业股票。几十年来，股票始终保持为500种。标普500指数覆盖的是最受欢迎、持有者最多的美国股票，具有很强的代表性。\n");
        tv3.setText("纳斯达克100指数作为纳斯达克的主要指数，其100只成分股均具有高科技、高成长和非金融的特点，可以说是美国科技股的代表。纳斯达克100指数里，这些高成长性股票的良好业绩，都是由各自内生性的高成长带来的，特别是创新业务。\n" +
                "从纳斯达克100指数十大权重成分股来看，他们主要为高科技企业，其中计算机行业的公司居多，包括苹果、微软、谷歌等诸多知名公司。\n");
        tv4.setText("英国富时100指数 (FTSE 100) 由世界级的指数计算金融机构FTSE(富时集团)编制，自1984年起，涵盖在英国伦敦证券交易所交易的上市且市值排名前100的股票，为世界投资人欢迎的金融商品之一，和法国的CAC-40指数，德国的法兰克福指数并称为欧洲三大股票指数，是当前全球投资人观察欧股动向最重要的指标之一。\n");
        tv5.setText("日经指数是日本代表性的股票指数，也是反映日元价值的因素之一，因此会对日元在外汇市场上的汇率造成相关的影响。\n" +
                "日经225指数是以成交量最活跃、市场流通性最高的225只股票的价格为基础，选取的股票虽只占东京证券交易所第一类股中20%的股数，但代表第一类股中近60%的交易量，以及近50%的总市值。具体而言，选择的多为具高流通性的股票，目前其构成股票包括松下电工、丰田汽车、资生堂、花王等知名企业。\n" +
                "值得注意的是，由于日本时间比北京时间要早，所以日本开盘比中国要早1小时。当全球宏观经济和宏观政策出现突发时，通常日本股市的走势会对中国股市有借鉴意义，这也是很多人在A股开盘前会先看日股走势的原因。\n");
        tv6.setText("恒生指数，由香港恒生银行全资附属的恒生指数服务有限公司编制，是以香港股票市场中的50家上市股票为成分股样本，以其发行量为权数的加权平均股价指数，是反映香港股市价幅趋势最有影响的一种股价指数。\n" +
                "恒生指数的成份股具有广泛的市场代表性，其总市值占香港联合交易所市场资本额总和的90%左右。为了进一步反映市场中各类股票的价格走势，恒生指数于1985年开始公布四个分类指数，把33种成份股分别纳入工商业、金融、地产和公共事业四个分类指数中。\n");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
