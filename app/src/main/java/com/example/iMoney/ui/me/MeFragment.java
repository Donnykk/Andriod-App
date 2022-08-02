package com.example.iMoney.ui.me;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.example.iMoney.LoginActivity;
import com.example.iMoney.MainActivity;
import com.example.iMoney.R;
import com.example.iMoney.Test.TestActivity;
import com.example.iMoney.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {
    private FragmentMeBinding binding;

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

        return binding.getRoot();
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