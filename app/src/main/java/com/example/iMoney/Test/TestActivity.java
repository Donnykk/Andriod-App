package com.example.iMoney.Test;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iMoney.LoginActivity;
import com.example.iMoney.MainActivity;
import com.example.iMoney.MyDatabaseHelper;
import com.example.iMoney.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class TestActivity extends Activity {
    private static final String ipAddress = "39.106.139.86";
    private boolean opstatus = true;
    private int score = 0;
    private int sa, sb, sc, sd;
    private Button gobtn;
    private TextView tv;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private TextView tv_ending;
    private SQLiteDatabase mydb;
    private Cursor cursor;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        gobtn = findViewById(R.id.gobtn);
        tv = findViewById(R.id.textv);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        tv_ending = findViewById(R.id.ending);

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this, "test2.db");
        try {
            myDatabaseHelper.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbpath = getDatabasePath("test2.db").toString();

        System.out.println(dbpath);

        mydb = SQLiteDatabase.openDatabase(dbpath, null, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING);

        cursor = mydb.rawQuery("select * from OPENING", null);

        cursor.moveToFirst();
        changetext(tv, cursor.getString(cursor.getColumnIndex("content")).replace("%usrname%", "AAAAAAAH"));
        changetext(gobtn, "开始!");
        opstatus = true;

        gobtn.setOnClickListener(v -> {
            if (!opstatus) {
                changetext(gobtn, "");
                changetext(tv, cursor.getString(cursor.getColumnIndex("QUESTION")));
                changetext(btnA, cursor.getString(cursor.getColumnIndex("A")));
                changetext(btnB, cursor.getString(cursor.getColumnIndex("B")));
                changetext(btnC, cursor.getString(cursor.getColumnIndex("C")));
                changetext(btnD, cursor.getString(cursor.getColumnIndex("D")));
                sa = cursor.getInt(cursor.getColumnIndex("scoreA"));
                sb = cursor.getInt(cursor.getColumnIndex("scoreB"));
                sc = cursor.getInt(cursor.getColumnIndex("scoreC"));
                sd = cursor.getInt(cursor.getColumnIndex("scoreD"));
                cursor.moveToNext();
                return;
            }
            if (cursor.moveToNext()) {
                changetext(tv, cursor.getString(cursor.getColumnIndex("content")).replaceAll("%usrname%", "AAAAAAAH"));
                changetext(gobtn, "继续");
            } else {
                cursor = mydb.rawQuery("select * from TEST", null);
                cursor.moveToFirst();
                opstatus = false;
                showNext();
            }
        });
        btnA.setOnClickListener(v -> {
            score += sa;
            if (!showNext()) {
                try {
                    ending();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnB.setOnClickListener(v -> {
            score += sb;
            if (!showNext()) {
                try {
                    ending();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnC.setOnClickListener(v -> {
            score += sc;
            if (!showNext()) {
                try {
                    ending();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnD.setOnClickListener(v -> {
            score += sd;
            if (!showNext()) {
                try {
                    ending();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void changetext(TextView btn, String text) {

        if (text == (btn.getText().toString())) return;

        ObjectAnimator mFadeOutObjectAnimator;
        ObjectAnimator mFadeInObjectAnimator;

        AnimatorSet mAnimatorSet;
        mAnimatorSet = new AnimatorSet();

        if (text.isEmpty()) {
            if (btn.getVisibility() == View.GONE) return;
            mFadeOutObjectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1, 0);
            mFadeOutObjectAnimator.setDuration(500);
            mFadeOutObjectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    btn.setText("");
                    btn.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            mAnimatorSet.play(mFadeOutObjectAnimator);
            mAnimatorSet.start();
            return;
        }

        mFadeInObjectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 0, 1);
        mFadeInObjectAnimator.setDuration(500);

        mFadeOutObjectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1, 0);
        mFadeOutObjectAnimator.setDuration(500);

        mFadeOutObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画执行完毕后修改TextView的值
                if (btn.getVisibility() == View.GONE) {
                    //btn.setAlpha(0);
                    btn.setVisibility(View.VISIBLE);
                }
                btn.setText(text);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        mAnimatorSet.play(mFadeOutObjectAnimator).before(mFadeInObjectAnimator);
        mAnimatorSet.start();
    }

    private void changetext(Button btn, String text) {

        if (text == (btn.getText().toString())) return;

        ObjectAnimator mFadeOutObjectAnimator;
        ObjectAnimator mFadeInObjectAnimator;

        AnimatorSet mAnimatorSet;
        mAnimatorSet = new AnimatorSet();

        if (text.isEmpty()) {
            if (btn.getVisibility() == View.GONE) return;
            mFadeOutObjectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1, 0);
            mFadeOutObjectAnimator.setDuration(500);
            mFadeOutObjectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    btn.setText("");
                    btn.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            mAnimatorSet.play(mFadeOutObjectAnimator);
            mAnimatorSet.start();
            return;
        }

        mFadeInObjectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 0, 1);
        mFadeInObjectAnimator.setDuration(500);

        mFadeOutObjectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1, 0);
        mFadeOutObjectAnimator.setDuration(500);

        mFadeOutObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画执行完毕后修改TextView的值
                if (btn.getVisibility() == View.GONE) {
                    //btn.setAlpha(0);
                    btn.setVisibility(View.VISIBLE);
                }
                btn.setText(text);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        mAnimatorSet.play(mFadeOutObjectAnimator).before(mFadeInObjectAnimator);
        mAnimatorSet.start();
    }

    @SuppressLint("Range")
    boolean showNext() {
        if (cursor.getPosition() == cursor.getCount()) return false;
        String qin = cursor.getString(cursor.getColumnIndex("QIN"));
        if (!qin.isEmpty()) {
            changetext(tv, qin);
            changetext(btnA, "");
            changetext(btnB, "");
            changetext(btnC, "");
            changetext(btnD, "");
            changetext(gobtn, "继续");
            //gobtn change onclick
        } else {
            String q = cursor.getString(cursor.getColumnIndex("QUESTION"));
            changetext(tv, q);
            changetext(btnA, cursor.getString(cursor.getColumnIndex("A")));
            changetext(btnB, cursor.getString(cursor.getColumnIndex("B")));
            changetext(btnC, cursor.getString(cursor.getColumnIndex("C")));
            changetext(btnD, cursor.getString(cursor.getColumnIndex("D")));
            changetext(gobtn, "");
            sa = cursor.getInt(cursor.getColumnIndex("scoreA"));
            sb = cursor.getInt(cursor.getColumnIndex("scoreB"));
            sc = cursor.getInt(cursor.getColumnIndex("scoreC"));
            sd = cursor.getInt(cursor.getColumnIndex("scoreD"));
            cursor.moveToNext();
        }
        return true;
    }

    @SuppressLint("Range")
    void ending() throws IOException {
        int sty;
        String style, stycontent;
        if (score >= 8 && score <= 12) {
            sty = 1;
        } else if (score >= 13 && score <= 17) {
            sty = 2;
        } else if (score >= 18 && score <= 22) {
            sty = 3;
        } else {
            sty = 4;
        }
        cursor = mydb.rawQuery("select * from RESULT where id=" + sty, null);
        cursor.moveToFirst();
        changetext(btnA, "");
        changetext(btnB, "");
        changetext(btnC, "");
        changetext(btnD, "");
        changetext(tv, "");
        style = cursor.getString(cursor.getColumnIndex("style"));
        stycontent = cursor.getString(cursor.getColumnIndex("content"));
        changetext(tv_ending, "您的测试结果为：" + style + '\n' + '\n' + stycontent);
        upLoadResult(style);
    }

    void upLoadResult(String style) throws IOException {
        LoginActivity.style = style;
        Socket s1 = new Socket(ipAddress, 8088);
        OutputStream os = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        String phone = LoginActivity.phoneNum;
        dos.writeUTF(phone + " " + style + " " + "ChangeStyle");
    }
}
