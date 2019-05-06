package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.graphics.Color;
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

import pe.torganizagroup.easyhotelapp.DrawableActivity;
import pe.torganizagroup.easyhotelapp.Fragment.hotel_detalle_fragment;
import pe.torganizagroup.easyhotelapp.Fragment.lista_hoteles_fragment;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.R;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {

    private Context mContext;
    private List<Hotels> listaFiltro = new ArrayList<> ();
    private List<String> urlList = new ArrayList<> ();
    private lista_hoteles_fragment fragment;

    public FilterListAdapter(Context mContext, List<Hotels> listaFiltro, lista_hoteles_fragment fragment) {
        this.mContext = mContext;
        this.listaFiltro = listaFiltro;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public FilterListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate (R.layout.item_local, viewGroup,false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterListAdapter.ViewHolder viewHolder, int i) {

        Hotels h1 = listaFiltro.get (i);

        String costeF = "Desde S/. ";

        final String nombre = h1.getNameHotel ();
        final String direccion = h1.getAddress ();
        final String tarifa = h1.getMinimalRate ();
        final String associatedType = h1.getAssociatedType ();

        urlList = h1.getPhotos ();
        String foto = urlList.get (0);

        viewHolder.cardView.setCardBackgroundColor(Color.parseColor ("#ffab02"));
        viewHolder.nombreLocal.setTextColor (Color.parseColor ("#FFFFFF"));
        viewHolder.direccionLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.tarifaLocal.setTextColor (Color.parseColor ("#000000"));

        viewHolder.nombreLocal.setText (nombre);
        viewHolder.direccionLocal.setText (direccion);
        viewHolder.tarifaLocal.setText (costeF+tarifa+" ");

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
        if (listaFiltro == null){
            return 0;
        }
        return listaFiltro.size ();
    }

    public void addHotelFilter(List<Hotels> list){
        listaFiltro.addAll (list);
        notifyDataSetChanged ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        private ImageView fotoLocal;
        private TextView nombreLocal;
        private TextView direccionLocal;
        private TextView tarifaLocal;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            cardView = (CardView) itemView.findViewById (R.id.ItemLista) ;
            fotoLocal = (ImageView) itemView.findViewById (R.id.fotoLocal);
            nombreLocal = (TextView) itemView.findViewById (R.id.txtNombreLocal);
            direccionLocal = (TextView) itemView.findViewById (R.id.txtDireccionLocal);
            tarifaLocal = (TextView) itemView.findViewById (R.id.txtTarifaLocal);

            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    DrawableActivity drawableActivity = (DrawableActivity) mContext;
                    drawableActivity.getSupportFragmentManager ();
                    hotel_detalle_fragment hdf = new hotel_detalle_fragment ();
                    fragment.openHotelDetailFragment (getAdapterPosition (), v.findViewById (R.id.fotoLocal));
                }
            });

        }


    }


}

