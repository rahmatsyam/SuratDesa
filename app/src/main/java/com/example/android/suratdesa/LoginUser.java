package com.example.android.suratdesa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUser extends AppCompatActivity {
   private EditText editTextUsername,editTextPassword;
    Button login;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = editTextUsername.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if (username.equals("")) {
                  Toast.makeText(LoginUser.this,"Username tak boleh kosong",Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginUser.this,"Password tak boleh kosong",Toast.LENGTH_SHORT).show();

                } else {

                    loginRong(username, password);

                }


            }
        });

    }

    public void loginRong(final String username,final String password    ){
        Intent intent = new Intent(LoginUser.this,MenuUtama.class);
        startActivity(intent);
    }
}
