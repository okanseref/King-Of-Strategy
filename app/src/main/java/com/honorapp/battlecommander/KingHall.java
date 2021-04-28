package com.honorapp.battlecommander;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import javax.xml.transform.Templates;

import static com.honorapp.battlecommander.MainActivity.adSwitch;


/**
 * A simple {@link Fragment} subclass.
 */
public class KingHall extends Fragment {

    Listener mListener;
    TextView ks0,ks1,ks2,ks3,ks4,ks5,ks6;
    public KingHall() {
        // Required empty public constructor
    }
    SharedPreferences sharedPreferences;

    Button home,help,watchad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_king_hall, container, false);
        home=(Button)view.findViewById(R.id.home);
        help=(Button)view.findViewById(R.id.button_help);
        watchad=(Button)view.findViewById(R.id.button_video);

        ks0=(TextView) view.findViewById(R.id.ks0);
        ks1=(TextView) view.findViewById(R.id.ks1);
        ks2=(TextView) view.findViewById(R.id.ks2);
        ks3=(TextView) view.findViewById(R.id.ks3);
        ks4=(TextView) view.findViewById(R.id.ks4);
        ks5=(TextView) view.findViewById(R.id.ks5);
        ks6=(TextView) view.findViewById(R.id.ks6);
        ArrayList<TextView> arr = new ArrayList<>();
        arr.add(ks0);
        arr.add(ks1);
        arr.add(ks2);
        arr.add(ks3);
        arr.add(ks4);
        arr.add(ks5);
        arr.add(ks6);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());

        for(int i=0;i<7;i++){
            if(sharedPreferences.getInt("ks"+i,0)==0){
                arr.get(i).setText(arr.get(i).getText()+" Alive");
                Spannable spannable = new SpannableString(arr.get(i).getText());
                spannable.setSpan(new ForegroundColorSpan(Color.GREEN), String.valueOf(arr.get(i).getText()).indexOf("Alive"), String.valueOf(arr.get(i).getText()).indexOf("Alive") + "Alive".length(),     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                arr.get(i).setText(spannable);
            }else{
                arr.get(i).setText(arr.get(i).getText()+" Dead");
                Spannable spannable = new SpannableString(arr.get(i).getText());
                spannable.setSpan(new ForegroundColorSpan(Color.RED), String.valueOf(arr.get(i).getText()).indexOf("Dead"), String.valueOf(arr.get(i).getText()).indexOf("Dead") + "Dead".length(),     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                arr.get(i).setText(spannable);
            }
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(0);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message();
            }
        });
        watchad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adSwitch=1;
                mListener.changeFragment(99);
                if(sharedPreferences.getBoolean("kingNextFight",false)){
                    watchad.setEnabled(false);
                    watchad.setText("King Ready");
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (KingHall.Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    private void Message(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_msg,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView text=view.findViewById(R.id.text_msg);
        TextView text2=view.findViewById(R.id.text_title);
        text2.setText("Help");
        builder.setCancelable(true);
        text.setText("\tWelcome To King Hall My Liege,\n\nHere is the list of most famous leaders. They may appear due to your level. Also you can check their status if they are alive.");
        TableRow row = view.findViewById(R.id.row_button);
        ImageButton button=(ImageButton)view.findViewById(R.id.tamam_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setView(view);
        builder.show();
        builder.getWindow().setLayout(Resources.getSystem().getDisplayMetrics().widthPixels*8/10, Resources.getSystem().getDisplayMetrics().heightPixels*40/100); //Controlling width and height.

    }

    public interface Listener {
        void changeFragment(int id);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(sharedPreferences.getBoolean("kingNextFight",false)){
            watchad.setEnabled(false);
            watchad.setText("King Appear\nNext Fight");
        }
    }

}
