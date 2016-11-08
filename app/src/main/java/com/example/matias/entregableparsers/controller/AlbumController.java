package com.example.matias.entregableparsers.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.matias.entregableparsers.dao.AlbumDAO;
import com.example.matias.entregableparsers.model.Album;
import com.example.matias.entregableparsers.util.HTTPConnectionManager;
import com.example.matias.entregableparsers.util.ResultListener;

import java.util.List;


public class AlbumController {
    private ResultListener<List<Album>> resultListenerFromView;
    private Context context;

    public void getAllAlbums(final Context context, final ResultListener<List<Album>> resultListenerFromView) {

        AlbumDAO albumDAO = new AlbumDAO(context);
        this.resultListenerFromView = resultListenerFromView;
        this.context = context;

        if(HTTPConnectionManager.isNetworkingOnline(context)) {
            albumDAO.getAllPostFromInternet(new ResultListenerController<List<Album>>());
        }
        else{
            List<Album> postList = albumDAO.getAllPostFromDatabase();
            resultListenerFromView.finish(postList);
            Toast.makeText(context, "Base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    private class ResultListenerController<T> implements ResultListener<List<Album>> {

        @Override
        public void finish(List<Album> resultado) {

            resultListenerFromView.finish(resultado);
            Toast.makeText(context, "Internet", Toast.LENGTH_SHORT).show();

        }
    }
}
