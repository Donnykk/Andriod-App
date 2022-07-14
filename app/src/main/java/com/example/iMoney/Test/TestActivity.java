package com.example.iMoney.Test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

import com.example.iMoney.R;


public class TestActivity extends Activity{

    private TestFragment2 q2;
    private TestFragment3 q3;
    private Button q1b;
    private Button q2b;
    private Button q3b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        FragmentManager fm=getFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        TestFragment1 q1 = new TestFragment1();
        tr.replace(R.id.testquest, q1);
        tr.commit();

    }

}
