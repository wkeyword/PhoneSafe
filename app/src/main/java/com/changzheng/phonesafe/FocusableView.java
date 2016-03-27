package com.changzheng.phonesafe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.widget.TextView;

import java.util.jar.Attributes;

/**
 * Created by changzheng on 16/3/27.
 */
public class FocusableView extends TextView {
    public FocusableView(Context context){
        super(context);
    }

    public FocusableView(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
    }

    public FocusableView(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    @ViewDebug.ExportedProperty(category = "focus")
    public boolean isFocused() {
        return true;
    }
}
