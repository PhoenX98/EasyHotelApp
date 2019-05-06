package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.Fragment.hotel_detalle_fragment;
import pe.torganizagroup.easyhotelapp.R;

public class DhHabAdapter extends RecyclerView.Adapter<DhHabAdapter.ViewHolder> {

    private Context mContext;
    private List<String> service = new ArrayList<> ();
    private hotel_detalle_fragment fragment;

    public DhHabAdapter(Context mContext, List<String> service, hotel_detalle_fragment fragment) {
        this.mContext = mContext;
        this.service = service;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate(R.layout.dh_item_hab_icon, viewGroup, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            String strings = service.get (i);

            viewHolder.txtHabName.setText (strings);
    }

    @Override
    public int getItemCount() {
        if (service == null){
            return 0;
        }
        return service.size ();
    }

    public void addServices(List<String> list){
        service.addAll (list);
        notifyDataSetChanged ();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgHab;
        private TextView txtHabName;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            imgHab = (ImageView) itemView.findViewById (R.id.imgHab);
            txtHabName = (TextView) itemView.findViewById (R.id.txtServName);
        }
    }
}
