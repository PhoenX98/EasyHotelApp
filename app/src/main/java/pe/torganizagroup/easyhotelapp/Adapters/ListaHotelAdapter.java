package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.DrawableActivity;
import pe.torganizagroup.easyhotelapp.Fragment.hotel_detalle_fragment;
import pe.torganizagroup.easyhotelapp.Fragment.lista_hoteles_fragment;
import pe.torganizagroup.easyhotelapp.Interfaces.FragmentComunicator;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.R;

public class ListaHotelAdapter extends RecyclerView.Adapter<ListaHotelAdapter.ViewHolder> {

    Context mContext;
    private List<Hotels> listaHotels = new ArrayList<> ();
    private List<String> urlList = new ArrayList<> ();
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ListaHotelAdapter(Context mContext, List<Hotels> listaHotels) {
        this.mContext = mContext;
        this.listaHotels = listaHotels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate (R.layout.item_local, viewGroup,false);
        ViewHolder holder = new ViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Hotels h1 = listaHotels.get (i);
        String costeF = "Desde S/. ";
//        final hotel_detalle_fragment HDF =new hotel_detalle_fragment ();
//        final Bundle bundleLHA = new Bundle ();
        final String nombre = h1.getNameHotel ();
        final String direccion = h1.getAddress ();
        final String tarifa = h1.getMinimalRate ();

        //carga de elemento de indice de numero de elementos en la lista photos
        //se debe mantener esas lineas en orden

        urlList = h1.getPhotos ();
        String foto = urlList.get (0);

        viewHolder.cardView.setCardBackgroundColor(Color.parseColor ("#ffab02"));
        viewHolder.nombreLocal.setTextColor (Color.parseColor ("#FFFFFF"));
        viewHolder.direccionLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.tarifaLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.nombreLocal.setText (nombre);
        viewHolder.direccionLocal.setText (direccion);
        viewHolder.tarifaLocal.setText (costeF+tarifa+" ");

//        Si en el json hay un hotel que no tenga el parametro photos
//         arrojara error null object reference

        if ( urlList.size () == 0){
            Glide.with (mContext)
                    .load (R.drawable.fondo_edificio_alterno)
                    .apply (new RequestOptions ()
                            .diskCacheStrategy (DiskCacheStrategy.ALL)
                            .placeholder (R.mipmap.ic_launcher)
                            .centerCrop ()
                    )
                    .into (viewHolder.fotoLocal);
        } else {

            Glide.with (mContext)
                    .load (foto)
                    .apply (new RequestOptions ()
                            .diskCacheStrategy (DiskCacheStrategy.ALL)
                            .placeholder (R.mipmap.ic_launcher)
                            .centerCrop ()
                            .fallback (R.drawable.fondo_edificio_alterno)
                    )
                    .into (viewHolder.fotoLocal);
        }


    }

    @Override
    public int getItemCount() {
        if (listaHotels == null )
            return 0;
        return listaHotels.size ();

    }

    public void addHotelItem(List<Hotels> list){
        listaHotels.addAll (list);
        notifyDataSetChanged ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView fotoLocal;
        private TextView nombreLocal;
        private TextView direccionLocal;
        private TextView tarifaLocal;
        private CardView cardView;
        private MyViewHolderClick mListener;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            cardView = (CardView) itemView.findViewById (R.id.ItemLista) ;
            fotoLocal = (ImageView) itemView.findViewById (R.id.fotoLocal);
            nombreLocal = (TextView) itemView.findViewById (R.id.txtNombreLocal);
            direccionLocal = (TextView) itemView.findViewById (R.id.txtDireccionLocal);
            tarifaLocal = (TextView) itemView.findViewById (R.id.txtTarifaLocal);



        }

        @Override
        public void onClick(View v) {

        }
    }

}
