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
import com.example.iMoney.databinding.FragmentKnowledgeBinding;

public class KnowledgeFragment extends Fragment {
    private FragmentKnowledgeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentKnowledgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button search_btn=binding.bt1;
        EditText searchText=binding.et1;
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("search_content",searchText.getText().toString());
                Navigation.findNavController(requireView()).navigate(R.id.fragment_search,bundle);
            }
        });
        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
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
