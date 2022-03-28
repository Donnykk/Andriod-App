package com.example.iMoney.ui.primepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iMoney.R;
import com.example.iMoney.TestActivity;
import com.example.iMoney.databinding.FragmentPrimepageBinding;

public class PrimepageFragment extends Fragment {

    private FragmentPrimepageBinding binding;

    private Button btn_test_start;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrimepageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_test_start = view.findViewById(R.id.test_btn_start);
        btn_test_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getActivity(), TestActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
