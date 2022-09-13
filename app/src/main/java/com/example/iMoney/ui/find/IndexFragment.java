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
import com.example.iMoney.databinding.FragmentIndexBinding;

import java.util.Objects;

public class IndexFragment extends Fragment {
    private FragmentIndexBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIndexBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bt1 = binding.b2;
        bt1.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoAshare));
        Button bt2 = binding.b3;
        bt2.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoKnowledge));
        Button bt3 = binding.b10;
        bt3.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoCommon));
        Button bt4 = binding.b5;
        bt4.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoCommon));
        Button bt5 = binding.b6;
        bt5.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoCommon));
        Button bt6 = binding.b4;
        bt6.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoCommon));
        Button bt7 = binding.b7;
        bt7.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
        Button bt8 = binding.b11;
        bt8.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
        Button bt9 = binding.b12;
        bt9.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
        Button bt10 = binding.b13;
        bt10.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
        Button bt11 = binding.b14;
        bt11.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
        Button bt12 = binding.b15;
        bt12.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
        Button bt13 = binding.b16;
        bt13.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.IndextoGlobal));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
