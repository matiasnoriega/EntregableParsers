package com.example.matias.entregableparsers.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matias.entregableparsers.R;
import com.example.matias.entregableparsers.controller.AlbumController;
import com.example.matias.entregableparsers.model.Album;
import com.example.matias.entregableparsers.model.RecyclerViewAdapter;
import com.example.matias.entregableparsers.util.ResultListener;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Album> miListaDeAlbumes;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewADevolver = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        miListaDeAlbumes = new ArrayList<Album>();
        this.recyclerViewAdapter = new RecyclerViewAdapter(viewADevolver.getContext(), miListaDeAlbumes, new Listener());
        AlbumController albumController = new AlbumController();
        albumController.getAllAlbums(viewADevolver.getContext(), new ResultListenerAlbum<List<Album>>());

        recyclerView = (RecyclerView) viewADevolver.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewADevolver.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        return viewADevolver;
    }


    private class ResultListenerAlbum<T> implements ResultListener<List<Album>> {

        @Override
        public void finish(List<Album> resultado) {
            miListaDeAlbumes.clear();
            miListaDeAlbumes.addAll(resultado);
            recyclerViewAdapter.notifyDataSetChanged();

        }
    }

    private class Listener implements View.OnClickListener {


        @Override
        public void onClick(View view) {

            AlbumCompletoFragment albumCompletoFragment = new AlbumCompletoFragment();
            Bundle bundle = new Bundle();

            Album album = miListaDeAlbumes.get(recyclerView.getChildAdapterPosition(view));
            bundle.putString("titulo", album.getTitle());
            bundle.putString("imagen", album.getUrl());

            albumCompletoFragment.setArguments(bundle);

            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentMain, albumCompletoFragment);
            fragmentTransaction.commit();

        }
    }
}
