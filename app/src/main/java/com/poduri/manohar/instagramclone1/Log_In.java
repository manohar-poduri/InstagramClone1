package com.poduri.manohar.instagramclone1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Log_In extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText edtName,edtkickpower,edtkickspeed,edtpunchspeed,edtpunchpower;

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



    }

    @Override
    public void onClick(View view) {

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
