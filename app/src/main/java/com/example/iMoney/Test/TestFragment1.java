package com.example.iMoney.Test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.iMoney.R;

public class TestFragment1 extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.testq1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button q1btn = view.findViewById(R.id.q1btn);
        q1btn.setOnClickListener(v -> getFragmentManager().beginTransaction()
                .replace(this.getId(), new TestFragment2()).addToBackStack(null).commit());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
