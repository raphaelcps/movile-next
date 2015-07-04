package com.movile.next.seriestracker.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.adapter.ShowGenresAdapter;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.util.FixedLinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowInfoFragment extends Fragment {

    private static final String ARG_SHOW = "show";

    private Show mShow;
    private View mRoot;

    private OnFragmentInteractionListener mListener;
    private ShowGenresAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ShowInfoFragment newInstance(Show show) {
        ShowInfoFragment fragment = new ShowInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOW, show);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mShow = (Show)getArguments().getSerializable(ARG_SHOW);
        }
    }

/*    @Override
    public void onStart() {
        super.onStart();
        mAdapter.populate(mShow.genres());
    }*/

    private void loadInfo() {
        ((TextView)mRoot.findViewById(R.id.show_info_summary)).setText(mShow.overview());
        mAdapter.populate(mShow.genres());
        ((TextView)mRoot.findViewById(R.id.show_info_details_broadcasting_value)).setText(mShow.airs().day() + " at " + mShow.airs().time() + " on " + mShow.network());
        ((TextView)mRoot.findViewById(R.id.show_info_details_status_value)).setText(mShow.status());
        ((TextView)mRoot.findViewById(R.id.show_info_details_episodes_value)).setText(Long.toString(mShow.airedEpisodes()));
        ((TextView)mRoot.findViewById(R.id.show_info_details_started_value)).setText(Long.toString(mShow.year()));
        ((TextView)mRoot.findViewById(R.id.show_info_details_country_value)).setText(mShow.country().toUpperCase());
        ((TextView)mRoot.findViewById(R.id.show_info_details_homepage_value)).setText((mShow.homepage()!=null)?mShow.homepage():mShow.trailer());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.show_info_fragment, container, false);

        RecyclerView recycler = (RecyclerView) mRoot.findViewById(R.id.show_info_list);
        recycler.setLayoutManager(new FixedLinearLayoutManager(inflater.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ShowGenresAdapter();
        recycler.setAdapter(mAdapter);

        loadInfo();

        return mRoot;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }

}
