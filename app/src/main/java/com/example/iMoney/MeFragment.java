package com.example.iMoney;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.example.iMoney.databinding.FragmentMeBinding;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MeFragment extends Fragment {
    private FragmentMeBinding binding;
    private static final String ipAddress = "39.106.139.86";
    //private static final String ipAddress = "10.130.22.101";

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMeBinding.inflate(inflater, container, false);
        //生成圆形头像
        String pic_num = LoginActivity.pic_num;
        Bitmap bitmap1;
        switch (pic_num) {
            case "1":
                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
                break;
            case "2":
                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
                break;
            case "3":
                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
                break;
            case "4":
                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
                break;
            default:
                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
        }
        ImageView image = binding.image;
        Bitmap bitmap2 = makeBitmapSquare(bitmap1, 120);
        RoundedBitmapDrawable roundImage = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
        roundImage.setAntiAlias(true);
        roundImage.setCornerRadius((float) bitmap2.getWidth() / 2);
        image.setImageDrawable(roundImage);
        //显示用户名
        TextView username = binding.username;
        username.setText(LoginActivity.username);

        //ImageButton
        ImageButton edit = binding.edit;
        edit.setOnClickListener(v -> {
            try {
                showChoice();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        int height = width;
        float scaleWidth = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        newBitmap = Bitmap.createBitmap(newBitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }
}