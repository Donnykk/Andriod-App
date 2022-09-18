package com.example.iMoney.ui.find;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.iMoney.R;
import com.example.iMoney.databinding.FragmentAshareBinding;

import java.util.Objects;

public class AshareFragment extends Fragment {
    private FragmentAshareBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAshareBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b1 = binding.b1;
        b1.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoIndex));
        Button b2 = binding.b3;
        b2.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoKnowledge));
        Button bt1 = binding.b4;
        bt1.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoRanking));
        Button bt2 = binding.b5;
        bt2.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoRanking));
        Button bt3 = binding.b8;
        bt3.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoRanking));
        Button bt4 = binding.b11;
        bt4.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoRanking));
        Button bt5 = binding.b15;
        bt5.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoTiger));
        Button bt6 = binding.b16;
        bt6.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoTiger));
        Button bt7 = binding.b17;
        bt7.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoTiger));
        Button bt8 = binding.b19;
        bt8.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoFlow));
        Button bt9 = binding.b20;
        bt9.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoFlow));
        Button bt10 = binding.b22;
        bt10.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoFlow));
        Button bt11 = binding.b23;
        bt11.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoFlow));
        Button bt12 = binding.b21;
        bt12.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.AsharetoFlow));

        Button search_btn = binding.bt1;
        EditText searchText = binding.et1;
        search_btn.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("search_content", searchText.getText().toString());
            Navigation.findNavController(requireView()).navigate(R.id.fragment_search, bundle);
        });

        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (v == null || v.getWindowToken() == null) {
                        return;
                    }
                    InputMethodManager im = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (im != null) {
                        im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
