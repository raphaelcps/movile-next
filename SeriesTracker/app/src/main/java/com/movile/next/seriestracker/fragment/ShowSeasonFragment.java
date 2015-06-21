package com.movile.next.seriestracker.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Show;

public class ShowSeasonFragment extends Fragment {
    private static final String ARG_SHOW = "show";

    private Show show;

    public static ShowSeasonFragment newInstance(Show show) {
        ShowSeasonFragment fragment = new ShowSeasonFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOW, show);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowSeasonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            show = (Show) getArguments().getSerializable(ARG_SHOW);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.show_season_fragment, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

}
