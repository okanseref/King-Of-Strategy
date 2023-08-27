package com.honorapp.battlecommander;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.TintableBackgroundView;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.honorapp.battlecommander.BattlePre.oarc;
import static com.honorapp.battlecommander.BattlePre.ohor;
import static com.honorapp.battlecommander.BattlePre.osv;
import static com.honorapp.battlecommander.MainActivity.adSwitch;


public class AnaMenu extends Fragment {
    Listener mListener;
    Button nextBattle,kingHall,betButton;
    ImageButton s1,s2,s3,s4,s5,s6;
    int health,refresh_suresi=10;
    SharedPreferences sharedPreferences;
    public static int shopSwitch=1;
    CountDownTimer cdt;
    ProgressBar progressBar,progressBar2;
    TextView balance_text,exp_text,level_text,username,textBlock1,textBlock2,health_text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ana_menu, container, false);
        nextBattle=(Button) view.findViewById(R.id.next_battle);
        kingHall=(Button) view.findViewById(R.id.kinghallB);
        betButton=(Button) view.findViewById(R.id.home);
        textBlock1=(TextView) view.findViewById(R.id.textBlock1);
        textBlock2=(TextView) view.findViewById(R.id.textBlock2);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBar2);
        progressBar2=(ProgressBar) view.findViewById(R.id.progressBar);

        s1=(ImageButton) view.findViewById(R.id.button8);
        s2=(ImageButton) view.findViewById(R.id.button9);
        s3=(ImageButton) view.findViewById(R.id.button10);
        s4=(ImageButton) view.findViewById(R.id.button_basict);
        s5=(ImageButton) view.findViewById(R.id.button_impt);
        s6=(ImageButton) view.findViewById(R.id.button_advt);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        health=sharedPreferences.getInt("health",100);

        health_text=(TextView) view.findViewById(R.id.health_text);
        balance_text=(TextView) view.findViewById(R.id.balance_menu);
        exp_text=(TextView) view.findViewById(R.id.exp_text);
        level_text=(TextView) view.findViewById(R.id.level_text);
        username=(TextView) view.findViewById(R.id.username);

        balance_text.setText(""+sharedPreferences.getInt("balance",50));
        exp_text.setText("Exp: "+ sharedPreferences.getInt("exp",0)+"/"+Math.round(Math.pow(1.06f,Double.valueOf(sharedPreferences.getInt("level",1))-1)*100));
        level_text.setText("Level "+sharedPreferences.getInt("level",1));
        progressBar.setProgress(Math.round(((float)sharedPreferences.getInt("exp",0)/Math.round(Math.pow(1.06f,Double.valueOf(sharedPreferences.getInt("level",1))-1)*100))*100));
        health_text.setText("%"+health);
        progressBar2.setProgress(health);
        if(sharedPreferences.getString("name","DefaultName").compareTo("DefaultName")==0) {
            nameDialog();
        }else {
            username.setText(sharedPreferences.getString("name","DefaultName"));
        }


        nextBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cdt!=null) {
                    cdt.cancel();
                }
                if(sharedPreferences.getInt("health",100)>0){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putInt("eventCount",sharedPreferences.getInt("eventCount",0)+1);
                    editor.apply();
                    if(sharedPreferences.getInt("eventCount",0)%5==0){
                        mListener.changeFragment(6);
                    }else{
                        mListener.changeFragment(1);
                    }
                }else {
                    healthDialog();
                }
            }
        });
        kingHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(4);
            }
        });
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSwitch=1;
                mListener.changeFragment(3);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSwitch=2;
                mListener.changeFragment(3);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSwitch=3;
                mListener.changeFragment(3);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSwitch=4;
                mListener.changeFragment(3);
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSwitch=5;
                mListener.changeFragment(3);
            }
        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopSwitch=6;
                mListener.changeFragment(3);
            }
        });

        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(5);
            }
        });

        s5.setEnabled(false);
        s6.setEnabled(false);

        if(sharedPreferences.getInt("level",0)>=8){
            textBlock1.setVisibility(View.GONE);
            s5.setEnabled(true);
            s5.setColorFilter(Color.argb(0, 0, 0, 0));
            s5.getBackground().setColorFilter(getResources().getColor(R.color.Transparent), PorterDuff.Mode.SRC_ATOP);
        }
        if(sharedPreferences.getInt("level",0)>=15){
            textBlock2.setVisibility(View.GONE);
            s6.setEnabled(true);
            s6.setColorFilter(Color.argb(0, 0, 0, 0));
            s6.getBackground().setColorFilter(getResources().getColor(R.color.Transparent), PorterDuff.Mode.SRC_ATOP);
        }
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameDialog();
            }
        });

        if(sharedPreferences.getBoolean("Tutorial",true)) {
            Tutorial0();
        }

        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AnaMenu.Listener) activity;
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
        health_text.setText("%"+sharedPreferences.getInt("health",100));
        progressBar2.setProgress(sharedPreferences.getInt("health",100));
    }

    private void nameDialog(){
        final androidx.appcompat.app.AlertDialog builder = new androidx.appcompat.app.AlertDialog.Builder(getContext()).create();
       // final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_entername,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final EditText input;
        TableRow r1,r2;
        input=(EditText) view.findViewById(R.id.editText);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        if(sharedPreferences.getString("name","DefaultName").compareTo("DefaultName")==0) {
            builder.setCancelable(false);
        }else{
            builder.setCancelable(true);
        }
        Button button;
        button=(Button)view.findViewById(R.id.tamam_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input.getText().length()>2) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", input.getText().toString());
                    editor.apply();
                    username.setText(input.getText().toString());
                    builder.dismiss();
                }
            }
        });

        builder.setView(view);
        builder.show();
        builder.getWindow().setLayout(Resources.getSystem().getDisplayMetrics().widthPixels*95/100, Resources.getSystem().getDisplayMetrics().heightPixels*45/100); //Controlling width and height.

    }
    private void healthDialog(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_nohealth,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Button button_pay,button_video;
        button_pay=(Button)view.findViewById(R.id.tamam_button2);
        setIconInText(button_pay);
        int balance=sharedPreferences.getInt("balance",50);
        if(balance>=250) {
            button_pay.setEnabled(true);
        }else{
            button_pay.setEnabled(false);
            button_pay.getBackground().setColorFilter(getResources().getColor(R.color.darkBackTint), PorterDuff.Mode.SRC_ATOP);
        }
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int balance=sharedPreferences.getInt("balance",50);
                balance-=250;
                balance_text.setText(String.valueOf(balance));
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor=sharedPreferences.edit();
                editor.putInt("balance",balance);
                editor.putInt("health",100);
                editor.apply();
                health_text.setText("%"+sharedPreferences.getInt("health",100));
                progressBar2.setProgress(sharedPreferences.getInt("health",100));
                builder.dismiss();
            }
        });
        button_video=(Button)view.findViewById(R.id.tamam_button);
        button_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adSwitch=0;
                mListener.changeFragment(99);
                health_text.setText("%"+sharedPreferences.getInt("health",100));
                progressBar2.setProgress(sharedPreferences.getInt("health",100));
                builder.dismiss();
            }
        });
        builder.setCancelable(true);
        builder.setView(view);
        builder.show();
        builder.getWindow().setLayout(Resources.getSystem().getDisplayMetrics().widthPixels*90/100, Resources.getSystem().getDisplayMetrics().heightPixels*60/100); //Controlling width and height.

    }




    private void Tutorial0(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_msg,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        nextBattle.setEnabled(true);
        betButton.setEnabled(false);
        kingHall.setEnabled(false);
        s1.setEnabled(false);
        s2.setEnabled(false);
        s3.setEnabled(false);
        s4.setEnabled(false);
        s5.setEnabled(false);
        s6.setEnabled(false);

        TextView text=view.findViewById(R.id.text_msg);
        text.setText("\tGreetings My Liege,\nYour troops are waiting for your first battle. Click 'Next Battle' button to continue");
        TableRow row = view.findViewById(R.id.row_button);
        TextView text2=view.findViewById(R.id.text_title);
        text2.setText("Tutorial");
        ImageButton button=(ImageButton)view.findViewById(R.id.tamam_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdt=new CountDownTimer(30000, 500) {
                    boolean x=true;
                    public void onTick(long millisUntilFinished) {
                        if(x){
                            nextBattle.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Transparent2));
                            x=false;
                        }else{
                            nextBattle.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Shine));
                            x=true;
                        }
                    }

                    public void onFinish() {
                        cdt.start();
                    }

                }.start();
                builder.dismiss();
            }
        });
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setView(view);
        builder.show();
        builder.getWindow().setLayout(Resources.getSystem().getDisplayMetrics().widthPixels*8/10, Resources.getSystem().getDisplayMetrics().heightPixels*40/100); //Controlling width and height.

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

}