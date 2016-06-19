package uk.co.greenturtwig.randomkats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felipecsl.gifimageview.library.GifImageView;
import com.koushikdutta.ion.Ion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GIFs extends Fragment {

    private GifImageView GIF;
    private String URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gifs, container, false);

        GIF = (GifImageView) view.findViewById(R.id.imageView);

        Ion.with(GIF)
                .load(GetURL());

        GIF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ion.with(GIF)
                        .load(GetURL());
            }
        });

        return view;
    }

    public String GetURL() {
        try {
            Document doc = Jsoup.connect("http://edgecats.net/random").get();
            Elements body = doc.select("body");
            for (Element b : body) {
                URL = b.text();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return URL;
    }
}