package com.example.android.suratdesa;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.suratdesa.adapter.CustomAdapter;
import com.example.android.suratdesa.adapter.Item;

import java.util.ArrayList;


public class MenuUtama extends AppCompatActivity {

    GridView gridView;
    Button btn_ok;
    android.support.v7.widget.Toolbar mytoolbar;
    TextView text_info;


    LayoutInflater inflater;
    AlertDialog.Builder Builder;
    View dialogView;

    ArrayList<Item> itemList = new ArrayList<>();
    private int pindah;
    private static final CharSequence[] items = {"Surat Domisili", "Surat Kelakuan Baik", "Surat Kurang Mampu",
            "Surat Izin Keramaian", "Surat Nikah", "Surat Usaha"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        gridView = findViewById(R.id.grid);

        itemList.add(new Item("Profil", R.drawable.icon_user1));
        itemList.add(new Item("Buat Surat", R.drawable.icon_user2));
        itemList.add(new Item("Riwayat", R.drawable.icon_user3));
        itemList.add(new Item("Kontak", R.drawable.icon_user4));
        itemList.add(new Item("Kotak Masuk", R.drawable.icon_user5));


        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.layout_item, itemList);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        Intent profil = new Intent(getApplicationContext(), ProfilUser.class);
                        startActivity(profil);
                        break;
                    case 1:
                        showDialogSurat();
                        break;
                    case 2:
                        Intent riwayat = new Intent(getApplicationContext(),Riwayat.class);
                        startActivity(riwayat);
                        break;
                    case 3:
                        dialogKontak();
                        break;
                    case 4:
                        Intent inbox = new Intent(getApplicationContext(), KotakMasuk.class);
                        startActivity(inbox);
                        break;
                    default:
                        break;
                }
            }
        });

    }


    public void showDialogSurat() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilihan Surat");
        builder.setCancelable(false);

        final int checkedItem = -1;
        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if (items[which] == "Surat Domisili") {
                    pindah = 1;
                } else if (items[which] == "Surat Kelakuan Baik") {
                    pindah = 2;
                } else if (items[which] == "Surat Kurang Mampu") {
                    pindah = 3;

                } else if (items[which] == "Surat Izin Keramaian") {
                    pindah = 4;

                } else if (items[which] == "Surat Nikah") {
                    pindah = 5;

                } else if (items[which] == "Surat Usaha") {
                    pindah = 6;
                }


            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                switch (pindah) {


                    case 1:
                        Intent intent = new Intent(getApplicationContext(), SuratDomisili.class);
                        startActivity(intent);
                        break;

                    case 2:
                        Intent intent1 = new Intent(getApplicationContext(), SuratKelakuanBaik.class);
                        startActivity(intent1);
                        break;

                    case 3:
                        Intent intent2 = new Intent(getApplicationContext(), SuratKurangMampu.class);
                        startActivity(intent2);
                        break;

                    case 4:
                        Intent intent3 = new Intent(getApplicationContext(), SuratIzinKeramaian.class);
                        startActivity(intent3);
                        break;

                    case 5:
                        Intent intent4 = new Intent(getApplicationContext(), SuratNikah.class);
                        startActivity(intent4);
                        break;
                    case 6:
                        Intent intent5 = new Intent(getApplicationContext(), SuratUsaha.class);
                        startActivity(intent5);
                        break;

                    default:
                        dialogInterface.dismiss();
                        break;
                }


            }
        });

        builder.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @SuppressLint("InflateParams")
    public void dialogKontak() {
        Builder = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_kontak, null);
        Builder.setView(dialogView);

        Builder.setCancelable(false);
        final AlertDialog alertDialog = Builder.show();

        mytoolbar = dialogView.findViewById(R.id.my_toolbar);

        mytoolbar.setTitle("Info Kontak");
        mytoolbar.setLogo(R.mipmap.ic_launcher_round);
        text_info = dialogView.findViewById(R.id.txt_info);

        btn_ok = dialogView.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedButton(alertDialog);
            }
        });


    }

    private void onClickedButton(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }


}
