package com.enesaksu.navigationfragmentcalismasi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.enesaksu.navigationfragmentcalismasi.FirstFragmentDirections;

public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavDirections action = FirstFragmentDirections.actionFirstFragmentToSecondFragment();
                com.enesaksu.navigationfragmentcalismasi.FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = (com.enesaksu.navigationfragmentcalismasi.FirstFragmentDirections.ActionFirstFragmentToSecondFragment) FirstFragmentDirections.actionFirstFragmentToSecondFragment();
                action.setAge(50);
                Navigation.findNavController(view).navigate(action);


            }
        });
    }

}