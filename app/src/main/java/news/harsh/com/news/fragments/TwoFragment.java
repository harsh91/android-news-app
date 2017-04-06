package news.harsh.com.news.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import news.harsh.com.news.R;
import news.harsh.com.news.helpers.Constants;


public class TwoFragment extends Fragment implements View.OnClickListener {

    Button technology, business, photography, cooking, politics, stockmarket, gaming, science, marketing, fashion;
    View view;
    SharedPreferences.Editor editor;
    SharedPreferences editing;

    public TwoFragment() {
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
        view = inflater.inflate(R.layout.fragment_two, container, false);

        editor = getActivity().getSharedPreferences(Constants.INTERESTS_PREFS, 0).edit();
        editing = getActivity().getSharedPreferences(Constants.INTERESTS_PREFS, 0);

        technology = (Button) view.findViewById(R.id.technology);
        business = (Button) view.findViewById(R.id.business);
        photography = (Button) view.findViewById(R.id.photography);
        cooking = (Button) view.findViewById(R.id.cooking);
        politics = (Button) view.findViewById(R.id.politics);
        stockmarket = (Button) view.findViewById(R.id.stockmarket);
        gaming = (Button) view.findViewById(R.id.gaming);
        science = (Button) view.findViewById(R.id.science);
        marketing = (Button) view.findViewById(R.id.marketing);
        fashion = (Button) view.findViewById(R.id.fashion);

        setButtonTransparent();

        technology.setOnClickListener(this);
        business.setOnClickListener(this);
        photography.setOnClickListener(this);
        cooking.setOnClickListener(this);
        politics.setOnClickListener(this);
        stockmarket.setOnClickListener(this);
        gaming.setOnClickListener(this);
        science.setOnClickListener(this);
        marketing.setOnClickListener(this);
        fashion.setOnClickListener(this);

        return view;
    }

    private void setButtonTransparent() {
        formatButtons(technology, Constants.INTEREST_TECHNOLOGY);
        formatButtons(business, Constants.INTEREST_BUSINESS);
        formatButtons(photography, Constants.INTEREST_PHOTOGRAPHY);
        formatButtons(cooking, Constants.INTEREST_COOKING);
        formatButtons(politics, Constants.INTEREST_POLITICS);
        formatButtons(stockmarket, Constants.INTEREST_STOCKMARKET);
        formatButtons(gaming, Constants.INTEREST_GAMING);
        formatButtons(science, Constants.INTEREST_SCIENCE);
        formatButtons(marketing, Constants.INTEREST_MARKETING);
        formatButtons(fashion, Constants.INTEREST_FASHION);
    }

    private void formatButtons(Button button, String interestType) {
        String isClicked = editing.getString(interestType, "");
        String text = button.getText().toString();
        if(!isClicked.trim().equals("")) {
            button.setText(text.substring(0, text.length() - " \n\n\n Add to click".length()));
            button.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            button.getBackground().setAlpha(255);
        } else {
            button.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            button.getBackground().setAlpha(128);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.technology:
                toggleText(technology, Constants.INTEREST_TECHNOLOGY);
                break;
            case R.id.business:
                toggleText(business, Constants.INTEREST_BUSINESS);
                break;
            case R.id.photography:
                toggleText(photography, Constants.INTEREST_PHOTOGRAPHY);
                break;
            case R.id.cooking:
                toggleText(cooking, Constants.INTEREST_COOKING);
                break;
            case R.id.politics:
                toggleText(politics, Constants.INTEREST_POLITICS);
                break;
            case R.id.stockmarket:
                toggleText(stockmarket, Constants.INTEREST_STOCKMARKET);
                break;
            case R.id.gaming:
                toggleText(gaming, Constants.INTEREST_GAMING);
                break;
            case R.id.science:
                toggleText(science, Constants.INTEREST_SCIENCE);
                break;
            case R.id.marketing:
                toggleText(marketing, Constants.INTEREST_MARKETING);
                break;
            case R.id.fashion:
                toggleText(fashion, Constants.INTEREST_FASHION);
                break;
        }
    }

    private void toggleText(Button button, String interestType) {
        String text = button.getText().toString();
        if(text.length() > 20) {
            editor.putString(interestType, "clicked");
            editor.commit();
            button.setText(text.substring(0, text.length() - " \n\n\n Add to click".length()));
            button.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
            button.getBackground().setAlpha(255);
        } else {
            editor.putString(interestType, "");
            editor.commit();
            button.setText(text + " \n\n\n Add to click");
            button.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
            button.getBackground().setAlpha(128);
        }
    }
}
