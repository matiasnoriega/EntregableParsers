package com.example.matias.entregableparsers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matias.entregableparsers.controller.AlbumController;
import com.example.matias.entregableparsers.model.Album;
import com.example.matias.entregableparsers.model.RecyclerViewAdapter;
import com.example.matias.entregableparsers.util.ResultListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private AlbumController albumController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlbumController albumController = new AlbumController();
        albumController.getAllAlbums(this, new ResultListenerAlbum<List<Album>>());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this);

        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }


    private class ResultListenerAlbum<T> implements ResultListener<List<Album>> {

        @Override
        public void finish(List<Album> resultado) {

            recyclerViewAdapter.setListaDeAlbumes(resultado);
            recyclerViewAdapter.notifyDataSetChanged();

        }
    }
}
