package com.example.matias.entregableparsers.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.matias.entregableparsers.dao.AlbumDAO;
import com.example.matias.entregableparsers.model.Album;
import com.example.matias.entregableparsers.util.HTTPConnectionManager;
import com.example.matias.entregableparsers.util.ResultListener;

import java.util.List;


public class AlbumController {

    public AlbumController() {
    }

    public void getAllAlbums(final Context context, final ResultListener<List<Album>> resultListenerFromView) {

        AlbumDAO albumDAO = new AlbumDAO(context);

        //Chequea si hay internet, en caso de no haber conexion, trae las noticias de la DB
        if(HTTPConnectionManager.isNetworkingOnline(context)) {
            albumDAO.getAllPostFromInternet(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    resultListenerFromView.finish(resultado);
                }
            });
        }
        else{
            resultListenerFromView.finish(albumDAO.getAllPostFromDatabase());
            Toast.makeText(context, "Base de datos", Toast.LENGTH_SHORT).show();
        }
    }

}
