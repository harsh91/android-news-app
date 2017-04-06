package news.harsh.com.news.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import news.harsh.com.news.R;
import news.harsh.com.news.activity.IconTabsActivity;
import news.harsh.com.news.helpers.Constants;


public class ThreeFragment extends Fragment {

    RadioButton small, medium, large;
    RadioGroup radioGroup;
    Button saveSettings;
    SharedPreferences.Editor editor;
    SharedPreferences edit;

    public ThreeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        small = (RadioButton) view.findViewById(R.id.small);
        medium = (RadioButton) view.findViewById(R.id.medium);
        large = (RadioButton) view.findViewById(R.id.large);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        saveSettings = (Button) view.findViewById(R.id.saveButton);

        editor = getActivity().getSharedPreferences(Constants.SETTINGS_PREFS, 0).edit();
        edit = getActivity().getSharedPreferences(Constants.SETTINGS_PREFS, 0);
        String selectedZoomLevel = edit.getString(Constants.ZOOM_MODE, "");
        if(!selectedZoomLevel.trim().equals("")) {
            if(selectedZoomLevel.equals(Constants.ZOOM_MODE_LARGE)) {
                large.setChecked(true);
            } else if(selectedZoomLevel.equals(Constants.ZOOM_MODE_MEDIUM)) {
                medium.setChecked(true);
            } else {
                small.setChecked(true);
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.small:
                        editor.putString(Constants.ZOOM_MODE, "small");
                        break;
                    case R.id.medium:
                        editor.putString(Constants.ZOOM_MODE, "medium");
                        break;
                    case R.id.large:
                        editor.putString(Constants.ZOOM_MODE, "large");
                        break;
                }
            }
        });

        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want save new settings?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            editor.commit();
                            Intent intent = new Intent(getActivity().getApplicationContext(), IconTabsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                            }
                            }
                    ).show();
            }
        });

        return view;
    }
}
