package com.poduri.manohar.instagramclone1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Log_In extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText edtName,edtkickpower,edtkickspeed,edtpunchspeed,edtpunchpower;
    private TextView txtgetdata;
    private Button btnGetAllData;
    private String AllKickboxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(Log_In.this);
        edtName = findViewById(R.id.edtName);
        edtkickpower = findViewById(R.id.edtkickpower);
        edtkickspeed = findViewById(R.id.edtkickspeed);
        edtpunchspeed = findViewById(R.id.edtpunchspeed);
        edtpunchpower = findViewById(R.id.edtpunchpower);
        txtgetdata = findViewById(R.id.txtgetdata);
        btnGetAllData = findViewById(R.id.btnGetAllData);

        txtgetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> ParseQuery = com.parse.ParseQuery.getQuery("Kickboxer");
                ParseQuery.getInBackground("BkunYZxYDf", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null) {
                            txtgetdata.setText(object.get("name") + " - " + "Punch Power: " + object.get("kickspeed") );
                        }
                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllKickboxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null) {
                            if (objects.size()>0) {

                                for(ParseObject kickboxer : objects) {

                                    AllKickboxers = AllKickboxers + kickboxer.get("name") + "\n";
                                }

                                FancyToast.makeText(Log_In.this,AllKickboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(Log_In.this,"Warning",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();

                            }
                        }
                    }
                });
            }
        });
    }




    @Override
    public void onClick(View v){

        try {
            final ParseObject Kickboxer = new ParseObject("Kickboxer");
            Kickboxer.put("name", edtName.getText().toString());
            Kickboxer.put("kickpower", Integer.parseInt(edtkickpower.getText().toString()));
            Kickboxer.put("kickspeed", Integer.parseInt(edtkickspeed.getText().toString()));
            Kickboxer.put("punchspeed", Integer.parseInt(edtpunchspeed.getText().toString()));
            Kickboxer.put("punchpower", Integer.parseInt(edtpunchpower.getText().toString()));



            Kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(Log_In.this, Kickboxer.get("name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {
                        FancyToast.makeText(Log_In.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(Log_In.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
