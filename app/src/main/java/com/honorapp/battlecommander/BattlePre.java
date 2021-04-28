package com.honorapp.battlecommander;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BattlePre extends Fragment {
    Spinner tacticbutton;
    Button ready,flee;
    ImageButton a0,a1,s0,s1,h0,h1;
    TextView ca,cs,ch,oa,os,oh,otactic,oname,cmilitary;
    public static int arc=0,sw=0,hor=0,total=0,max=10,oarc,osv,ohor,hero;
    ImageView oimage;
    ImageButton fill0,fill1,fill2;
    Listener mListener;
    boolean Tutorial=true;
    SharedPreferences sharedPreferences;
    private ArrayList<CountryItem> mCountryList;
    CountDownTimer cdt;
    public static int chosenTactic=0,opponentTactic=0;
    private CountryAdapter adapter;
    String[] albums ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_battle_pre, container, false);
        albums= getResources().getStringArray(R.array.arr);
        a0=(ImageButton) view.findViewById(R.id.arhcercount0);
        a1=(ImageButton) view.findViewById(R.id.arhcercount1);
        s0=(ImageButton) view.findViewById(R.id.swordcount0);
        s1=(ImageButton) view.findViewById(R.id.swordcount1);
        h0=(ImageButton) view.findViewById(R.id.horsecount0);
        h1=(ImageButton) view.findViewById(R.id.horsecount1);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Tutorial=sharedPreferences.getBoolean("Tutorial",true);
        fill0=(ImageButton) view.findViewById(R.id.imageView5);
        fill1=(ImageButton) view.findViewById(R.id.imageView6);
        fill2=(ImageButton) view.findViewById(R.id.imageView7);

        tacticbutton=(Spinner) view.findViewById(R.id.tactic_choose);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        mCountryList=new ArrayList<>();
        mCountryList.add(new CountryItem("No Tactic\n"));

        for(int i=30;i>=15;i--){
            String s=" ";
            if(sharedPreferences.getInt(String.valueOf(i),0)==1){
                switch(i){
                    case 15:
                        s=getResources().getString(R.string.buff3_1);
                        break;
                    case 16:
                        s=getResources().getString(R.string.buff3_2);
                        break;
                    case 17:
                        s=getResources().getString(R.string.buff3_3);
                        break;
                    case 18:
                        s=getResources().getString(R.string.buff3_4);
                        break;
                    case 19:
                        s=getResources().getString(R.string.buff3_5);
                        break;
                    case 20:
                        s=getResources().getString(R.string.buff4_1);
                        break;
                    case 21:
                        s=getResources().getString(R.string.buff4_2);
                        break;
                    case 22:
                        s=getResources().getString(R.string.buff4_3);
                        break;
                    case 23:
                        s=getResources().getString(R.string.buff4_4);
                        break;
                    case 24:
                        s=getResources().getString(R.string.buff4_5);
                        break;
                    case 25:
                        s=getResources().getString(R.string.buff5_1);
                        break;
                    case 26:
                        s=getResources().getString(R.string.buff5_2);
                        break;
                    case 27:
                        s=getResources().getString(R.string.buff5_3);
                        break;
                    case 28:
                        s=getResources().getString(R.string.buff5_4);
                        break;
                    case 29:
                        s=getResources().getString(R.string.buff5_5);
                        break;

                }
                mCountryList.add(new CountryItem(s));
            }

        }
        arc=0;
        sw=0;
        hor=0;
        total=0;
        max=10+sharedPreferences.getInt("level",0);
        adapter=new CountryAdapter(getActivity(),mCountryList);
        tacticbutton.setAdapter(adapter);
        tacticbutton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff3_1))==0){
                    chosenTactic=15;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff3_2))==0){
                    chosenTactic=16;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff3_3))==0){
                    chosenTactic=17;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff3_4))==0){
                    chosenTactic=18;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff3_5))==0){
                    chosenTactic=19;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff4_1))==0){
                    chosenTactic=20;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff4_2))==0){
                    chosenTactic=21;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff4_3))==0){
                    chosenTactic=22;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff4_4))==0){
                    chosenTactic=23;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff4_5))==0){
                    chosenTactic=24;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff5_1))==0){
                    chosenTactic=25;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff5_2))==0){
                    chosenTactic=26;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff5_3))==0){
                    chosenTactic=27;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff5_4))==0){
                    chosenTactic=28;
                }
                if(tacticbutton.getItemAtPosition(i).toString().compareTo(getResources().getString(R.string.buff5_5))==0){
                    chosenTactic=29;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ca=(TextView) view.findViewById(R.id.count_archer);
        cs=(TextView) view.findViewById(R.id.count_sword);
        ch=(TextView) view.findViewById(R.id.count_horse);
        oa=(TextView) view.findViewById(R.id.op_archer);
        os=(TextView) view.findViewById(R.id.op_sword);
        oh=(TextView) view.findViewById(R.id.op_horse);
        otactic=(TextView) view.findViewById(R.id.op_tactic);
        oname=(TextView) view.findViewById(R.id.op_name);
        oimage=(ImageView) view.findViewById(R.id.op_image);
        ready=(Button) view.findViewById(R.id.ready_to_battle);
        flee=(Button) view.findViewById(R.id.flee);

        cmilitary=(TextView) view.findViewById(R.id.count_military);
        cmilitary.setText(String.valueOf(total)+"/"+String.valueOf(max));

        arc=0;
        sw=0;
        hor=0;
        total=0;
        hero=-1;
        ohor=0;
        osv=0;
        oarc=0;
        opponentTactic=0;
        chosenTactic=0;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int x;
        Random r = new Random();
        x=r.nextInt(100);
        if(sharedPreferences.getInt("x",-1)<0){
            editor.putInt("x", x);
            editor.apply();
        }else {
            x=sharedPreferences.getInt("x",-1);
        }
        if((x<6&&sharedPreferences.getInt("level",1)>=2)||sharedPreferences.getBoolean("kingNextFight",false)){
            ArrayList<Integer> list=new ArrayList<>();
            if(sharedPreferences.getInt("level",1)>=18&&sharedPreferences.getInt("ks"+6,0)==0){
                list.add(6);
            }
            if(sharedPreferences.getInt("level",1)>=15&&sharedPreferences.getInt("ks"+5,0)==0){
                list.add(5);
            }
            if(sharedPreferences.getInt("level",1)>=12&&sharedPreferences.getInt("ks"+4,0)==0){
                list.add(4);
            }
            if(sharedPreferences.getInt("level",1)>=10&&sharedPreferences.getInt("ks"+3,0)==0){
                list.add(3);
            }
            if(sharedPreferences.getInt("level",1)>=7&&sharedPreferences.getInt("ks"+2,0)==0){
                list.add(2);
            }
            if(sharedPreferences.getInt("level",1)>=5&&sharedPreferences.getInt("ks"+1,0)==0){
                list.add(1);
            }
            if(sharedPreferences.getInt("level",1)>=1&&sharedPreferences.getInt("ks"+0,0)==0){
                list.add(0);
            }
            editor=sharedPreferences.edit();
            editor.putBoolean("kingNextFight",false);
            editor.apply();
            x=r.nextInt(list.size());
            fillGeneralInfo(x);
        }else{
            int b=0;
            if(x<18){
                oname.setText("Warrior Chief");
                oimage.setImageResource(R.drawable.ic_warrior);
                b=r.nextInt(4);
                newEasyOpponent(6+b);
                hero=100;
            }else if(x<42){
                oname.setText("Fighter Captain");
                oimage.setImageResource(R.drawable.ic_warrior_2);
                b=r.nextInt(3);
                newEasyOpponent(4+b);
                hero=101;
            }else if(x<79){
                oname.setText("Farmer Leader");
                oimage.setImageResource(R.drawable.ic_farmer);
                b=r.nextInt(3);
                newEasyOpponent(2+b);
                hero=102;
            }else if(x<101){
                oname.setText("No General");
                oimage.setImageResource(R.drawable.torch);
                b=r.nextInt(3);
                newEasyOpponent(b);
                hero=103;
            }else if(x==900){
                oname.setText("Ishida");
                oimage.setImageResource(R.drawable.opponent_5);
                osv=38;
                oarc=15;
                ohor=12;
                otactic.setText("Army Of Sword Masters With Eastern Elite Sword Skills");
                hero=5;
                MessageIshida();
            }else if(x==901){
                oname.setText("Roman Warriors");
                oimage.setImageResource(R.drawable.ic_haraccilar);
                osv=28;
                oarc=2;
                ohor=6;
                otactic.setText("They Carry Extra Gold");
                hero=200;
            }
        }

        if(Tutorial) {
            Tutorial1();
        }

        if(oarc>max*0.85f){
            oa.setTextColor(getResources().getColor(R.color.hot2));
        }else if(oarc>max/2){
            oa.setTextColor(getResources().getColor(R.color.hot1));
        }
        if(osv>max*0.85f){
            os.setTextColor(getResources().getColor(R.color.hot2));
        }else if(osv>max/2){
            os.setTextColor(getResources().getColor(R.color.hot1));
        }
        if(ohor>max*0.85f){
            oh.setTextColor(getResources().getColor(R.color.hot2));
        }else if(ohor>max/2){
            oh.setTextColor(getResources().getColor(R.color.hot1));
        }
        oa.setText(String.valueOf(oarc));
        os.setText(String.valueOf(osv));
        oh.setText(String.valueOf(ohor));

        setTouchListener0(a0,-1,ca);
        setTouchListener0(a1,1,ca);
        setTouchListener1(s0,-1,cs);
        setTouchListener1(s1,1,cs);
        setTouchListener2(h0,-1,ch);
        setTouchListener2(h1,1,ch);
        fill0.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                        if(total!=max) {
                            int add = max - arc - sw - hor;
                            arc += add;
                            ca.setText(String.valueOf(arc));
                            total += add;
                            cmilitary.setText(String.valueOf(total) + "/" + String.valueOf(max));
                        }else{
                            int add = arc;
                            arc -= add;
                            ca.setText(String.valueOf(arc));
                            total -= add;
                            cmilitary.setText(String.valueOf(total) + "/" + String.valueOf(max));
                        }
                }
                return false;
            }
        });
        fill1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(total!=max) {
                        int add = max - arc - sw - hor;
                    sw += add;
                    cs.setText(String.valueOf(sw));
                    total += add;
                    cmilitary.setText(String.valueOf(total) + "/" + String.valueOf(max));
                    }else{
                    int add = sw;
                        sw -= add;
                    cs.setText(String.valueOf(sw));
                    total -= add;
                    cmilitary.setText(String.valueOf(total) + "/" + String.valueOf(max));
                }
                }
                return false;
            }
        });
        fill2.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if(total!=max) {
                        int add=max-arc-sw-hor;
                    hor += add;
                    ch.setText(String.valueOf(hor));
                    total+=add;
                    cmilitary.setText(String.valueOf(total)+"/"+String.valueOf(max));
                    }else{
                        int add = hor;
                        hor -= add;
                        ch.setText(String.valueOf(hor));
                        total -= add;
                        cmilitary.setText(String.valueOf(total) + "/" + String.valueOf(max));
                    }
                }
                return false;
            }
        });
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(2);
            }
        });
        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("x",-3);
                editor.apply();
                changeHealth(-8);
                mListener.changeFragment(0);
            }
        });
        return view;
    }
    private void fillGeneralInfo(int x){
        hero=x;
        switch (x){
            case 0:
                oname.setText("King Edward");
                oimage.setImageResource(R.drawable.opponent_0);
                oarc=15;
                osv=8;
                ohor=8;
                otactic.setText("Rookie King Of These Lands");
                break;
            case 1:
                oname.setText("Loabrader Raider");
                oimage.setImageResource(R.drawable.opponent_1);
                oarc=8;
                osv=25;
                ohor=5;
                otactic.setText("Wild Barbarians Thirsty For Blood");
                break;
            case 2:
                oname.setText("Philopator Queen");
                oimage.setImageResource(R.drawable.opponent_2);
                oarc=14;
                osv=12;
                ohor=22;
                otactic.setText("Beautiful Queen With Her Carriot Horsemans");
                break;
            case 3:
                oname.setText("Shah Musman");
                oimage.setImageResource(R.drawable.opponent_3);
                oarc=8;
                osv=30;
                ohor=12;
                otactic.setText("Rich Shah And His Army Of Mercenaries");
                break;
            case 4:
                oname.setText("Emperor Tocha");
                oimage.setImageResource(R.drawable.opponent_4);
                oarc=28;
                osv=12;
                ohor=10;
                otactic.setText("Experienced And Strong Leader , Mighty Tocha");
                break;
            case 5:
                oname.setText("Ishida");
                oimage.setImageResource(R.drawable.opponent_5);
                osv=38;
                oarc=15;
                ohor=12;
                otactic.setText("Army Of Sword Masters With Eastern Elite Sword Skills");
                break;
            case 6:
                oname.setText("Joan Darkmai");
                oimage.setImageResource(R.drawable.opponent_6);
                oarc=35;
                osv=44;
                ohor=33;
                otactic.setText("The Maid Of Orleans");
                break;
                default:
                    oname.setText("No General");
                    oimage.setImageResource(R.drawable.torch);
                    break;
        }
    }
    private void newEasyOpponent(int plus){
        Random r = new Random();
        int ototal=max+plus;
        oarc=r.nextInt(ototal);
        ototal-=oarc;
        osv=r.nextInt(ototal);
        ototal-=osv;
        ohor=ototal;
        opponentTactic=r.nextInt(4);
        otactic.setText(albums[opponentTactic]);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (BattlePre.Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    void changeHealth(int x){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        if(sharedPreferences.getInt("health",100)+x<0){
            editor.putInt("health",0);
        }else{
            editor.putInt("health",sharedPreferences.getInt("health",100)+x);
        }
        editor.apply();
    }
    public interface Listener {
        void changeFragment(int id);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    void setTouchListener0(ImageButton button, final int i, final TextView textView){
        button.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if(total+i<=max&&arc+i>=0) {
                        arc += i;
                        textView.setText(String.valueOf(arc));
                        total+=i;
                        cmilitary.setText(String.valueOf(total)+"/"+String.valueOf(max));
                    }
            }
                return false;
            }
        });
    }

    void setTouchListener1(ImageButton button, final int i, final TextView textView){
        button.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if(total+i<=max&&sw+i>=0) {
                        sw += i;
                        textView.setText(String.valueOf(sw));
                        total+=i;
                        cmilitary.setText(String.valueOf(total)+"/"+String.valueOf(max));
                    }
                }
                if(sw==max-hor&&Tutorial){
                    s1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Transparent2));
                    a0.setEnabled(false);
                    a1.setEnabled(false);
                    s0.setEnabled(false);
                    s1.setEnabled(false);
                    h0.setEnabled(false);
                    h1.setEnabled(false);
                    fill0.setEnabled(false);
                    fill1.setEnabled(false);
                    fill2.setEnabled(false);
                    ready.setEnabled(true);
                    cdt.cancel();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("Tutorial",false);
                    editor.apply();
                    cdt=new CountDownTimer(30000, 500) {
                        boolean x=true;
                        public void onTick(long millisUntilFinished) {
                            if(x){
                                ready.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Transparent2));
                                x=false;
                            }else{
                                ready.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Shine));
                                x=true;
                            }
                        }

                        public void onFinish() {
                            cdt.start();
                        }

                    }.start();
                }
                return false;
            }
        });
    }
    void setTouchListener2(ImageButton button, final int i, final TextView textView){
        button.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if(total+i<=max&&hor+i>=0) {
                        hor += i;
                        textView.setText(String.valueOf(hor));
                        total+=i;
                        cmilitary.setText(String.valueOf(total)+"/"+String.valueOf(max));
                    }
                    if(hor==6&&Tutorial){
                        h1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Transparent2));
                        a0.setEnabled(false);
                        a1.setEnabled(false);
                        s0.setEnabled(false);
                        s1.setEnabled(true);
                        h0.setEnabled(false);
                        h1.setEnabled(false);
                        fill0.setEnabled(false);
                        fill1.setEnabled(false);
                        fill2.setEnabled(false);
                        ready.setEnabled(false);

                        cdt.cancel();
                        cdt=new CountDownTimer(30000, 500) {
                            boolean x=true;
                            public void onTick(long millisUntilFinished) {
                                if(x){
                                    s1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Transparent2));
                                    x=false;
                                }else{
                                    s1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Shine));
                                    x=true;
                                }
                            }

                            public void onFinish() {
                                cdt.start();
                            }

                        }.start();
                    }
                }
                return false;
            }
        });
    }

    private void Tutorial1(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_msg,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        a0.setEnabled(false);
        a1.setEnabled(false);
        s0.setEnabled(false);
        s1.setEnabled(false);
        h0.setEnabled(false);
        h1.setEnabled(true);
        fill0.setEnabled(false);
        fill1.setEnabled(false);
        fill2.setEnabled(false);
        ready.setEnabled(false);
        ohor=1;
        osv=2;
        oarc=6;
        TextView text=view.findViewById(R.id.text_msg);
        TextView text2=view.findViewById(R.id.text_title);
        text2.setText("Tutorial");

        text.setText("\tThis is Pre-Battle Screen My Liege,\nBy checking enemy troops, you can build your army to counter them. Enemy has crowded 'Archer' troops, so we should focus 'Horsemans'.");
        TableRow row = view.findViewById(R.id.row_button);
        ImageButton button=(ImageButton)view.findViewById(R.id.tamam_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdt=new CountDownTimer(30000, 500) {
                    boolean x=true;

                    public void onTick(long millisUntilFinished) {
                        if(x){
                            h1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Transparent2));
                            x=false;
                        }else{
                            h1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.Shine));
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
    private void MessageIshida(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_msg,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView text=view.findViewById(R.id.text_msg);
        TextView text2=view.findViewById(R.id.text_title);
        text2.setText("Rich Lady");
        builder.setCancelable(true);
        text.setText("It seems Ishida protects this Lady. Now you have to face with Ishida.");
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
