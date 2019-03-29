package pe.torganizagroup.easyhotelapp.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import java.util.Objects;
import static com.bumptech.glide.load.resource.gif.GifDrawable.LOOP_INTRINSIC;
import pe.torganizagroup.easyhotelapp.R;

public class Intro1 extends Fragment {

    TextView t1,t2,t3;
//    String URL = "https://media.giphy.com/media/20k1punZ5bpmM/giphy.gif";
    ImageView Gif;

    public Intro1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate (R.layout.fragment_intro1, container, false);

        ImageView Gif = (ImageView) v.findViewById (R.id.imgGif);

        Glide.with (Objects.requireNonNull (getContext ()))
                .load (R.drawable.animacion_easy_hotel_intro)
                .apply (new RequestOptions ()
//                .fitCenter ()
                .diskCacheStrategy (DiskCacheStrategy.ALL)
                )
                .listener (new RequestListener<Drawable> () {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        resource.setLoopCount(LOOP_INTRINSIC);
                        return false;
                    }
                }
                )
                .into (Gif);
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
    }

}
