package com.example.project_1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestFragment2 extends Fragment {
    private Button q2btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.testq2,container,false);
    }

    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        q2btn=view.findViewById(R.id.q2btn);
        q2btn.setOnClickListener(v -> getFragmentManager().beginTransaction().replace(this.getId(),new TestFragment3()).addToBackStack(null).commit());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
