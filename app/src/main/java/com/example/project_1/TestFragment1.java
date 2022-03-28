package com.example.project_1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestFragment1 extends Fragment{

    private Button q1btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.testq1,container,false);
    }

    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        q1btn=view.findViewById(R.id.q1btn);
        q1btn.setOnClickListener(v -> getFragmentManager().beginTransaction().replace(this.getId(),new TestFragment2()).addToBackStack(null).commit());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



}
