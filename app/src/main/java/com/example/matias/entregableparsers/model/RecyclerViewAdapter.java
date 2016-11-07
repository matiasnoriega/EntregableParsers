package com.example.matias.entregableparsers.model;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.matias.entregableparsers.R;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by dh-mob-tt on 03/11/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context contexto;
    private List<Album> listaDeAlbumes;


    public RecyclerViewAdapter(Context contexto) {
        this.contexto = contexto;
        this.listaDeAlbumes = new ArrayList<Album>();
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public List<Album> getListaDeAlbumes() {
        return listaDeAlbumes;
    }

    public void setListaDeAlbumes(List<Album> listaDeAlbumes) {
        this.listaDeAlbumes = listaDeAlbumes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View vistaCelda = inflater.inflate(R.layout.album_recyclerview, parent, false);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(vistaCelda);
        return  albumViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Album album = listaDeAlbumes.get(position);
        AlbumViewHolder albumViewHolder = (AlbumViewHolder) holder;
        albumViewHolder.cargarAlbum(album);
    }

    @Override
    public int getItemCount() {
        return listaDeAlbumes.size();
    }

    private class AlbumViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewfotoAlbum;
        private TextView textViewTitulo;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            textViewTitulo = (TextView) itemView.findViewById(R.id.textViewTituloRecyclerView);
            imageViewfotoAlbum = (ImageView) itemView.findViewById(R.id.imageViewFotoRecyclerView);
        }

        public void cargarAlbum(Album album){
            textViewTitulo.setText(album.getTitle());
            Glide.with(contexto).load(album.getThumbnailUrl()).into(imageViewfotoAlbum);
        }
    }
}