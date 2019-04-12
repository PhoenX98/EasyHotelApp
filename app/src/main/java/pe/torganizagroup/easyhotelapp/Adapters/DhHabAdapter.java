package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pe.torganizagroup.easyhotelapp.R;

public class DhHabAdapter extends RecyclerView.Adapter<DhHabAdapter.ViewHolder> {

    private Context mContext;

    public DhHabAdapter(Context mContext) {
        this.mContext = mContext;
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

    }

    @Override
    public int getItemCount() {
        return 0;
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
