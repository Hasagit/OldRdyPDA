package com.rdypda.presenter;

import android.content.Context;
import android.content.Intent;

import com.rdypda.view.activity.GdxqActivity;
import com.rdypda.view.activity.LlddrActivity;
import com.rdypda.view.activity.LoginActivity;
import com.rdypda.view.activity.YljsActivity;
import com.rdypda.view.viewinterface.IMainView;

/**
 * Created by DengJf on 2017/12/8.
 */

public class MainPresenter extends BasePresenter{
    private IMainView view;


    public MainPresenter(Context context,IMainView view) {
        super(context);
        this.view = view;
    }


    public void goToLogin(){
        Intent intent=new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void goToLlddr(){
        Intent intent=new Intent(context, LlddrActivity.class);
        context.startActivity(intent);
    }

    public void goToYljs(){
        Intent intent=new Intent(context, YljsActivity.class);
        context.startActivity(intent);
    }

    public void goToGdxq(){
        Intent intent=new Intent(context, GdxqActivity.class);
        context.startActivity(intent);
    }
}
