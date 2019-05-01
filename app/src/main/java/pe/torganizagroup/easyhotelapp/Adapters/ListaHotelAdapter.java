package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.Fragment.hotel_detalle_fragment;
import pe.torganizagroup.easyhotelapp.Interfaces.FragmentComunicator;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.R;

public class ListaHotelAdapter extends RecyclerView.Adapter<ListaHotelAdapter.ViewHolder> {

//    private View.OnClickListener onItemClickListener;
//    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private LayoutInflater inflater;
    private FragmentComunicator mComunicator;
    private Context mContext;
    private List<Hotels> listaHotels = new ArrayList<> ();
    private List<String> urlList = new ArrayList<> ();
//,  @NonNull RecyclerViewOnItemClickListener
//            recyclerViewOnItemClickListener
    public ListaHotelAdapter(Context mContext, List<Hotels> listaHotels, FragmentComunicator comunicator) {
        inflater = LayoutInflater.from (mContext);
        this.mContext = mContext;
        this.listaHotels = listaHotels;
        this.mComunicator = comunicator;
    }

//    public void setItemClickListener(View.OnClickListener clickListener) {
//        onItemClickListener = clickListener;
//    }
//this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate (R.layout.item_local, viewGroup,false);
        ViewHolder holder = new ViewHolder (view,mComunicator);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Hotels h1 = listaHotels.get (i);
        String costeF = "Desde S/. ";
        String nombre = h1.getNameHotel ();
        String direccion = h1.getAddress ();
        String tarifa = h1.getMinimalRate ();
        urlList = h1.getPhotos ();

        viewHolder.cardView.setCardBackgroundColor(Color.parseColor ("#ffab02"));
        viewHolder.nombreLocal.setTextColor (Color.parseColor ("#FFFFFF"));
        viewHolder.direccionLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.tarifaLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.nombreLocal.setText (nombre);
        viewHolder.direccionLocal.setText (direccion);
        viewHolder.tarifaLocal.setText (costeF+tarifa+" ");

        hotel_detalle_fragment HDF =new hotel_detalle_fragment ();
        Bundle bundleLHA = new Bundle ();
        bundleLHA.putString ("NAME",nombre);
        bundleLHA.putString ("ADDRESS",direccion);
        bundleLHA.putString ("PRICE",tarifa);
        bundleLHA.putStringArrayList ("LIST", (ArrayList<String>) urlList);
        HDF.setArguments (bundleLHA);
//        viewHolder.itemView.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
////                ((Activity) mContext).finish ();
//                Toast.makeText (mContext,"Probando invocacion, meter metodo aca",Toast.LENGTH_SHORT).show ();
//            }
//        });

        //Si en el json hay un hotel que no tenga el parametro photos arrojara error null object reference

//        if(urlList.size ()==0){
            Glide.with (mContext)
                    .load (R.drawable.fondo_edificio_alterno)
                    .apply (new RequestOptions ()
                            .diskCacheStrategy (DiskCacheStrategy.ALL)
                            .placeholder (R.mipmap.ic_launcher)
                            .centerCrop ()
                    )
                    .into (viewHolder.fotoLocal);
//        }else{
//            String foto = urlList.get (0);
//            Glide.with (mContext)
//                    .load (foto)
//                    .apply (new RequestOptions ()
//                            .diskCacheStrategy (DiskCacheStrategy.ALL)
//                            .placeholder (R.mipmap.ic_launcher)
//                            .centerCrop ()
//                            .fallback (R.drawable.fondo_edificio_alterno)
//                    )
//                    .into (viewHolder.fotoLocal);
//        }

//        notifyDataSetChanged ();

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
//implements RecyclerViewOnItemClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView fotoLocal;
        private TextView nombreLocal;
        private TextView direccionLocal;
        private TextView tarifaLocal;
        private CardView cardView;
        FragmentComunicator mCommunication;

        public ViewHolder(@NonNull View itemView,FragmentComunicator Comunicator) {
            super (itemView);

            cardView = (CardView) itemView.findViewById (R.id.ItemLista) ;
            fotoLocal = (ImageView) itemView.findViewById (R.id.fotoLocal);
            nombreLocal = (TextView) itemView.findViewById (R.id.txtNombreLocal);
            direccionLocal = (TextView) itemView.findViewById (R.id.txtDireccionLocal);
            tarifaLocal = (TextView) itemView.findViewById (R.id.txtTarifaLocal);
            mCommunication = Comunicator;
            fotoLocal.setOnClickListener (this);
//            itemView.setTag (this);
//            itemView.setOnClickListener (onItemClickListener);
        }

    @Override
    public void onClick(View v) {
        mCommunication.respond (
                getAdapterPosition (),
                listaHotels.get (getAdapterPosition ()).getNameHotel (),
                listaHotels.get (getAdapterPosition ()).getAddress (),
                listaHotels.get (getAdapterPosition ()).getMinimalRate (),
                listaHotels.get (getAdapterPosition ()).getPhotos ());
    }


//        @Override
//        public void onClick(View v, int position) {
//            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
//        }
    }

}
