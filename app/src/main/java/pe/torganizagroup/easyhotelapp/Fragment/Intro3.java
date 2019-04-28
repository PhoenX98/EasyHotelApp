package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.paolorotolo.appintro.ISlidePolicy;

import pe.torganizagroup.easyhotelapp.R;


public class Intro3 extends Fragment implements ISlidePolicy  {

    TextView t1,t2;
    CheckBox ch;
    Boolean Dex = false;
    public Intro3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_intro3, container, false);

        TextView t1 = (TextView) v.findViewById (R.id.txtmuestra1);
        ch = (CheckBox) v.findViewById (R.id.SuperCheck);
//        ch.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                   isPolicyRespected ();
//
//                }
//            }
//        });
        t1.setMovementMethod (LinkMovementMethod.getInstance ());



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

    @Override
    public boolean isPolicyRespected() {
//        final Boolean Dex =false;
        ch.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Dex = true;
                }else {
                    Dex = false;
                }
            }
        });
        return Dex;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {

    }
}
