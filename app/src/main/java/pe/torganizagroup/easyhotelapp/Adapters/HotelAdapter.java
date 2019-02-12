package pe.torganizagroup.easyhotelapp.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import pe.torganizagroup.easyhotelapp.Pojo.Hotel;
import pe.torganizagroup.easyhotelapp.R;


public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>{

    private ArrayList<Hotel> dataset;
    private Context context;

    public HotelAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<> ();
    }

//    public HotelAdapter(ArrayList<Hotel> dataset) {
//        this.dataset = dataset;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.item_local, viewGroup, false);
        return new ViewHolder (view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Hotel item = dataset.get (i);
        String costeF = "Desde: S/. ";
//        viewHolder.id.setText (String.valueOf (h.getIdentificador ()));
        if((i % 2) == 0){
            viewHolder.cardView.setBackgroundColor(Color.LTGRAY);
            viewHolder.nombreLocal.setTextColor (Color.MAGENTA);
            viewHolder.tipoLocal.setTextColor (Color.MAGENTA);
            viewHolder.tarifaLocal.setTextColor (Color.MAGENTA);
            viewHolder.direccionLocal.setTextColor (Color.MAGENTA);
        }
        else{
            viewHolder.cardView.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.nombreLocal.setTextColor (Color.WHITE);
            viewHolder.tipoLocal.setTextColor (Color.WHITE);
            viewHolder.tarifaLocal.setTextColor (Color.WHITE);
            viewHolder.direccionLocal.setTextColor (Color.WHITE);
        }


        viewHolder.nombreLocal.setText (item.getNombreEmpresa ());
        viewHolder.tipoLocal.setText (item.getTipoLocal ());
        viewHolder.tarifaLocal.setText (costeF + String.valueOf (item.getTarifaMinima ()));
        viewHolder.direccionLocal.setText (item.getDireccion ());
        Glide.with (context)
                .load ("https://t-organizagroup.com/ws_easyhotel/public/api/imagen/local/"+ item.getNumber ())
                .apply (new RequestOptions ()
//                        .diskCacheStrategy (DiskCacheStrategy.ALL)

                        .centerCrop ()
                        .placeholder (R.mipmap.ic_launcher)
//                        .fallback (R.mipmap.ic_launcher)
                        .fitCenter ()
                        .override (100,110)
                )
                .into(viewHolder.fotoLocal);

    }

    @Override
    public int getItemCount() {
        if(dataset == null)
            return 0;
        return dataset.size ();
    }

    public void adicionarListaHoteles(ArrayList<Hotel> localItems) {
        dataset.addAll (localItems);
        notifyDataSetChanged ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoLocal;
        private TextView nombreLocal;
        private TextView tipoLocal;
        private TextView direccionLocal;
        private TextView tarifaLocal;
        private CardView cardView;
//        private TextView id;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
//            id = (TextView) itemView.findViewById (R.id.txtId);
            cardView = (CardView) itemView.findViewById (R.id.ItemLista) ;
            fotoLocal = (ImageView) itemView.findViewById (R.id.fotoLocal);
            nombreLocal = (TextView) itemView.findViewById (R.id.txtNombreLocal);
            tipoLocal = (TextView) itemView.findViewById (R.id.txtTipoLocal);
            direccionLocal = (TextView) itemView.findViewById (R.id.txtDireccionLocal);
            tarifaLocal = (TextView) itemView.findViewById (R.id.txtTarifaLocal);

        }
    }
}