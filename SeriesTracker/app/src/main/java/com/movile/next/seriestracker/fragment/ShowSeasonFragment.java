package com.movile.next.seriestracker.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.adapter.SeasonsRecyclerAdapter;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.presenter.SeasonsDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonsDetailsView;

import java.util.List;

public class ShowSeasonFragment extends Fragment implements SeasonsDetailsView, SeasonsRecyclerAdapter.OnSeasonClickListener {
    private static final String ARG_SHOW = "show";

    private Show show;
    private SeasonsDetailsPresenter mPresenter;
    private SeasonsRecyclerAdapter mAdapter;

    public static ShowSeasonFragment newInstance(Show show) {
        ShowSeasonFragment fragment = new ShowSeasonFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOW, show);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowSeasonFragment() {
        // Required empty public constructor
        mPresenter = new SeasonsDetailsPresenter(this);
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
        View view = inflater.inflate(R.layout.show_season_fragment, container, false);

        // RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.seasons_recycler_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new SeasonsRecyclerAdapter(inflater.getContext(), this);
        recyclerView.setAdapter(mAdapter);

        mPresenter.getSeasonsDetails(show.ids().slug());

        return view;
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

    @Override
    public void loadSeasons(List<Season> seasons) {
        mAdapter.populateSeasons(seasons);
    }
}
