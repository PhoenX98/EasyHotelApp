package pe.torganizagroup.easyhotelapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomPageAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;
    private String[] hd_photoList;

    public CustomPageAdapter(Context context, String[] hd_photoList) {
        inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.hd_photoList = hd_photoList;
    }

    @Override
    public int getCount() {
        return hd_photoList.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView (context);

            if(!hd_photoList[position].equals ("")){
                Glide.with (context)
                        .load (hd_photoList[position])
                        .into (imageView);
            }

        container.addView (imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView ((View) object);

    }

}
