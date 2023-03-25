package com.example.iMoney;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private static class Question {
        public String sA = "", sB = "", sC = "";
        Uri video;
        Question nextA = null, nextB = null, nextC = null;
        boolean isLast;
    }

    private static Question q;
    private final Question q1 = new Question();
    private final Question q1_no = new Question();
    private final Question q2 = new Question();
    private final Question p1 = new Question();
    private final Question p1_y = new Question();
    private final Question p1_n = new Question();
    private final Question p2 = new Question();
    private final Question p2_y = new Question();
    private final Question p2_n = new Question();
    private final Question p3 = new Question();
    private final Question p3_y = new Question();
    private final Question p3_n = new Question();

    private void initQuestion() {

        q1.sA = "买！";
        q1.sB = "不买！";
        q1.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.q1);
        q1.nextA = q2;
        q1.nextB = q1_no;
        q1.isLast = false;

        q1_no.sA = "重新开始";
        q1_no.sB = "结束";
        q1_no.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.q1_no);
        q1_no.nextA = q1;
        q1_no.isLast = true;

        q2.sA = "产品一";
        q2.sB = "产品二";
        q2.sC = "产品三";
        q2.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.q2);
        q2.nextA = p1;
        q2.nextB = p2;
        q2.nextC = p3;
        q2.isLast = false;

        p1.sA = "买！";
        p1.sB = "不买！";
        p1.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p1);
        p1.nextA = p1_y;
        p1.nextB = p1_n;
        p1.isLast = false;

        p1_n.sA = "重新选择";
        p1_n.sB = "重新播放";
        p1_n.sC = "结束";
        p1_n.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p1_no);
        p1_n.nextA = q2;
        p1_n.nextB = q1;
        p1_n.isLast = false;

        p1_y.sA = "重新选择";
        p1_y.sB = "重新播放";
        p1_y.sC = "结束";
        p1_y.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p1_yes);
        p1_y.nextA = q2;
        p1_y.nextB = q1;
        p1_y.isLast = false;

        p2.sA = "买！";
        p2.sB = "不买！";
        p2.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p2);
        p2.nextA = p2_y;
        p2.nextB = p2_n;
        p2.isLast = false;

        p2_y.sA = "重新选择";
        p2_y.sB = "重新播放";
        p2_y.sC = "结束";
        p2_y.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p2_yes);
        p2_y.nextA = q2;
        p2_y.nextB = q1;
        p2_y.isLast = false;

        p2_n.sA = "重新选择";
        p2_n.sB = "重新播放";
        p2_n.sC = "结束";
        p2_n.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p2_no);
        p2_n.nextA = q2;
        p2_n.nextB = q1;
        p2_n.isLast = false;

        p3.sA = "买！";
        p3.sB = "不买！";
        p3.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p3);
        p3.nextA = p3_y;
        p3.nextB = p3_n;
        p3.isLast = false;

        p3_y.sA = "重新选择";
        p3_y.sB = "重新播放";
        p3_y.sC = "结束";
        p3_y.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p3_yes);
        p3_y.nextA = q2;
        p3_y.nextB = q1;
        p3_y.isLast = false;

        p3_n.sA = "重新选择";
        p3_n.sB = "重新播放";
        p3_n.sC = "结束";
        p3_n.video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.p3_no);
        p3_n.nextA = q2;
        p3_n.nextB = q1;
        p3_n.isLast = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        initQuestion();
        q = q1;
        VideoView videoView = findViewById(R.id.videov);
        playQ(q, videoView);
        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.q1));
        videoView.setOnCompletionListener(mp -> showBtn(videoView));
    }

    private void showBtn(VideoView vv) {
        Button btn1 = findViewById(R.id.vbtnA);
        Button btn2 = findViewById(R.id.vbtnB);
        Button btn3 = findViewById(R.id.vbtnC);

        if (q.sC.isEmpty()) {
            btn1.setText(q.sA);
            btn3.setText(q.sB);
            btn1.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);

            btn1.setOnClickListener(v -> {
                playQ(q.nextA, vv);
                q = q.nextA;
                System.out.println(q);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
            });
            btn3.setOnClickListener(v -> {
                playQ(q.nextB, vv);
                q = q.nextB;
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
            });

        } else {
            btn1.setText(q.sA);
            btn2.setText(q.sB);
            btn3.setText(q.sC);
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);

            btn1.setOnClickListener(v -> {
                playQ(q.nextA, vv);
                q = q.nextA;
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
            });
            btn2.setOnClickListener(v -> {
                playQ(q.nextB, vv);
                q = q.nextB;
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
            });
            btn3.setOnClickListener(v -> {
                playQ(q.nextC, vv);
                q = q.nextC;
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
            });
        }
    }


    private void playQ(Question q, VideoView vv) {
        if (q == null) {
            finish();
        } else {
            vv.setVideoURI(q.video);
            vv.start();
        }
    }


}