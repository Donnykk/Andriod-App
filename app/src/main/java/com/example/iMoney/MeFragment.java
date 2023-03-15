package com.example.iMoney;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.example.iMoney.Test.TestActivity;
import com.example.iMoney.databinding.FragmentMeBinding;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MeFragment extends Fragment {
    private FragmentMeBinding binding;
    private static final String ipAddress = "39.106.139.86";

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMeBinding.inflate(inflater, container, false);
        //生成圆形头像
        ImageView image = binding.image;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Bitmap bitmap2 = makeBitmapSquare(bitmap1, 120);
        RoundedBitmapDrawable roundImage = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
        roundImage.setAntiAlias(true);
        roundImage.setCornerRadius((float) bitmap2.getWidth() / 2);
        image.setImageDrawable(roundImage);
        //显示用户名
        TextView username = binding.username;
        username.setText(LoginActivity.username);
        //显示投资风格
        TextView title = binding.testTitle;
        TextView intro = binding.testIntro;
        title.setText("你的投资风格：" + LoginActivity.style);
        ImageButton ib = binding.refresh;
        ib.setOnClickListener(v -> {
            title.setText("你的投资风格：" + LoginActivity.style);
        });
        switch (LoginActivity.style) {
            case "保守型":
                intro.setText("在个性上，本能地抗拒冒险，不抱碰运气的侥幸心理，通常不愿意承受投资波动对心理的煎熬，" +
                        "追求稳定。你极度需要安全感，你的小心谨慎也许会让你避开许多危险和错误。身为一个投资人，" +
                        "你依然秉持谨小慎微的特质。即使你也想投资未来，但仍然以安全为第一优先。");
            case "中庸型（稳健型）":
                intro.setText("在个性上，您有较高的追求目标，而且对风险有清醒的认识，但通常不会采取激进的办法去达到目标，" +
                        "而总是在事情的两极之间找到相对妥协、均衡的方法，因此通常能缓慢但稳定地进步。");
            case "进取型（积极型）":
                intro.setText("个性上比较自信，对于机会跃跃欲试，经常在考虑过后选择尝试。风险是你生活的一部分，" +
                        "但你也不会轻率去冒险。你可以将保守与动态的投资结合在一起。你是一个宁愿冒险也不愿当逃兵的投资人，" +
                        "但是你也应该注意随时确保自己有足够的现金储备，这样才能维持资金流动性，" +
                        "以便有机会在资本市场“拣便宜”，并且保障投资组合的安全。");
            case "冒险型":
                intro.setText("个性上非常自信。极度追求成功，常不给自己留后路以激励自己向前，不惜冒失败的风险。" +
                        "你乐于冒险，尝试极端的经验、经营充满冒险性的事业生涯以及从事高度波动性的投资，" +
                        "都是你生活中的一部分。对你而言，没有绝对的安全，只有绝佳的机会。身为一个投资人，" +
                        "你希望获得最高的报酬，也愿意冒必要的风险，而勇于冒险犯难的成果就是高于平均标准的获利率。");
        }
        //ImageButton
        ImageButton edit = binding.edit;
        edit.setOnClickListener(v -> {
            try {
                showChoice();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //开始测试
        Button startbtn = binding.testBtnStart;
        startbtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TestActivity.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }

    public void showChoice() throws IOException {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] choices = {"编辑头像", "编辑昵称"};
        Socket s1 = new Socket(ipAddress, 8088);
        OutputStream os = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        String phone = LoginActivity.phoneNum;
        builder.setItems(choices, (dialog, choice) -> {
            if (choice == 1) {
                //编辑昵称
                View v = getLayoutInflater().inflate(R.layout.popmenu, null);
                final EditText et = v.findViewById(R.id.name_dialog);
                new AlertDialog.Builder(getActivity()).setView(v)
                        .setPositiveButton("确定", (dialogInterface, i) -> {
                            LoginActivity.username = et.getText().toString();
                            binding.username.setText(et.getText().toString());
                            //修改数据库
                            try {
                                dos.writeUTF(phone + " " + et.getText().toString() + " " + "ChangeName");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).setNegativeButton("取消", null).show();
            } else if (choice == 0) {
                //编辑头像
                final String[] pic_num = new String[1];
                View v = getLayoutInflater().inflate(R.layout.pic_menu, null);
                final Bitmap[] bitmap1 = new Bitmap[1];
                @SuppressLint("CutPasteId") Button bt1 = v.findViewById(R.id.bt1);
                @SuppressLint("CutPasteId") Button bt2 = v.findViewById(R.id.bt2);
                @SuppressLint("CutPasteId") Button bt3 = v.findViewById(R.id.bt3);
                @SuppressLint("CutPasteId") Button bt4 = v.findViewById(R.id.bt4);
                bt1.setOnClickListener(v1 -> {
                    bitmap1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
                    pic_num[0] = "1";
                });
                bt2.setOnClickListener(v1 -> {
                    bitmap1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
                    pic_num[0] = "2";
                });
                bt3.setOnClickListener(v1 -> {
                    bitmap1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
                    pic_num[0] = "3";
                });
                bt4.setOnClickListener(v1 -> {
                    bitmap1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
                    pic_num[0] = "4";
                });
                new AlertDialog.Builder(getActivity()).setView(v)
                        .setPositiveButton("确定", (dialogInterface, i) -> {
                            ImageView image = binding.image;
                            Bitmap bitmap2 = makeBitmapSquare(bitmap1[0], 120);
                            RoundedBitmapDrawable roundImage = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
                            roundImage.setAntiAlias(true);
                            roundImage.setCornerRadius((float) bitmap2.getWidth() / 2);
                            image.setImageDrawable(roundImage);
                            //修改数据库
                            try {
                                dos.writeUTF(phone + " " + pic_num[0] + " " + "ChangePic");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
        });
        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static Bitmap makeBitmapSquare(Bitmap oldBitmap, int newWidth) {
        Bitmap newBitmap;
        if (oldBitmap.getWidth() > oldBitmap.getHeight()) {
            newBitmap = Bitmap.createBitmap(oldBitmap, oldBitmap.getWidth() / 2 - oldBitmap.getHeight() / 2, 0, oldBitmap.getHeight(), oldBitmap.getHeight());
        } else {
            newBitmap = Bitmap.createBitmap(oldBitmap, 0, oldBitmap.getHeight() / 2 - oldBitmap.getWidth() / 2, oldBitmap.getWidth(), oldBitmap.getWidth());
        }
        int width = newBitmap.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        newBitmap = Bitmap.createBitmap(newBitmap, 0, 0, width, width, matrix, true);
        return newBitmap;
    }
}