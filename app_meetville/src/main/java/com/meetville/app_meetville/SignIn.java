package com.meetville.app_meetville;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;
import android.view.View.OnClickListener;

/**
 * Created by user on 11.03.14.
 */
public class SignIn extends Activity implements OnClickListener {
    EditText editTextEmail;
    EditText editTextPassword;
    Button btnSignIn;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignIn:
                password = editTextPassword.getText().toString();
                email = editTextEmail.getText().toString();
                Toast.makeText(this, "password: " + password + " email: " + email , Toast.LENGTH_LONG).show();
        }
    }
}
