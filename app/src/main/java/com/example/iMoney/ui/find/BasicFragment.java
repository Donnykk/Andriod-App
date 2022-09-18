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
import com.example.iMoney.databinding.FragmentBasicBinding;

public class BasicFragment extends Fragment {
    private FragmentBasicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBasicBinding.inflate(inflater, container, false);
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

        Button btn1=binding.b2;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",17);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn2=binding.b3;
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",18);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn3=binding.b4;
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",19);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn4=binding.b5;
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",20);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn5=binding.b6;
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",21);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn6=binding.b7;
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",22);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn7=binding.b8;
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",23);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn8=binding.b9;
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",24);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn9=binding.b10;
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",25);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn10=binding.b11;
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",26);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn11=binding.b12;
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",27);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn12=binding.b13;
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",28);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        Button btn13=binding.b14;
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID",29);
                Navigation.findNavController(requireView()).navigate(R.id.fragment_entry,bundle);
            }
        });

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
