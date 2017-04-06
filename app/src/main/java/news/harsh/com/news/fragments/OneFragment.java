package news.harsh.com.news.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ListView;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

import news.harsh.com.news.R;
import news.harsh.com.news.activity.NewsDetailActivity;
import news.harsh.com.news.helpers.Constants;


public class OneFragment extends Fragment implements View.OnClickListener {

    LinearLayout news1, news2, news3, news4, news5, news6;
    ImageView topNews;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("One Fragment", " onCreateView!!");
        SharedPreferences prefs = getActivity().getSharedPreferences(Constants.SETTINGS_PREFS, 0);
        String mode = prefs.getString(Constants.ZOOM_MODE, "");
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ArrayList<String> items = new ArrayList<String>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");

        if(mode.equals("medium")) {
            view = inflater.inflate(R.layout.fragment_one_medium, container, false);
        } else if(mode.equals("large")) {
            view = inflater.inflate(R.layout.fragment_one_large, container, false);
        }
        topNews = (ImageView) view.findViewById(R.id.topNews);
        news1 = (LinearLayout) view.findViewById(R.id.news1);
        news2 = (LinearLayout) view.findViewById(R.id.news2);
        news3 = (LinearLayout) view.findViewById(R.id.news3);
        news4 = (LinearLayout) view.findViewById(R.id.news4);
        news5 = (LinearLayout) view.findViewById(R.id.news5);
        news6 = (LinearLayout) view.findViewById(R.id.news6);


        if (topNews!= null) topNews.setOnClickListener(this);
        news1.setOnClickListener(this);
        news2.setOnClickListener(this);
        news3.setOnClickListener(this);
        news4.setOnClickListener(this);
        news5.setOnClickListener(this);
        news6.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), NewsDetailActivity.class);
        startActivity(intent);
    }
}
