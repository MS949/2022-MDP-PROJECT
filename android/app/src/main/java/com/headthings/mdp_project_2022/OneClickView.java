package com.headthings.mdp_project_2022;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class OneClickView extends LinearLayout implements CompoundButton.OnCheckedChangeListener {

    private LinearLayout bg;
    private ImageView symbol;
    private TextView text;
    private Switch power;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener;

    public OneClickView(Context context) {
        super(context);
        initView();
    }

    public OneClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public OneClickView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }


    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.layout_esset, this, false);
        addView(v);

        bg = findViewById(R.id.bg);
        symbol = findViewById(R.id.symbol);
        text = findViewById(R.id.text);
        power = findViewById(R.id.power);

        power.setOnCheckedChangeListener(this);
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mListener) {
        mOnCheckedChangeListener = mListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mOnCheckedChangeListener.onCheckedChanged(compoundButton, b);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.OneClickView);
        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.OneClickView, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int bg_resID = typedArray.getResourceId(R.styleable.OneClickView_bg, R.drawable.shape_oval_black);
        bg.setBackgroundResource(bg_resID);

        int symbol_resID = typedArray.getResourceId(R.styleable.OneClickView_symbol, 0);
        symbol.setImageResource(symbol_resID);

        int textColor = typedArray.getColor(R.styleable.OneClickView_textColor, 0);
        text.setTextColor(textColor);

        String text_string = typedArray.getString(R.styleable.OneClickView_text);
        text.setText(text_string);

        boolean isPower = typedArray.getBoolean(R.styleable.OneClickView_isPower, false);
        power.setChecked(isPower);

        typedArray.recycle();
    }

    public void setPower(boolean isPower) {
        power.setChecked(isPower);
    }

    public void setBg(int bg_resID) {
        bg.setBackgroundResource(bg_resID);
    }

    public void setSymbol(int symbol_resID) {
        symbol.setImageResource(symbol_resID);
    }

    public void setTextColor(int color) {
        text.setTextColor(color);
    }

    public void setText(String text_string) {
        text.setText(text_string);
    }

    public void setText(int text_resID) {
        text.setText(text_resID);
    }
}