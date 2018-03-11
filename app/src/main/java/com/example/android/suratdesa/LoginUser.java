package com.example.android.suratdesa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.suratdesa.config.SessionManager;
import com.example.android.suratdesa.config.UrlConfig;
import com.example.android.suratdesa.connection.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginUser extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    Button login;

    String username, password, apilogin, berhasil;

    ProgressDialog pDialog;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Status User Login" +
                session.isLoggedIn(), Toast.LENGTH_SHORT).show();



        if (android.os.Build.VERSION.SDK_INT > 16) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.enableDefaults();
        }

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = editTextUsername.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if (username.equals("")) {
                    Toast.makeText(LoginUser.this, "Username tak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginUser.this, "Password tak boleh kosong", Toast.LENGTH_SHORT).show();

                } else {

                    loginRong(username, password);

                }


            }
        });

    }

    public void loginRong(final String username, final String password) {

        pDialog = new ProgressDialog(LoginUser.this);
        pDialog.setMessage("Tunggu...");
        pDialog.setIndeterminate(false);
        pDialog.show();

        apilogin = UrlConfig.LOGIN_USER + username + "&password=" + password;

        StringRequest strReq = new StringRequest(Request.Method.POST, apilogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                            openProfile();

                            JSONParser jParser = new JSONParser();
                            JSONObject json = jParser.getJSONFromUrl(apilogin);
                            try {
                                berhasil = json.getString("berhasil");
                                JSONArray hasil = json.getJSONArray("loginuser");
                                if (berhasil.equals("1")) {
                                    for (int i = 0; i < hasil.length(); i++) {

                                        JSONObject c = hasil.getJSONObject(i);
                                        String nama = c.getString("nama").trim();
                                        session.createLoginSession(nama);

                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        } else {
                            Toast.makeText(LoginUser.this, "Login gagal", Toast.LENGTH_SHORT).show();

                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();

                            new AlertDialog.Builder(LoginUser.this)
                                    .setTitle("Koneksi Jaringan")
                                    .setMessage("Sepertinya jaringan anda bermasalah " +
                                            "Silakan login ulang")
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setIcon(R.mipmap.ic_launcher).show();

                        }
                        NetworkResponse response = error.networkResponse;

                        if (response != null && response.data != null) {
                            Toast.makeText(LoginUser.this, "LOGIN GAGAL", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(strReq);
    }

    private void openProfile() {

        Intent intent = new Intent(LoginUser.this, MenuUtama.class);
        startActivity(intent);
    }
}
