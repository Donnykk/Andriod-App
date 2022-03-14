package com.example.project_1.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project_1.R;
import com.example.project_1.databinding.FragmentFindBinding;

public class FindFragment extends Fragment {
    private FragmentFindBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFindBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button b2 = binding.bt2;
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = binding.find;
                linearLayout.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.nav_host_fragment_activity_main,new IndexFragment(),null)
                        .addToBackStack(null).commit();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}