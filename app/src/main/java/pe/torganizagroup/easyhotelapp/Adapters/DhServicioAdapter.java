package pe.torganizagroup.easyhotelapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.Fragment.hotel_detalle_fragment;
import pe.torganizagroup.easyhotelapp.Pojo.HotelDetails;
import pe.torganizagroup.easyhotelapp.R;

public class DhServicioAdapter extends RecyclerView.Adapter<DhServicioAdapter.ViewHolder> {

    private Context mContext;
    private List<String> service = new ArrayList<> ();
    private hotel_detalle_fragment fragment;

    public DhServicioAdapter(Context mContext, List<String> service, hotel_detalle_fragment fragment) {
        this.mContext = mContext;
        this.service = service;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate(R.layout.dh_item_servicios, viewGroup, false);
        return new ViewHolder (view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String s = service.get (i);

        viewHolder.txtServName.setText (s);

    }

    @Override
    public int getItemCount() {
        if (service == null){
            return 0;
        }
        return service.size ();
    }

    public void addServices(List<String> details) {
        service.addAll (details);
        notifyDataSetChanged ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServName;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            txtServName = (TextView) itemView.findViewById (R.id.txtServName);
        }
    }
}
