package com.honorapp.battlecommander;


import android.annotation.SuppressLint;
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

import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_bet extends Fragment {
    Listener mListener;
    boolean odd=true,firsthit=false;
    public fragment_bet() {
        // Required empty public constructor
    }
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    int bet=0,x=1,temp;
    CountDownTimer cd;
    private Button home,play,oddb,evenb,help;
    private ImageButton bet0,bet1;
    private TextView bet_text,balance,roll;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_bet, container, false);
        bet_text=(TextView) view.findViewById(R.id.bet_text);
        balance=(TextView) view.findViewById(R.id.balance_text2);
        roll=(TextView) view.findViewById(R.id.roll);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        balance.setText("x"+sharedPreferences.getInt("balance",50));
        setIconInText(balance);
        home=(Button) view.findViewById(R.id.home);
        play=(Button) view.findViewById(R.id.next_battle);
        bet0=(ImageButton) view.findViewById(R.id.bet0);
        bet1=(ImageButton) view.findViewById(R.id.bet1);
        oddb=(Button) view.findViewById(R.id.button2);
        evenb=(Button) view.findViewById(R.id.button3);
        help=(Button) view.findViewById(R.id.helpbet);

        refreshBet((sharedPreferences.getInt("balance",500)/2-sharedPreferences.getInt("balance",500)/2%10));
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(0);
            }
        });
        bet0.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    refreshBet(-5);
                }
                return false;
            }
        });
        bet1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    refreshBet(5);
                }
                return false;
            }
        });
        oddb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    odd=true;
                    firsthit=true;
                    oddb.setBackgroundResource(R.drawable.buttonspecial2);
                    evenb.setBackgroundResource(R.drawable.buttonspecial);
                }
                return true;
            }
        });
        evenb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    odd=false;
                    firsthit=true;
                    evenb.setBackgroundResource(R.drawable.buttonspecial2);
                    oddb.setBackgroundResource(R.drawable.buttonspecial);
                }
                return true;
            }
        });
        final Random r=new Random();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sharedPreferences.getInt("balance",500)>=bet&&firsthit&&bet!=0) {
                    refreshBalance(bet * -1);
                    play.setClickable(false);
                    home.setClickable(false);
                    cd = new CountDownTimer(1800, 145) {

                        public void onTick(long millisUntilFinished) {
                            temp = r.nextInt(6) + 1;
                            while (temp == x) {
                                temp = r.nextInt(6) + 1;
                            }
                            x = temp;
                            roll.setText(String.valueOf(x));

                        }

                        public void onFinish() {
                            if ((x % 2 == 0 && !odd) || (x % 2 != 0 && odd)) {
                                refreshBalance(bet * 2);
                                alertDialog("Winning: x"+bet*2);
                            }
                            play.setClickable(true);
                            home.setClickable(true);
                        }

                    }.start();
                }else if(!firsthit){
                    alertDialog("Choose Between 'Odd' & 'Even'");
                }else{
                    alertDialog("Not Enough x!");
                }
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message();
            }
        });
        return view;
    }
    void refreshBet(int a){
        if(bet+a>=0){
            bet+=a;
            bet_text.setText("x"+String.valueOf(bet));
            setIconInText(bet_text);
        }
    }
    void refreshBalance(int a){
        if(a!=0) {
            editor = sharedPreferences.edit();
            editor.putInt("balance", sharedPreferences.getInt("balance", 500) + a);
            editor.apply();
        }
        balance.setText("x"+sharedPreferences.getInt("balance",500));
        setIconInText(balance);
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
    public void setIconInText(TextView b){
        b.setTransformationMethod(null);
        int konum=b.getText().toString().indexOf("x");
        SpannableString ss = new SpannableString(b.getText());
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.skull);
        d.setBounds(15, 18, b.getLineHeight(),b.getLineHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss.setSpan(span, konum, konum+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        b.setText(ss);
    }
    private void alertDialog(String input){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_betend,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView exp;
        exp=(TextView)view.findViewById(R.id.win_textview);


        exp.setText(input);
        if(input.contains("x")) {
            setIconInText(exp);
        }
        //builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        builder.setCancelable(true);

        builder.setView(view);
        builder.show();
    }

    public interface Listener {
        void changeFragment(int id);
    }
    @Override
    public void onResume() {
        super.onResume();
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
        text.setText("\tWelcome To Betting My Liege,\n\nYou can play bet with your treasure here. Adjust bet size as you wish. Make your guess between 'Odd' & 'Even'.\n\nGood Luck!");
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

}
