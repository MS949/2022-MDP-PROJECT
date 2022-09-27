package com.headthings.mdp_project_2022.initPageFragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.headthings.mdp_project_2022.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitPageFourFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_init_page_four, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.init_ok_button)
    void onClick() {
        SharedPreferences pref = getActivity().getSharedPreferences(getString(R.string.isFirst), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(getString(R.string.isFirst), false);
        editor.apply();
        getActivity().finish();
    }
}
