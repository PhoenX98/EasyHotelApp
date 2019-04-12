package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.torganizagroup.easyhotelapp.R;

public class DhServicioAdapter extends RecyclerView.Adapter<DhServicioAdapter.ViewHolder> {

    private Context mContext;

    public DhServicioAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (mContext);
        View view = inflater.inflate(R.layout.dh_item_servicios, viewGroup, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServName;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            txtServName = (TextView) itemView.findViewById (R.id.txtServName);
        }
    }
}
