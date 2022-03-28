package com.example.project_1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestFragment3 extends Fragment {
    private Button q3btn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.testq3,container,false);
    }

    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        q3btn=view.findViewById(R.id.q3btn);
        q3btn.setOnClickListener(v -> Toast.makeText(getActivity(),"This is the last question",Toast.LENGTH_SHORT).show());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
