package com.example.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class ResultFragment extends Fragment {

    private static String argSelectedFaculty = "selectedFaculty";
    private static String argSelectedCourse = "selectedCourse";

    private String selectedFaculty;
    private String selectedCourse;

    private Button cancelButton;

    public static ResultFragment newInstance(String selectedFaculty, String selectedCourse) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(argSelectedFaculty, selectedFaculty);
        args.putString(argSelectedCourse, selectedCourse);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedFaculty = getArguments().getString(argSelectedFaculty);
            selectedCourse = getArguments().getString(argSelectedCourse);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView resultTextView = view.findViewById(R.id.resultTextView);
        String resultText = getString(R.string.result_format, selectedFaculty, selectedCourse);
        resultTextView.setText(resultText);

        cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            clearResult();
            navigateToSelectionFragment();
        });

        return view;
    }

    private void clearResult() {
        TextView result = requireView().findViewById(R.id.resultTextView);
        result.setText("");
    }

    private void navigateToSelectionFragment() {
        SelectionFragment selectionFragment = new SelectionFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, selectionFragment);
        transaction.commit();
    }
}
