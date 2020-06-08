package com.dicoding.intifada.sm5.addingmethod;

import android.content.Context;

import com.dicoding.intifada.sm5.R;



public class AllMyStrings {
    public String getNoInet(Context context) {
        return context.getResources().getText(R.string.str_no_internet).toString();
    }

    public String getTryAgain(Context context) {
        return context.getResources().getText(R.string.str_try_again).toString();
    }

    public String getRecon(Context context) {
        return context.getResources().getText(R.string.str_reconnect).toString();
    }

    public String getWrongNet(Context context) {
        return context.getResources().getText(R.string.str_wrong_net).toString();
    }

    public String getWrongErr(Context context) {
        return context.getResources().getText(R.string.str_error_wrong).toString();
    }
}
