package com.example.project_1.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project_1.R;
import com.example.project_1.databinding.FragmentKnowledgeBinding;

public class KnowledgeFragment extends Fragment {
    private FragmentKnowledgeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentKnowledgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bt1 = binding.b1;
        bt1.setOnClickListener(v-> Navigation.findNavController(getView()).navigate(R.id.KnowledgetoIndex));
        Button bt2 = binding.b2;
        bt2.setOnClickListener(v-> Navigation.findNavController(getView()).navigate(R.id.KnowledgetoAshare));
        Button bt3 = binding.b4;
        bt3.setOnClickListener(v-> Navigation.findNavController(getView()).navigate(R.id.KnowledgetoBasic));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
