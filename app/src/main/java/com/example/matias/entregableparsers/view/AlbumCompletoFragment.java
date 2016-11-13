package com.example.matias.entregableparsers.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.matias.entregableparsers.R;
import com.example.matias.entregableparsers.model.Album;

public class AlbumCompletoFragment extends Fragment {


    public AlbumCompletoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View viewADevolver = inflater.inflate(R.layout.fragment_album_completo, container, false);

        ImageView imageView = (ImageView) viewADevolver.findViewById(R.id.image_fragment_pantallaCompleta);
        TextView textView = (TextView) viewADevolver.findViewById(R.id.textViewTituloAlbumCompleto);

        Glide.with(viewADevolver.getContext()).load(bundle.getString("imagen")).placeholder(R.drawable.placeholder).into(imageView);
        textView.setText(bundle.getString("titulo"));

        return viewADevolver;
    }

    public static AlbumCompletoFragment crearAlbumCompletoFragment(Album unAlbum) {
        AlbumCompletoFragment fragment = new AlbumCompletoFragment();
        Bundle bundle = new Bundle();

        bundle.putString("titulo", unAlbum.getTitle());
        bundle.putString("imagen", unAlbum.getUrl());

        fragment.setArguments(bundle);
        return fragment;
    }


}
