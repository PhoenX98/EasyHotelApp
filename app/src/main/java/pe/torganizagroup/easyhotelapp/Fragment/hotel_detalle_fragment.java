package pe.torganizagroup.easyhotelapp.Fragment;


import android.accessibilityservice.AccessibilityService;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.R;

public class hotel_detalle_fragment extends Fragment {

    private TextView txtName,txtAddress,txtPhone,txtDistance,txtPrice;
    String hd_name,hd_address,hd_phoneNumber;
    Integer hd_price,hd_distance;
    private RecyclerView RvS,RvR;
    List<String> hd_photoList = new ArrayList<String> ();

    public hotel_detalle_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        assert getArguments () != null;
        hd_name=getArguments ().getString ("NAME");
        hd_address=getArguments ().getString ("ADDRESS");
        hd_price=getArguments ().getInt ("PRICE");
        hd_photoList=getArguments ().getStringArrayList ("LIST");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_hotel_detalle, container, false);

        RvS = (RecyclerView) v.findViewById (R.id.hd_RvServices);
        RvR = (RecyclerView) v.findViewById (R.id.hd_RvRoom);
        txtName = (TextView) v.findViewById (R.id.hd_txtName);
        txtAddress = (TextView) v.findViewById (R.id.hd_txtAddress);
        txtPrice =(TextView) v.findViewById (R.id.hd_txtPrice);

        final GridLayoutManager LMServices = new GridLayoutManager (getContext (), 3);
        final GridLayoutManager LMRooms = new GridLayoutManager (getContext (), 3);
        LMRooms.setOrientation (LinearLayoutManager.VERTICAL);
        LMServices.setOrientation (LinearLayoutManager.VERTICAL);
        RvS.setLayoutManager (LMServices);
        RvR.setLayoutManager (LMRooms);
        RvS.setHasFixedSize (true);
        RvR.setHasFixedSize (true);
        RvS.setItemAnimator (new DefaultItemAnimator ());
        RvR.setItemAnimator (new DefaultItemAnimator ());

        txtName.setText (hd_name);
        txtAddress.setText (hd_address);
//        txtPhone.setText (hd_phoneNumber);
        txtPrice.setText (hd_price);
        return v;
    }

}
