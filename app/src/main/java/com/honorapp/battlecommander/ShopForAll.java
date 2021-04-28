package com.honorapp.battlecommander;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;


import org.w3c.dom.Text;

import static com.honorapp.battlecommander.AnaMenu.shopSwitch;
import static com.honorapp.battlecommander.MainActivity.adSwitch;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopForAll extends Fragment {
    public ShopForAll() {
    // Required empty public constructor
    }
    Listener mListener;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    TableRow r1,r2,r3,r4,r5;
    TextView tx1,tx2,tx3,tx4,tx5,pr1,pr2,pr3,pr4,pr5,balance_text,shop_name;
    ImageView shop_image;
    int selection=0;
    Button b1,b2,b3;
    int balance;
    boolean x1=true,x2=true,x3=true,x4=true,x5=true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_shop_for_all, container, false);
        final int[] prices={150,170,220,190,230,
                        210,190,180,220,170,
                         250,220,240,220,225,
                          150,120,110,120,125,
                          220,185,190,200,200,
                          415,400,410,400,420};
        tx1=(TextView) view.findViewById(R.id.textView9);
        tx2=(TextView) view.findViewById(R.id.textView11);
        tx3=(TextView) view.findViewById(R.id.textView13);
        tx4=(TextView) view.findViewById(R.id.textView15);
        tx5=(TextView) view.findViewById(R.id.textView17);
        pr1=(TextView) view.findViewById(R.id.textView10);
        pr2=(TextView) view.findViewById(R.id.textView12);
        pr3=(TextView) view.findViewById(R.id.textView14);
        pr4=(TextView) view.findViewById(R.id.textView16);
        pr5=(TextView) view.findViewById(R.id.textView18);
        shop_name=(TextView) view.findViewById(R.id.shop_name);
        balance_text=(TextView) view.findViewById(R.id.balance_text);
        shop_image=(ImageView) view.findViewById(R.id.shop_image);

        b1=(Button) view.findViewById(R.id.button_home);
        b2=(Button) view.findViewById(R.id.button_video);
        b3=(Button) view.findViewById(R.id.button_buy);

        r1=(TableRow) view.findViewById(R.id.row1);
        r2=(TableRow) view.findViewById(R.id.row2);
        r3=(TableRow) view.findViewById(R.id.row3);
        r4=(TableRow) view.findViewById(R.id.row4);
        r5=(TableRow) view.findViewById(R.id.row5);


        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5),0)==0) {
                    selection = 1;
                }else{
                    selection=0;
                }
                r1.setBackgroundResource(R.drawable.buttonspecial2);
                r2.setBackgroundResource(R.drawable.buttonspecial);
                r3.setBackgroundResource(R.drawable.buttonspecial);
                r4.setBackgroundResource(R.drawable.buttonspecial);
                r5.setBackgroundResource(R.drawable.buttonspecial);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+1),0)==0) {
                    selection = 2;
                }else{
                    selection=0;
                }
                r1.setBackgroundResource(R.drawable.buttonspecial);
                r2.setBackgroundResource(R.drawable.buttonspecial2);
                r3.setBackgroundResource(R.drawable.buttonspecial);
                r4.setBackgroundResource(R.drawable.buttonspecial);
                r5.setBackgroundResource(R.drawable.buttonspecial);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+2),0)==0) {
                    selection = 3;
                }else{
                    selection=0;
                }
                r1.setBackgroundResource(R.drawable.buttonspecial);
                r2.setBackgroundResource(R.drawable.buttonspecial);
                r3.setBackgroundResource(R.drawable.buttonspecial2);
                r4.setBackgroundResource(R.drawable.buttonspecial);
                r5.setBackgroundResource(R.drawable.buttonspecial);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+3),0)==0) {
                    selection = 4;
                }else{
                    selection=0;
                }
                r1.setBackgroundResource(R.drawable.buttonspecial);
                r2.setBackgroundResource(R.drawable.buttonspecial);
                r3.setBackgroundResource(R.drawable.buttonspecial);
                r4.setBackgroundResource(R.drawable.buttonspecial2);
                r5.setBackgroundResource(R.drawable.buttonspecial);
            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+4),0)==0) {
                    selection = 5;
                }else{
                    selection=0;
                }
                r1.setBackgroundResource(R.drawable.buttonspecial);
                r2.setBackgroundResource(R.drawable.buttonspecial);
                r3.setBackgroundResource(R.drawable.buttonspecial);
                r4.setBackgroundResource(R.drawable.buttonspecial);
                r5.setBackgroundResource(R.drawable.buttonspecial2);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(0);
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        balance=sharedPreferences.getInt("balance",50);
        balance_text.setText("x "+balance);
        setIconInText(balance_text);
        switch(shopSwitch){
            case 1:
                tx1.setText(R.string.buff0_1);
                tx2.setText(R.string.buff0_2);
                tx3.setText(R.string.buff0_3);
                tx4.setText(R.string.buff0_4);
                tx5.setText(R.string.buff0_5);
                pr1.setText("x "+String.valueOf(prices[0]));
                pr2.setText("x "+String.valueOf(prices[1]));
                pr3.setText("x "+String.valueOf(prices[2]));
                pr4.setText("x "+String.valueOf(prices[3]));
                pr5.setText("x "+String.valueOf(prices[4]));
                shop_name.setText("Archer Upgrades");
                shop_image.setImageResource(R.drawable.archer_ic);

                break;
            case 2:
                tx1.setText(R.string.buff1_1);
                tx2.setText(R.string.buff1_2);
                tx3.setText(R.string.buff1_3);
                tx4.setText(R.string.buff1_4);
                tx5.setText(R.string.buff1_5);
                pr1.setText("x "+String.valueOf(prices[5]));
                pr2.setText("x "+String.valueOf(prices[6]));
                pr3.setText("x "+String.valueOf(prices[7]));
                pr4.setText("x "+String.valueOf(prices[8]));
                pr5.setText("x "+String.valueOf(prices[9]));
                shop_name.setText("Swordsman Upgrades");
                shop_image.setImageResource(R.drawable.sword_ic);
                break;
            case 3:
                tx1.setText(R.string.buff2_1);
                tx2.setText(R.string.buff2_2);
                tx3.setText(R.string.buff2_3);
                tx4.setText(R.string.buff2_4);
                tx5.setText(R.string.buff2_5);
                pr1.setText("x "+String.valueOf(prices[10]));
                pr2.setText("x "+String.valueOf(prices[11]));
                pr3.setText("x "+String.valueOf(prices[12]));
                pr4.setText("x "+String.valueOf(prices[13]));
                pr5.setText("x "+String.valueOf(prices[14]));
                shop_name.setText("Horseman Upgrades");
                shop_image.setImageResource(R.drawable.horse_ic);
                break;
            case 4:
                tx1.setText(R.string.buff3_1);
                tx2.setText(R.string.buff3_2);
                tx3.setText(R.string.buff3_3);
                tx4.setText(R.string.buff3_4);
                tx5.setText(R.string.buff3_5);
                pr1.setText("x "+String.valueOf(prices[15]));
                pr2.setText("x "+String.valueOf(prices[16]));
                pr3.setText("x "+String.valueOf(prices[17]));
                pr4.setText("x "+String.valueOf(prices[18]));
                pr5.setText("x "+String.valueOf(prices[19]));
                shop_name.setText("Basic Tactics");
                shop_image.setImageResource(R.drawable.basic_tactics);
                break;
            case 5:
                tx1.setText(R.string.buff4_1);
                tx2.setText(R.string.buff4_2);
                tx3.setText(R.string.buff4_3);
                tx4.setText(R.string.buff4_4);
                tx5.setText(R.string.buff4_5);
                pr1.setText("x "+String.valueOf(prices[20]));
                pr2.setText("x "+String.valueOf(prices[21]));
                pr3.setText("x "+String.valueOf(prices[22]));
                pr4.setText("x "+String.valueOf(prices[23]));
                pr5.setText("x "+String.valueOf(prices[24]));
                shop_name.setText("Improved Tactics");
                shop_image.setImageResource(R.drawable.improved_tactics);
                break;
            case 6:
                tx1.setText(R.string.buff5_1);
                tx2.setText(R.string.buff5_2);
                tx3.setText(R.string.buff5_3);
                tx4.setText(R.string.buff5_4);
                tx5.setText(R.string.buff5_5);
                pr1.setText("x "+String.valueOf(prices[25]));
                pr2.setText("x "+String.valueOf(prices[26]));
                pr3.setText("x "+String.valueOf(prices[27]));
                pr4.setText("x "+String.valueOf(prices[28]));
                pr5.setText("x "+String.valueOf(prices[29]));
                shop_name.setText("Advanced Tactics");
                shop_image.setImageResource(R.drawable.advanced_tactics);
                break;
        }
        setIconInText(pr1);
        setIconInText(pr2);
        setIconInText(pr3);
        setIconInText(pr4);
        setIconInText(pr5);

        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5),0)==1){x1=false;}
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+1),0)==1){x2=false;}
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+2),0)==1){x3=false;}
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+3),0)==1){x4=false;}
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+4),0)==1){x5=false;}


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selection==1&&balance>=prices[(shopSwitch-1)*5+selection-1]){
                    setIconInTextTick(pr1);

                    balance-=prices[(shopSwitch-1)*5+selection-1];
                    balance_text.setText("x "+String.valueOf(balance));
                    setIconInText(balance_text);
                    editor=sharedPreferences.edit();
                    editor.putInt(String.valueOf((shopSwitch-1)*5),1);
                    editor.putInt("balance",balance);
                    editor.apply();
                    selection=0;
                }
                if(selection==2&&balance>=prices[(shopSwitch-1)*5+selection-1]){
                    setIconInTextTick(pr2);

                    balance-=prices[(shopSwitch-1)*5+selection-1];
                    balance_text.setText("x "+String.valueOf(balance));
                    setIconInText(balance_text);
                    editor=sharedPreferences.edit();
                    editor.putInt(String.valueOf((shopSwitch-1)*5+1),1);
                    editor.putInt("balance",balance);
                    editor.apply();
                    selection=0;
                }
                if(selection==3&&balance>=prices[(shopSwitch-1)*5+selection-1]){
                    setIconInTextTick(pr3);

                    balance-=prices[(shopSwitch-1)*5+selection-1];
                    balance_text.setText("x "+String.valueOf(balance));
                    setIconInText(balance_text);
                    editor=sharedPreferences.edit();
                    editor.putInt(String.valueOf((shopSwitch-1)*5+2),1);
                    editor.putInt("balance",balance);
                    editor.apply();
                    selection=0;
                }
                if(selection==4&&balance>=prices[(shopSwitch-1)*5+selection-1]){
                    setIconInTextTick(pr4);

                    balance-=prices[(shopSwitch-1)*5+selection-1];
                    balance_text.setText("x "+String.valueOf(balance));
                    setIconInText(balance_text);
                    editor=sharedPreferences.edit();
                    editor.putInt(String.valueOf((shopSwitch-1)*5+3),1);
                    editor.putInt("balance",balance);
                    editor.apply();
                    selection=0;
                }
                if(selection==5&&balance>=prices[(shopSwitch-1)*5+selection-1]){
                    setIconInTextTick(pr5);
                    x5=false;

                    balance-=prices[(shopSwitch-1)*5+selection-1];
                    balance_text.setText("x "+String.valueOf(balance));
                    setIconInText(balance_text);
                    editor=sharedPreferences.edit();
                    editor.putInt(String.valueOf((shopSwitch-1)*5+4),1);
                    editor.putInt("balance",balance);
                    editor.apply();
                    selection=0;
                }
            }
        });

        if(sharedPreferences.getInt("level",1)==sharedPreferences.getInt("levelcheck",0)){
            b2.setEnabled(false);
            b2.getBackground().setColorFilter(getResources().getColor(R.color.darkBackTint), PorterDuff.Mode.SRC_ATOP);
            b2.setText("Once Per Level");
        }else{
            b2.setTextColor(getResources().getColor(R.color.textColorGold));
            setIconInText(b2);
        }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sharedPreferences.getInt("level",1)!=sharedPreferences.getInt("levelcheck",0)){
                    adSwitch=2;
                    mListener.changeFragment(99);
                }
            }
        });

        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5),0)==1){
            setIconInTextTick(pr1);
        }
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+1),0)==1){
            setIconInTextTick(pr2);
        }
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+2),0)==1){
            setIconInTextTick(pr3);
        }
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+3),0)==1){
            setIconInTextTick(pr4);
        }
        if(sharedPreferences.getInt(String.valueOf((shopSwitch-1)*5+4),0)==1){
            setIconInTextTick(pr5);
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ShopForAll.Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public interface Listener {
        void changeFragment(int id);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(sharedPreferences.getInt("level",1)==sharedPreferences.getInt("levelcheck",0)){
            b2.setEnabled(false);
            b2.getBackground().setColorFilter(getResources().getColor(R.color.darkBackTint), PorterDuff.Mode.SRC_ATOP);
            b2.setText("Once Per Level");
        }else{
            b2.setTextColor(getResources().getColor(R.color.textColorGold));
        }
        balance=sharedPreferences.getInt("balance",50);
        balance_text.setText("x "+balance);
        setIconInText(balance_text);
    }

    public void setIconInText(TextView b){
        b.setTransformationMethod(null);
        int konum=b.getText().toString().indexOf("x");
        SpannableString ss = new SpannableString(b.getText());
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.skull);
        d.setBounds(5, 5, b.getLineHeight(),b.getLineHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss.setSpan(span, konum, konum+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        b.setText(ss);
    }
    public void setIconInTextTick(TextView b){
        b.setText("x");
        b.setTransformationMethod(null);
        int konum=b.getText().toString().indexOf("x");
        SpannableString ss = new SpannableString(b.getText());
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.tick);
        d.setBounds(5, 5, b.getLineHeight(),b.getLineHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss.setSpan(span, konum, konum+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        b.setText(ss);
    }

}
