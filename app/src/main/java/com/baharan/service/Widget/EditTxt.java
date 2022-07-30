package com.baharan.service.Widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class EditTxt extends AppCompatEditText {
    public EditTxt(Context context) {
        super(context);
        init(context);
    }

    public EditTxt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "isans.ttf");
        setTypeface(typeface);

    }

}

