package uk.co.greenturtwig.randomkats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Facts extends Fragment {

    private TextView Fact;
    private String FactText;
    private String Text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facts, container, false);
        Fact = (TextView) view.findViewById(R.id.textView);

        Fact.setText(GetFact());

        Fact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fact.setText(GetFact());
            }
        });

        return view;
    }

    public String GetFact() {
        JSONObject json = null;
        String str = "";
        HttpResponse response;
        HttpClient myClient = new DefaultHttpClient();
        HttpPost myConnection = new HttpPost("http://crossorigin.me/http://catfacts-api.appspot.com/api/facts");

        try {
            response = myClient.execute(myConnection);
            str = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            json = new JSONObject(str);
            Text = (json.getString("facts"));
            Text = Text.substring(2, Text.length()-2);
            Text = Text.replace("\\", "");
            FactText = Text;

        } catch ( JSONException e) {
            //e.printStackTrace();
            Log.e("Error", Log.getStackTraceString(e));
        }

        return FactText;
    }
}