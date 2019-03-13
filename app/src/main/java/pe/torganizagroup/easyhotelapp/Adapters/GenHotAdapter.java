package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
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

import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import pe.torganizagroup.easyhotelapp.R;

public class GenHotAdapter extends RecyclerView.Adapter<GenHotAdapter.ViewHolder>{

    private Context mContext;
    private List<GenHot> hotelsList = new ArrayList<> ();

    public GenHotAdapter(Context mContext, List<GenHot> hotelsList) {
        this.mContext = mContext;
        this.hotelsList = hotelsList;
    }

    @NonNull
    @Override
    public GenHotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_local, viewGroup, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GenHot hotel = hotelsList.get(i);
        String costeF = "Desde: S/. ";
//        viewHolder.title.setText(hotel.getTitle());
        String thumbnail = hotel.getImage ();
        viewHolder.nombreLocal.setText (hotel.getNombreLocal ());
        viewHolder.tipoLocal.setText (hotel.getId ());
        viewHolder.tarifaLocal.setText (costeF + String.valueOf (hotel.getTarifaMinima ()));
        viewHolder.direccionLocal.setText (hotel.getDireccion ());

//        Glide.with(mContext)
//                .load(thumbnail)
//                .into(viewHolder.fotoLocal);
        Glide.with (mContext)
                .load (hotel.getImage ())
                .apply (new RequestOptions ()
                                .diskCacheStrategy (DiskCacheStrategy.ALL)
                                .centerCrop ()
                                .placeholder (R.mipmap.ic_launcher)
//                        .fallback (R.mipmap.ic_launcher)
                                .fitCenter ()
                                .override (100,155)
                )
                .into(viewHolder.fotoLocal);
    }

    @Override
    public int getItemCount() {
        if(hotelsList == null)
            return 0;
        return hotelsList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fotoLocal;
        private TextView nombreLocal;
        private TextView tipoLocal;
        private TextView direccionLocal;
        private TextView tarifaLocal;
        private CardView cardView;

        public ViewHolder(View view){

            super(view);
            cardView = (CardView) itemView.findViewById (R.id.ItemLista) ;
            fotoLocal = (ImageView) itemView.findViewById (R.id.fotoLocal);
            nombreLocal = (TextView) itemView.findViewById (R.id.txtNombreLocal);
            tipoLocal = (TextView) itemView.findViewById (R.id.txtTipoLocal);
            direccionLocal = (TextView) itemView.findViewById (R.id.txtDireccionLocal);
            tarifaLocal = (TextView) itemView.findViewById (R.id.txtTarifaLocal);

//            view.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    int pos = getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION){
//                        Recipe clickedDataItem = recipeList.get(pos);
//
//                        Intent intent = new Intent(mContext, RecipeDetailActivity.class);
//                        intent.putExtra("Recipe", clickedDataItem);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
//                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }
}
