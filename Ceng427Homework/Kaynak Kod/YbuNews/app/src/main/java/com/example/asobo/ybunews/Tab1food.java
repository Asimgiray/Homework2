package com.example.asobo.ybunews;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by asobo on 1.05.2018.
 */

public class Tab1food extends Fragment {

    public TextView food;
    String link = "http://ybu.edu.tr/sks";
    ArrayList<String> foodList;
    public ProgressDialog PrgDlg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.tab1food, container, false);
        food=(TextView) view.findViewById(R.id.foodtext);
        new getWebSite().execute();
        return view;
    }

    private class getWebSite extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodList = new ArrayList<String>();
            try {
                URL link = new URL("http://ybu.edu.tr/sks");
                Document doc = Jsoup.parse(link,3000);
                Element table = doc.select("table").first();

                Iterator<Element> itrtr = table.select("td").iterator();

                itrtr.next();
                while(itrtr.hasNext()){
                    foodList.add(itrtr.next().text());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }





        @Override
        protected void onPostExecute(Void aVoid) {
            String a="";
            for(int i=0;i<foodList.size();i++){
                a=a+"\n"+foodList.get(i).toString();
            }
            food.setText(a);
        }
    }
}
