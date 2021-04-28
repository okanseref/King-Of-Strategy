package com.honorapp.battlecommander;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Event extends Fragment {
    fragment_bet.Listener mListener;
    SharedPreferences sharedPreferences;
    ImageView image;
    Button b1,b2,b3;
    TextView event_title;
    private int input=0;
    public Event() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event, container, false);
        image=(ImageView)view.findViewById(R.id.event_image);
        b1=(Button)view.findViewById(R.id.b1);
        b2=(Button)view.findViewById(R.id.b2);
        b3=(Button)view.findViewById(R.id.b3);
        event_title=(TextView) view.findViewById(R.id.event_title);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());

        final Random r=new Random();
        while(input==sharedPreferences.getInt("prev",-1)){
            input=r.nextInt(5);
        }
        Log.d("asd",input+""+sharedPreferences.getInt("y",11111));
        if(sharedPreferences.getInt("y",0)!=-1){
            input=sharedPreferences.getInt("y",0);
        }else {
            Log.d("ccc",""+sharedPreferences.getInt("y",11111));

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("y", input);
            editor.apply();
            Log.d("abbbsd",""+sharedPreferences.getInt("y",11111));
        }
        switch(input){
            case 0:
                event_title.setText("A Priest asks for donation");
                image.setImageResource(R.drawable.priest);

                b1.setText("Donate x10 (+%50Hp)");
                b2.setText("Kill Him (+10 Exp  -5%Hp)");
                b3.setText("Ignore");
                colorInText(b1);
                colorInText(b2);
                setIconInText(b1);
                disableBalance(b1,10);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(50);
                        changeBalance(-10);
                        mListener.changeFragment(0);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(-5);
                        changeExp(10);
                        mListener.changeFragment(0);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        mListener.changeFragment(0);
                    }
                });
                break;
            case 1:
                event_title.setText("A Wild Tiger Appeared");
                image.setImageResource(R.drawable.tiger);

                b1.setText("Feed The Tiger, Costs x12");
                b2.setText("Defend Yourself (-25%Hp  +10Exp)");
                b3.setText("Let Your Troops Defend (-%15Hp)" );
                colorInText(b1);
                colorInText(b2);
                colorInText(b3);
                setIconInText(b1);
                disableBalance(b1,12);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeBalance(-12);
                        mListener.changeFragment(0);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(-25);
                        changeExp(10);
                        mListener.changeFragment(0);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(-15);
                        mListener.changeFragment(0);
                    }
                });
                break;
            case 2:
                event_title.setText("The Fortuneteller Has Something To Tell");
                image.setImageResource(R.drawable.witch);

                b1.setText("Pay x20 For Fortune (+26 Exp)");
                b2.setText("Pay x8 For Fortune (+12 Exp)");
                b3.setText("Nonsense" );
                colorInText(b1);
                colorInText(b2);
                setIconInText(b1);
                setIconInText(b2);
                disableBalance(b1,20);
                disableBalance(b2,8);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeBalance(-20);
                        changeExp(26);
                        mListener.changeFragment(0);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(-8);
                        changeExp(12);
                        mListener.changeFragment(0);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        mListener.changeFragment(0);
                    }
                });
                break;
            case 3:
                event_title.setText("Rich Lady is Passing By");
                image.setImageResource(R.drawable.ic_japan);

                b1.setText("Rob Her Valuables (x80)");
                b2.setText("Escort Her Passing, Costs x4");
                b3.setText("Run Away (-%5Hp)" );
                setIconInText(b1);
                colorInText(b3);
                setIconInText(b2);
                disableBalance(b2,4);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        int x=0;
                        x=r.nextInt(100);
                        if(sharedPreferences.getInt("ks"+5,0)==0 &&x>20){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putInt("x",900);
                            editor.apply();
                            mListener.changeFragment(1);
                        }else{
                            changeBalance(80);
                            MessageIshidaEscape();
                            mListener.changeFragment(0);
                        }
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeBalance(-4);
                        mListener.changeFragment(0);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(-5);
                        mListener.changeFragment(0);
                    }
                });
                break;
            case 4:
                event_title.setText("Roman Warriors Demand Tribute");
                image.setImageResource(R.drawable.ic_haraccilar);

                b1.setText("You Little Rats! (Battle)");
                b2.setText("Pay Tribute x20");
                b3.setText("Surrender (-%95Hp)" );
                setIconInText(b2);
                colorInText(b3);
                disableBalance(b2,20);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putInt("x",901);
                        editor.apply();
                        mListener.changeFragment(1);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeBalance(-20);
                        mListener.changeFragment(0);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroY();
                        changeHealth(-95);
                        mListener.changeFragment(0);
                    }
                });
                break;
        }

        return view;
    }
    private void Message(String title,String string){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_msg,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text=view.findViewById(R.id.text_msg);
        TextView text2=view.findViewById(R.id.text_title);
        text2.setText(title);
        builder.setCancelable(true);
        text.setText(string);
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
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (fragment_bet.Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public interface Listener {
        void changeFragment(int id);
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
    public void colorInText(TextView textView){
        String string=textView.getText().toString();

        final SpannableStringBuilder sb = new SpannableStringBuilder(string);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.textColorBlue));
        final ForegroundColorSpan fcs2 = new ForegroundColorSpan(getResources().getColor(R.color.textColorDarkRed));

        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        final StyleSpan bss2 = new StyleSpan(android.graphics.Typeface.BOLD);


        if(string.contains("Hp")){
            sb.setSpan(fcs2,string.indexOf("Hp")-5 , string.indexOf("Hp")+3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb.setSpan(bss,string.indexOf("Hp")-5 , string.indexOf("Hp")+3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        }
        if(string.contains("Exp")){
            sb.setSpan(fcs,string.indexOf("Exp")-5 , string.indexOf("Exp")+4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb.setSpan(bss2,string.indexOf("Exp")-5 , string.indexOf("Exp")+4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        }
// make them also bold
        //sb.setSpan(bss, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setText(sb);
    }
    void changeHealth(int x){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        if(sharedPreferences.getInt("health",100)+x<0){
            editor.putInt("health",0);
        }else if(sharedPreferences.getInt("health",100)+x>100){
            editor.putInt("health",100);
        }else {
            editor.putInt("health",sharedPreferences.getInt("health",100)+x);
        }
        editor.apply();
    }
    void changeExp(int x){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("exp",sharedPreferences.getInt("exp",0)+x);
        if(sharedPreferences.getInt("exp",0)+x>=Math.round(Math.pow(1.06f,(sharedPreferences.getInt("level",1))-1)*100)){
            editor.putInt("level",sharedPreferences.getInt("level",1)+1);
            editor.putInt("exp",sharedPreferences.getInt("exp",0)+x-(int)Math.round(Math.pow(1.06f,(sharedPreferences.getInt("level",1))-1)*100));
        }
        editor.apply();
    }
    void changeBalance(int x){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("balance",sharedPreferences.getInt("balance",0)+x);
        editor.apply();
    }
    void disableBalance(Button button,int balance){
        if(sharedPreferences.getInt("balance",50)<balance){
            button.setEnabled(false);
        }
    }
    private void MessageIshidaEscape(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_msg,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView text=view.findViewById(R.id.text_msg);
        TextView text2=view.findViewById(R.id.text_title);
        text2.setText("Rich Lady");
        builder.setCancelable(true);
        if(sharedPreferences.getInt("ks"+5,0)==0){
            text.setText("It seems Ishida protects this Lady. But you were lucky to escape with valuables. You gained x80 !");
        }else {
            text.setText("It seems Ishida used to protects this Lady. Fortunately Ishida is death now. You were successfully robbed lady. You gained x80!");
        }
        setIconInText(text);
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
    private void zeroY(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("prev",sharedPreferences.getInt("y",-1));
        editor.putInt("y", -1);

        editor.apply();
    }
}
