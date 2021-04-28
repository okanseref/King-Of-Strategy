package com.honorapp.battlecommander;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryItem> {
    public CountryAdapter(Context context, ArrayList<CountryItem>countryList){
        super(context,0,countryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView,  ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_row,parent,false
            );
        }
        TextView textView=convertView.findViewById(R.id.spinner_lay_txt);

        CountryItem currentItem = getItem(position);
        if(currentItem!=null) {
            int konum=currentItem.toString().indexOf("\n");

            final SpannableStringBuilder sb = new SpannableStringBuilder(currentItem.getString());
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
            sb.setSpan(bss, 0, konum, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold

            textView.setText(sb);
        }
        return convertView;
    }
}
