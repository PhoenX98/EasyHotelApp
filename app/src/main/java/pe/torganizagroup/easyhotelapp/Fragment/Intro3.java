package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.paolorotolo.appintro.ISlidePolicy;

import pe.torganizagroup.easyhotelapp.R;


public class Intro3 extends Fragment  {

    TextView t1,t2;

    public Intro3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_intro3, container, false);

        TextView t1 = (TextView) v.findViewById (R.id.txtmuestra1);
        TextView t2 = (TextView) v.findViewById (R.id.txtmuestra2);

        t1.setMovementMethod (LinkMovementMethod.getInstance ());
        t2.setMovementMethod (LinkMovementMethod.getInstance ());



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

}
