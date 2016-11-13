package com.example.matias.entregableparsers.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matias.entregableparsers.R;
import com.example.matias.entregableparsers.model.Album;

public class AlbumCompletoFragment extends Fragment {


    public AlbumCompletoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_completo, container, false);
    }

    public static AlbumCompletoFragment crearAlbumCompletoFragment(Album unAlbum) {
        AlbumCompletoFragment fragment = new AlbumCompletoFragment();

        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


}
