package com.example.matias.entregableparsers.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.matias.entregableparsers.R;
import com.example.matias.entregableparsers.controller.AlbumController;
import com.example.matias.entregableparsers.model.Album;
import com.example.matias.entregableparsers.model.RecyclerViewAdapter;
import com.example.matias.entregableparsers.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Album> miListaDeAlbumes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miListaDeAlbumes = new ArrayList<Album>();
        this.recyclerViewAdapter = new RecyclerViewAdapter(this, miListaDeAlbumes);
        AlbumController albumController = new AlbumController();
        albumController.getAllAlbums(this, new ResultListenerAlbum<List<Album>>());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


    }


    private class ResultListenerAlbum<T> implements ResultListener<List<Album>> {

        @Override
        public void finish(List<Album> resultado) {
            miListaDeAlbumes.clear();
            miListaDeAlbumes.addAll(resultado);
            recyclerViewAdapter.notifyDataSetChanged();

        }
    }

    public class Listener implements View.OnClickListener{


        @Override
        public void onClick(View view) {

            ViewPagerNoticiasFragment viewPagerNoticiasFragment = new ViewPagerNoticiasFragment();
            Bundle bundle = new Bundle();

            bundle.putInt("Posicion", recyclerView.getChildAdapterPosition(view));
            bundle.putString("Categoria", getCategoria());


            viewPagerNoticiasFragment.setArguments(bundle);

            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, viewPagerNoticiasFragment);
            fragmentTransaction.commit();

        }
}
