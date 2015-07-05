package com.movile.next.seriestracker.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.ShowDetailsActivity;
import com.movile.next.seriestracker.adapter.FavoritesAdapter;
import com.movile.next.seriestracker.presenter.FavoritesPresenter;
import com.movile.next.seriestracker.view.FavoritesView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment implements FavoritesView, FavoritesAdapter.FavoritesListener {
//    private OnFragmentInteractionListener mListener;

    private FavoritesAdapter mAdapter;
    private FavoritesPresenter mPresenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.favorites_fragment, container, false);

        ListView list = (ListView) root.findViewById(R.id.favorite_shows_list);

        mAdapter = new FavoritesAdapter(this.getActivity(), this);
        list.setAdapter(mAdapter);
        //list.addHeaderView(inflater.inflate(R.layout.favorites_header, container));

        mPresenter = new FavoritesPresenter(getActivity(), this, getActivity().getSupportLoaderManager());
        mPresenter.LoadFavorites();

        return root;
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
/*        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onFavoritesLoaded(Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onFavoriteClicked(String slug) {
        Log.d("teste", slug);
        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW_SLUG, slug);
        startActivity(intent);
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
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
