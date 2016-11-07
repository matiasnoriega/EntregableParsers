package com.example.matias.entregableparsers.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.example.matias.entregableparsers.model.Album;
import com.example.matias.entregableparsers.model.AlbumContainer;
import com.example.matias.entregableparsers.util.HTTPConnectionManager;
import com.example.matias.entregableparsers.util.ResultListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matias on 06/11/16.
 */

public class AlbumDAO extends SQLiteOpenHelper{

    //CONSTANTES PARA LOS NOMBRES DE LA BD Y LOS CAMPOS
    private static final String DATABASENAME = "AlbumDB";
    private static final Integer DATABASEVERSION = 1;

    //TABLA PERSONA CON SUS CAMPOS
    private static final String TABLEPOST = "Post";
    private static final String ID = "ID";
    private static final String TITLE = "title";
    private static final String IMAGE = "Body";

    //EL CONSTRUCTOR CREA LA BASE DE DATOS
    public AlbumDAO(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        //CREA LA ESTRUCTURA DE LA BASE DE DATOS, ES DECIR LA/LAS TABLA/S

        String createTable = "CREATE TABLE " + TABLEPOST + "("
                + ID + " INTEGER PRIMARY KEY, "
                + TITLE + " TEXT, "
                + IMAGE + " TEXT " + ")";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //CONSTRUIR UN METODO QUE AGREGUE UN POST A LA BASE DE DATOS
    public void addAlbumToDatabase(Album album){

        //PIDE UNA CONEXION DE ESCRITURA A LA BD
        SQLiteDatabase database = getWritableDatabase();

        //GENERA LA FILA DE LA TABLA
        ContentValues row = new ContentValues();

        //Obtengo los datos y los cargo en el row
        row.put(ID, album.getId());
        row.put(TITLE, album.getTitle());
        row.put(IMAGE, album.getThumbnailUrl());

        //INSERTA EN LA DATABASE EN LA TABLA POST LA FILA CREADA
        database.insert(TABLEPOST, null, row);

        database.close();

    }


    public List<Album> getAllPostFromDatabase(){

        SQLiteDatabase database = getReadableDatabase();

        List<Album> postList = new ArrayList<>();

        String select = "SELECT * FROM " + TABLEPOST;

        Cursor cursor = database.rawQuery(select, null);

        //MIENTRAS HAYA FILAS PARA LEER
        while(cursor.moveToNext()){

            //LEER AL POST
            Album post = new Album();
            post.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            post.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            post.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(IMAGE)));

            postList.add(post);
        }

        cursor.close();
        database.close();
        return postList;
    }

    //// CONEXION A INTERNET - NECESITA TAREA ASINCRONA


    public void getAllPostFromInternet(ResultListener<List<Album>> resultListenerFromController) {

        RetrievePostTask task = new RetrievePostTask(resultListenerFromController);
        task.execute();
    }

    //ESTA CLASE ES UNA CLASE QUE ME PERMITE GENERAR UNA TAREA ASINCRONICA. ES DECIR, ESTA TAREA SE EJECUTARA
// INDEPENDIENTEMENTE DE LO QUE ESTE HACIENDO COMO ACTIVIDAD PRINCIPAL
    private class RetrievePostTask extends AsyncTask<String, Void, List<Album>> {

        private ResultListener listenerFromCrontroller;

        public RetrievePostTask(ResultListener listenerFromCrontroller) {
            this.listenerFromCrontroller = listenerFromCrontroller;
        }

        @Override
        protected List<Album> doInBackground(String... strings) {
            //LO QUE PONGA ACA SE EJECUTA EN SEGUNDO PLANO
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();

            AlbumContainer albumContainer = null;
            try {
                InputStream input = connectionManager.getRequestStream("https://api.myjson.com/bins/2haa3");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                Gson gson = new Gson();
                albumContainer = gson.fromJson(bufferedReader, AlbumContainer.class);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //AGREGAMOS LOS POST A LA BASE DE DATOS
            addAllAlbumToDatabase(albumContainer.getResults());
            return albumContainer.getResults();
        }


        @Override
        protected void onPostExecute(List<Album> posts) {
            //ESTA FUNCION SE EJECUTA CUANDO TERMINO LA TAREA EN SEGUNDO PLANO
            listenerFromCrontroller.finish(posts);
        }
    }

    private void addAllAlbumToDatabase(List<Album> album) {
        for(Album unAlbum : album){
            addAlbumToDatabase(unAlbum);
        }
    }
}
