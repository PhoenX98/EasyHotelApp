package pe.torganizagroup.easyhotelapp.Adapters;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.DrawableActivity;
import pe.torganizagroup.easyhotelapp.Pojo.HotelDetails;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.R;

public class ListaHotelAdapter extends RecyclerView.Adapter<ListaHotelAdapter.ViewHolder> {

    private Context mContext;
    private List<Hotels> listaHotels = new ArrayList<> ();


    public ListaHotelAdapter(Context mContext, List<Hotels> listaHotels) {
        this.mContext = mContext;
        this.listaHotels = listaHotels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate (R.layout.item_local, viewGroup,false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Hotels h1 = listaHotels.get (i);
        String costeF = "S/. ";
        String nombre = h1.getNameHotel ();
        Double longitud = Double.valueOf (h1.getLength ());
        Double latitud = Double.valueOf (h1.getLatitude ());
        String direccion = h1.getAddress ();

        viewHolder.cardView.setBackgroundColor(Color.parseColor ("#ffab02"));
        viewHolder.nombreLocal.setTextColor (Color.parseColor ("#FFFFFF"));
        viewHolder.tipoLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.tarifaLocal.setTextColor (Color.parseColor ("#000000"));
        viewHolder.direccionLocal.setTextColor (Color.parseColor ("#000000"));

        viewHolder.nombreLocal.setText (nombre);
        viewHolder.direccionLocal.setText (direccion);

        viewHolder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

//                ((Activity) mContext).finish ();
            }
        });

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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fotoLocal;
        private TextView nombreLocal;
        private TextView tipoLocal;
        private TextView direccionLocal;
        private TextView tarifaLocal;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            cardView = (CardView) itemView.findViewById (R.id.ItemLista) ;
            fotoLocal = (ImageView) itemView.findViewById (R.id.fotoLocal);
            nombreLocal = (TextView) itemView.findViewById (R.id.txtNombreLocal);
            tipoLocal = (TextView) itemView.findViewById (R.id.txtTipoLocal);
            direccionLocal = (TextView) itemView.findViewById (R.id.txtDireccionLocal);
            tarifaLocal = (TextView) itemView.findViewById (R.id.txtTarifaLocal);
        }
    }

}
