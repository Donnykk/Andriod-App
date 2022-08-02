package com.example.iMoney.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.iMoney.R;
import com.example.iMoney.databinding.FragmentFindBinding;

public class FindFragment extends Fragment {
    private FragmentFindBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFindBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button index_button = binding.bt2;
        index_button.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.FindtoIndex));

        Button ashare_button = binding.bt3;
        ashare_button.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.FindtoAshare));

        Button knowledge_button = binding.bt4;
        knowledge_button.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.FindtoKnowledge));

        Button euro_button = binding.bt5;
        euro_button.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.FindtoHistory));

        Button pons_button = binding.bt6;
        pons_button.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.FindtoHistory));

        Button tulip_button = binding.bt7;
        tulip_button.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.FindtoHistory));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}