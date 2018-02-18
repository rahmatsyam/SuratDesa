package com.example.android.suratdesa.adapter;

/**
 * Created by user on 21/1/2018.
 */

public class Item {

    private String itemListName;
    private int itemListIcon;

    public Item(String itemName, int itemIcon)
    {
        this.itemListName = itemName;
        this.itemListIcon = itemIcon;
    }

    String getitemName()
    {
        return itemListName;
    }

    int getitemIcon()
    {
        return itemListIcon;
    }

}
