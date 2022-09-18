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
import com.example.iMoney.databinding.FragmentFindBinding;

import java.util.Objects;

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
        index_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoIndex));

        Button ashare_button = binding.bt3;
        ashare_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoAshare));

        Button knowledge_button = binding.bt4;
        knowledge_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoKnowledge));

        Button euro_button = binding.bt5;
        euro_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button pons_button = binding.bt6;
        pons_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button tulip_button = binding.bt7;
        tulip_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button south_button = binding.bt8;
        south_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button mississippi_button = binding.bt9;
        mississippi_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button asia_button = binding.bt10;
        asia_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button danger_button = binding.bt11;
        danger_button.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.FindtoHistory));

        Button search_btn = binding.bt1;
        EditText searchText = binding.et1;
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("search_content", searchText.getText().toString());
                Navigation.findNavController(requireView()).navigate(R.id.FindtoSearch, bundle);
            }
        });
        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (v == null || v.getWindowToken() == null) {
                        return;
                    }
                    InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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