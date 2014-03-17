package com.meetville.app_meetville;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by user on 11.03.14.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    Button btnSignUp;
    TextView tvSiginIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetville);
        btnSignUp = (Button) findViewById(R.id.buttonSignUp);
        btnSignUp.setOnClickListener(this);
        tvSiginIn = (TextView) findViewById(R.id.textViewSiginIn);
        Pattern pattern1 = Pattern.compile("Sign in");
        Linkify.addLinks(tvSiginIn, pattern1, "activity4://");

    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSignUp:
                Intent intent = new Intent(this, RegistrationStep1.class);
                if(isOnline()) startActivity(intent);
                else Toast.makeText(this, "does not have a connection to the Internet", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}
