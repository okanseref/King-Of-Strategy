package com.honorapp.battlecommander;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

import static com.honorapp.battlecommander.BattlePre.arc;
import static com.honorapp.battlecommander.BattlePre.chosenTactic;
import static com.honorapp.battlecommander.BattlePre.hero;
import static com.honorapp.battlecommander.BattlePre.hor;
import static com.honorapp.battlecommander.BattlePre.oarc;
import static com.honorapp.battlecommander.BattlePre.ohor;
import static com.honorapp.battlecommander.BattlePre.opponentTactic;
import static com.honorapp.battlecommander.BattlePre.osv;
import static com.honorapp.battlecommander.BattlePre.sw;


/**
 * A simple {@link Fragment} subclass.
 */
public class BattleScreen extends Fragment {
    DecimalFormat twoDForm;
    CountDownTimer cd;
    private int timeSeperate=0,exptotal=0,prev_prog=50;
    private ProgressBar battleProgress;
    private Listener mListener;
    private ImageView battleScreen,image,oimage,pointer;
    private float archers,swmans,horseman,archers2,swmans2,horseman2,ch1,ch2,ch3,ch4,ch5,ch6;
    private TextView ra0,rs0,rh0,ra1,rs1,rh1,red1,red2,red3,red4,red5,red6,battlephasetext,name,oname,focus_text;
    private boolean winner=false;
    private float horseDamageToInfantry=0.5f;
    private int total1,total2,oppbonusgold=0,focus=1,phasec0=0,phasec1=0;
    private ImageButton b1,b2,b3;
    private TableRow r1,r2,r3,r4,r5,r6;
    private Boolean shockCntr=true,bugfix1=true;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_battle_screen, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        battleProgress=(ProgressBar) view.findViewById(R.id.battleProgress);
        focus_text=(TextView) view.findViewById(R.id.focus);
        ra0=(TextView) view.findViewById(R.id.ra0);
        rs0=(TextView) view.findViewById(R.id.rs0);
        rh0=(TextView) view.findViewById(R.id.rh0);
        ra1=(TextView) view.findViewById(R.id.ra1);
        rs1=(TextView) view.findViewById(R.id.rs1);
        rh1=(TextView) view.findViewById(R.id.rh1);
        battleScreen=(ImageView) view.findViewById(R.id.battle_image);
        red1=(TextView) view.findViewById(R.id.ch1);
        red2=(TextView) view.findViewById(R.id.ch2);
        red3=(TextView) view.findViewById(R.id.ch3);
        red4=(TextView) view.findViewById(R.id.ch4);
        red5=(TextView) view.findViewById(R.id.ch5);
        red6=(TextView) view.findViewById(R.id.ch6);
        r1=(TableRow) view.findViewById(R.id.r1);
        r2=(TableRow) view.findViewById(R.id.r2);
        r3=(TableRow) view.findViewById(R.id.r3);
        r4=(TableRow) view.findViewById(R.id.r4);
        r5=(TableRow) view.findViewById(R.id.r5);
        r6=(TableRow) view.findViewById(R.id.r6);
        battlephasetext=(TextView) view.findViewById(R.id.battlephasetext);
        name=(TextView) view.findViewById(R.id.btext);
        oname=(TextView) view.findViewById(R.id.op_btext);
        image=(ImageView) view.findViewById(R.id.bimage);
        oimage=(ImageView) view.findViewById(R.id.op_bimage);
        pointer=(ImageView) view.findViewById(R.id.pointer);
        b1=(ImageButton) view.findViewById(R.id.imageButton);
        b2=(ImageButton) view.findViewById(R.id.imageButton2);
        b3=(ImageButton) view.findViewById(R.id.imageButton3);
        if(sharedPreferences.getInt("x",-2)==-2) {
            sw=sharedPreferences.getInt("sw", 0);
            arc=sharedPreferences.getInt("arc", 0);
            hor=sharedPreferences.getInt("hor", 0);
            osv=sharedPreferences.getInt("osw", 0);
            oarc=sharedPreferences.getInt("oarc", 0);
            ohor=sharedPreferences.getInt("ohor", 0);
            hero=sharedPreferences.getInt("hero", 0);
            chosenTactic=sharedPreferences.getInt("chosenTactic", 0);
            opponentTactic=sharedPreferences.getInt("opponentTactic", 0);

        }else{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("x", -2);
            editor.putInt("sw", sw);
            editor.putInt("arc", arc);
            editor.putInt("hor", hor);
            editor.putInt("osw", osv);
            editor.putInt("oarc", oarc);
            editor.putInt("ohor", ohor);
            editor.putInt("hero", hero);
            editor.putInt("chosenTactic", chosenTactic);
            editor.putInt("opponentTactic", opponentTactic);
            editor.apply();
        }

        fillGeneralInfo(hero);
        archers=Math.round(arc);
        swmans=Math.round(sw);
        horseman=Math.round(hor);
        archers2=Math.round(oarc);
        swmans2=Math.round(osv);
        horseman2=Math.round(ohor);
        total1=sw+arc+hor;
        total2=osv+oarc+ohor;
        name.setText(sharedPreferences.getString("name","DefaultName"));
        if(sharedPreferences.getInt("level",0)>=20){
            image.setImageResource(R.drawable.advanced_tactics);
        }else if(sharedPreferences.getInt("level",0)>=12){
            image.setImageResource(R.drawable.improved_tactics);
        }
        battleProgress.setProgress(50);
        battleProgress.setSecondaryProgress(100);

        twoDForm = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        twoDForm.setDecimalFormatSymbols(dfs);

        refreshNumbers();

        cd=new CountDownTimer(90000, 2600) {

            public void onTick(long millisUntilFinished) {
                //clearRows();
                exptotal++;
                if(archers==0&&archers2==0&&timeSeperate<3&&chosenTactic!=25){timeSeperate=3;}
                if(horseman==0&&horseman2==0&&timeSeperate<6&&timeSeperate>2){timeSeperate=6;}


                switch (timeSeperate){
                    case 0:
                        battleScreen.setImageResource(R.drawable.phase_start);
                        if(chosenTactic==27&&bugfix1){ timeSeperate=2;}
                        break;
                    case 1:
                    case 2:
                        pointer.setVisibility(View.VISIBLE);
                        firePhase();
                        checkEnd();
                        break;
                    case 3:
                    case 4:
                    case 5:
                        if(chosenTactic==17&&shockCntr){timeSeperate++;shockCntr=false;}
                        shockPhase();
                        checkEnd();
                        if(chosenTactic==27&&bugfix1&&timeSeperate==5){ timeSeperate=0;bugfix1=false;}

                        break;
                    case 6:
                    case 7:
                    case 8:
                        if(chosenTactic==28){swmans*=1.3f;horseman*=1.3f;archers*=1.3f;}
                        fightPhase();
                        checkEnd();
                        break;
                    default:
                        if(chosenTactic==28){swmans*=1.3f;horseman*=1.3f;archers*=1.3f;}
                        fightPhase();
                        checkEnd();
                        break;
                }
                timeSeperate++;
            }

            public void onFinish() {

            }

        }.start();

        b1.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
        b2.setBackground(getResources().getDrawable(R.drawable.buttonhigh4_));
        b3.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
        focus=1;
        focus_text.setText("Balanced Focus");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setBackground(getResources().getDrawable(R.drawable.buttonhigh4_));
                b2.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
                b3.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
                focus=0;
                focus_text.setText("Defensive Focus");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
                b2.setBackground(getResources().getDrawable(R.drawable.buttonhigh4_));
                b3.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
                focus=1;
                focus_text.setText("Balanced Focus");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
                b2.setBackground(getResources().getDrawable(R.drawable.buttonhigh3_));
                b3.setBackground(getResources().getDrawable(R.drawable.buttonhigh4_));
                focus=2;
                focus_text.setText("Aggressive Focus");
            }
        });
        return view;
    }

    void firePhase(){
        battleScreen.setImageResource(R.drawable.phase_fire);
        phasec0++;
        String string ="Fire ";
        for(int i=0;i<phasec0;i++){
            string+="I";
        }
        battlephasetext.setText(string);
        float damage1,damage2;
        damage1=archers;
        if(sharedPreferences.getInt(String.valueOf(0),0)==1){damage1*=1.06f;}
        if(sharedPreferences.getInt(String.valueOf(2),0)==1){damage1-=archers*horseman2/9f;}else{
        damage1-=archers*horseman2/8f;}
        damage1+=archers*swmans2/10f;
        damage2=archers2;
        if(chosenTactic==26){
            if(swmans>=damage2){damage2=0;
        }else{
                damage2-=swmans;            //OKLAR KESİLDİKTEN SONRA
                damage2-=archers2*horseman/8f;
                damage2+=archers2*swmans/10f;
        } }else{
            damage2-=archers2*horseman/8f;      //NORMAL BUFFLAR
            damage2+=archers2*swmans/6f;
        }


        if(sharedPreferences.getInt(String.valueOf(4),0)==1){damage2*=0.92f;}

        damage1/=3;
        damage2/=3;
        if(chosenTactic==24){damage2*=0.9f;}
        if(chosenTactic==16){damage1*=1.05f;}
        if(chosenTactic==18){damage2*=0.75f;}
        if(opponentTactic==1){damage1*=0.82f;}
        if(damage1<0){
            int x=0;
            Random r=new Random();
            x=r.nextInt(25);
            damage1=(float)x/100;
        }
        if(damage2<0){
            int x=0;
            Random r=new Random();
            x=r.nextInt(25);
            damage2=(float)x/100;
        }

        boolean bugfix=true;
        if(chosenTactic==21){
            damage1*=1.3f;
            if(archers2<damage1&&archers2!=0){
                damage1-=archers2;
                ch4=archers2;
                archers2=0;
            }else if(archers2!=0){
                archers2-=damage1;
                ch4=damage1;
                damage1=0;
            }
            bugfix=false;
        }
        if(chosenTactic==25){damage1+=(archers+swmans+horseman)/6.2f;}

        /*if(damage1>0){
            r1.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(damage2>0){
            r6.setBackgroundColor(getResources().getColor(R.color.Active));
        }*/
        damage1=focusAdjustment(damage1);
        damage2=focusAdjustment(damage2);
        if(swmans2<damage1&&swmans2!=0){
            damage1-=swmans2;
            ch5=swmans2;
            swmans2=0;
        }else if(swmans2!=0){
            swmans2-=damage1;
            ch5=damage1;
            damage1=0;
        }
        if(archers2<damage1&&archers2!=0&&bugfix){
            damage1-=archers2;
            ch4=archers2;
            archers2=0;
        }else if(archers2!=0&&bugfix){
            archers2-=damage1;
            ch4=damage1;
            damage1=0;
        }
        if(horseman2<damage1&&horseman2!=0){
            damage1-=horseman2;
            ch6=horseman2;
            horseman2=0;
        }else if(horseman2!=0){
            if(sharedPreferences.getInt(String.valueOf(3),0)==1){horseman2-=damage1*1.12f;}else{
            horseman2-=damage1;}
            ch6=damage1;
            damage1=0;
        }

        if(swmans<damage2&&swmans!=0){
            damage2-=swmans;
            ch2=swmans;
            swmans=0;
        }else if(swmans!=0){
            if(sharedPreferences.getInt(String.valueOf(7),0)==1){damage2*=0.92f;}
            swmans-=damage2;
            ch2=damage2;
            damage2=0;
        }
        if(archers<damage2&&archers!=0){
            damage2-=archers;
            ch1=archers;
            archers=0;
        }else if(archers!=0){
            archers-=damage2;
            ch1=damage2;
            damage2=0;
        }
        if(horseman<damage2&&horseman!=0){
            damage2-=horseman;
            ch3=horseman;
            horseman=0;
        }else if(horseman!=0){
            horseman-=damage2;
            ch3=damage2;
            damage2=0;
        }

        refreshNumbers();
    }
    void shockPhase(){
        battleScreen.setImageResource(R.drawable.phase_shock);
        phasec1++;
        String string ="Shock ";
        for(int i=0;i<phasec1;i++){
            string+="I";
        }
        battlephasetext.setText(string);
        float damage1,damage2;
        damage1=horseman;
        if(chosenTactic==27){ damage1*=1.35f;}

        if(sharedPreferences.getInt(String.valueOf(10),0)==1){damage1*=1.07f;}
        if(sharedPreferences.getInt(String.valueOf(11),0)==1){damage1-=horseman*swmans2/8f; }else{
        damage1-=horseman*swmans2/7f;}
        damage1+=horseman*archers2/10f;
        if(sharedPreferences.getInt(String.valueOf(13),0)==1){damage1*=1.1f;}

        damage2=horseman2;
        if(sharedPreferences.getInt(String.valueOf(5),0)==1){damage2-=horseman2*swmans/6f;}else{
        damage2-=horseman2*swmans/7f;}
        damage2+=horseman2*archers/10f;
        if(sharedPreferences.getInt(String.valueOf(14),0)==1){damage2*=0.9f;}

        if(chosenTactic==15){damage1+=horseman*0.1f;damage2+=horseman2*0.1f;}
        if(opponentTactic==3){damage1*=1.2f;damage2*=1.2f;}

        damage1/=3;
        damage2/=3;
        if(damage1<0){damage1=0.1f;}
        if(damage2<0){damage2=0.1f;}
        if(chosenTactic==24){damage2*=0.9f;}
        if(chosenTactic==16){damage1*=1.05f;}
        /*if(damage1>0){
            r3.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(damage2>0){
            r4.setBackgroundColor(getResources().getColor(R.color.Active));
        }*/
        damage1=focusAdjustment(damage1);
        damage2=focusAdjustment(damage2);
        if(archers2<damage1&&archers2!=0){
            damage1-=archers2;
            ch4=archers2;
            archers2=0;
        }else if(archers2!=0){
            archers2-=damage1;
            ch4=damage1;
            damage1=0;
        }
        if(swmans2*(1/horseDamageToInfantry)<damage1&&swmans2!=0){
            damage1-=swmans2*(1/horseDamageToInfantry);
            ch5=swmans2*(1/horseDamageToInfantry);
            swmans2=0;
        }else if(swmans2!=0){
            swmans2-=damage1*horseDamageToInfantry;
            ch5=damage1*horseDamageToInfantry;
            damage1=0;
        }
        if(horseman2<damage1&&horseman2!=0){
            damage1-=horseman2;
            ch6=horseman2;
            horseman2=0;
        }else if(horseman2!=0){
            horseman2-=damage1;
            ch6=damage1;
            damage1=0;
        }

        if(archers<damage2&&archers!=0){
            damage2-=archers;
            ch1=archers;
            archers=0;
        }else if(archers!=0){
            archers-=damage2;
            ch1=damage2;
            damage2=0;
        }
        if(swmans*(1/horseDamageToInfantry)<damage2&&swmans!=0){
            damage2-=swmans*(1/horseDamageToInfantry);
            ch2=swmans*(1/horseDamageToInfantry);
            swmans=0;
        }else if(swmans!=0){
            swmans-=damage2*horseDamageToInfantry;
            ch2=damage2*horseDamageToInfantry;
            damage2=0;
        }
        if(horseman<damage2&&horseman!=0){
            damage2-=horseman;
            ch3=horseman;
            horseman=0;
        }else if(horseman!=0){
            horseman-=damage2;
            ch3=damage2;
            damage2=0;
        }

        refreshNumbers();
    }
    void fightPhase(){
        battleScreen.setImageResource(R.drawable.phase_fight);
        battlephasetext.setText("Charge");
        if(chosenTactic==29){
            swmans+=archers;
            swmans+=horseman;
            archers=0;
            horseman=0;
        }
        float damage1,damage2;
        damage1=swmans*1.8f+archers*0.5f+horseman*1f;
        if(chosenTactic==29){damage1*=0.9f; }//"BUT %10 WEAKER"
        if(sharedPreferences.getInt(String.valueOf(12),0)==1){damage1+=horseman*0.08f;}
        if(chosenTactic==22&&horseman==0){damage1+=swmans*0.72f;}
        if(chosenTactic==23&&horseman+swmans+archers>archers2+swmans2+horseman2){damage1*=1.3f;}
        if(chosenTactic==19){damage1+=archers*0.3f;}

        if(sharedPreferences.getInt(String.valueOf(1),0)==1){damage1+=archers*0.1f;}
        if(sharedPreferences.getInt(String.valueOf(6),0)==1){damage1+=swmans*0.09f;}
        if(sharedPreferences.getInt(String.valueOf(8),0)==1){damage1*=1.06f;}

        damage2=swmans2*1.8f+archers2*0.5f+horseman2*1f;
        if(chosenTactic==20){ damage2-=swmans2*0.27f+horseman2*0.15f;}
        if(sharedPreferences.getInt(String.valueOf(9),0)==1){damage2*=0.95f;}
        if(opponentTactic==2){damage1-=swmans*0.15f;damage2-=swmans2*0.15f;}

        /*if(archers>0){
            r1.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(swmans>0){
            r2.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(horseman>0){
            r3.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(horseman2>0){
            r4.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(swmans2>0){
            r5.setBackgroundColor(getResources().getColor(R.color.Active));
        }
        if(archers2>0){
            r6.setBackgroundColor(getResources().getColor(R.color.Active));
        }*/
        damage1/=3;
        damage2/=3;
        if(chosenTactic==24){damage2*=0.9f;}
        if(chosenTactic==16){damage1*=1.05f;}

        if(damage1<0){damage1=0.1f;}
        if(damage2<0){damage2=0.1f;}
        damage1=focusAdjustment(damage1);
        damage2=focusAdjustment(damage2);
        if(horseman2<damage1&&horseman2!=0){
            damage1-=horseman2;
            ch6=horseman2;
            horseman2=0;
        }else if(horseman2!=0){
            horseman2-=damage1;
            ch6=damage1;
            damage1=0;
        }
        if(swmans2<damage1&&swmans2!=0){
            damage1-=swmans2;
            ch5=swmans2;
            swmans2=0;
        }else if(swmans2!=0){
            swmans2-=damage1;
            ch5=damage1;
            damage1=0;
        }
        if(archers2<damage1&&archers2!=0){
            damage1-=archers2;
            ch4=archers2;
            archers2=0;
        }else if(archers2!=0){
            archers2-=damage1;
            ch4=damage1;
            damage1=0;
        }

        if(horseman<damage2&&horseman!=0){
            damage2-=horseman;
            ch3=horseman;
            horseman=0;
        }else if(horseman!=0){
            horseman-=damage2;
            ch3=damage2;
            damage2=0;
        }
        if(swmans<damage2&&swmans!=0){
            damage2-=swmans;
            ch2=swmans;
            swmans=0;
        }else if(swmans!=0){
            swmans-=damage2;
            ch2=damage2;
            damage2=0;
        }
        if(archers<damage2&&archers!=0){
            damage2-=archers;
            ch1=archers;
            archers=0;
        }else if(archers!=0){
            archers-=damage2;
            ch1=damage2;
            damage2=0;
        }
        refreshNumbers();
    }
    void refreshNumbers(){
        ra0.setText(String.valueOf(twoDForm.format(archers)));
        rs0.setText(String.valueOf(twoDForm.format(swmans)));
        rh0.setText(String.valueOf(twoDForm.format(horseman)));
        ra1.setText(String.valueOf(twoDForm.format(archers2)));
        rs1.setText(String.valueOf(twoDForm.format(swmans2)));
        rh1.setText(String.valueOf(twoDForm.format(horseman2)));

        if(ch1>=0.001){red1.setText("(-"+twoDForm.format(ch1)+")");}else {red1.setText("");}
        if(ch2>=0.001){red2.setText("(-"+twoDForm.format(ch2)+")");}else {red2.setText("");}
        if(ch3>=0.001){red3.setText("(-"+twoDForm.format(ch3)+")");}else {red3.setText("");}
        if(ch4>=0.001){red4.setText("(-"+twoDForm.format(ch4)+")");}else {red4.setText("");}
        if(ch5>=0.001){red5.setText("(-"+twoDForm.format(ch5)+")");}else {red5.setText("");}
        if(ch6>=0.001){red6.setText("(-"+twoDForm.format(ch6)+")");}else {red6.setText("");}

        float x=(float)(archers+horseman+swmans)/(float)total1,y=(float) (swmans2+archers2+horseman2)/(float)total2;
        battleProgress.setProgress(Math.round((1f/(x+y))*x*100));
        if(prev_prog-battleProgress.getProgress()<0){
            if(prev_prog-battleProgress.getProgress()>-10){
                pointer.setImageResource(R.drawable.ic_arrow);
            }else{
                pointer.setImageResource(R.drawable.ic_double_angle_pointing_to_right);
            }
            pointer.setRotation(0);
            pointer.setColorFilter(ContextCompat.getColor(getContext(), R.color.progress0), PorterDuff.Mode.SRC_IN);
        }else{
            if(prev_prog-battleProgress.getProgress()<10){
                pointer.setImageResource(R.drawable.ic_arrow);
            }else{
                pointer.setImageResource(R.drawable.ic_double_angle_pointing_to_right);
            }
            pointer.setRotation(180);
            pointer.setColorFilter(ContextCompat.getColor(getContext(), R.color.progress1), PorterDuff.Mode.SRC_IN);

        }
        prev_prog=battleProgress.getProgress();
        ch1=0;
        ch2=0;
        ch3=0;
        ch4=0;
        ch5=0;
        ch6=0;

    }
    void checkEnd(){
        if(archers+horseman+swmans<0.01f){
            winner=false;
            cd.cancel();
            cd=new CountDownTimer(1200, 1200) {
                public void onTick(long millisUntilFinished) { }
                public void onFinish() {
                    alertDialog();

                }
            }.start();
        }else if(archers2+horseman2+swmans2<0.01f){
            winner=true;
            cd.cancel();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("ks"+hero,1);
            editor.apply();
            cd=new CountDownTimer(1200, 1200) {
                public void onTick(long millisUntilFinished) { }
                public void onFinish() {
                    alertDialog();
                }
            }.start();

        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (BattleScreen.Listener) activity;
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
    }

    private void alertDialog(){
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_battleend,null);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView exp,gain,balance,defeat;
        TableRow r1,r2,r3;
        final ProgressBar pg;
        pg=(ProgressBar) view.findViewById(R.id.progressBar2);

        exp=(TextView)view.findViewById(R.id.kazanilan);
        balance=(TextView)view.findViewById(R.id.top_bakiye);
        defeat=(TextView)view.findViewById(R.id.defeat_text);
        gain=(TextView)view.findViewById(R.id.skor);
        r1=(TableRow) view.findViewById(R.id.LOSE);
        r2=(TableRow) view.findViewById(R.id.WIN);
        r3=(TableRow) view.findViewById(R.id.LEVELUP);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        builder.setCancelable(false);
        Button button;
        button=(Button)view.findViewById(R.id.tamam_button);
        if(winner){
            button.setText("Hurray!");
        }else{
            button.setText("Tragedy...");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeFragment(0);
                builder.dismiss();
            }
        });
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        int lvlbonus=0;
        editor.putInt("exp",sharedPreferences.getInt("exp",0)+exptotal);
        if(sharedPreferences.getInt("exp",0)+exptotal>=Math.round(Math.pow(1.06f,(sharedPreferences.getInt("level",1))-1)*100)){
            editor.putInt("level",sharedPreferences.getInt("level",1)+1);
            lvlbonus=50;
            r3.setVisibility(View.VISIBLE);
            editor.putInt("exp",sharedPreferences.getInt("exp",0)+exptotal-(int)Math.round(Math.pow(1.06f,(sharedPreferences.getInt("level",1))-1)*100));
            editor.apply();
        }
        if(winner){
            r1.setVisibility(View.GONE);
            exptotal+=total2;
            editor.putInt("balance",sharedPreferences.getInt("balance",50)+(total2/2+lvlbonus+oppbonusgold));
            gain.setText("Gained Gold: x"+(total2/2+lvlbonus+oppbonusgold));
            editor.apply();
        }else{
            changeHealth(-1*(8+Math.round(archers*2+horseman2*2+swmans2*2)));
            defeat.setText(" DEFEAT (-%"+String.valueOf((8+Math.round(archers*2+horseman2*2+swmans2*2)))+")");
            r2.setVisibility(View.GONE);
            editor.putInt("balance",sharedPreferences.getInt("balance",50)+(lvlbonus));
            gain.setText("Gained Gold: x"+(lvlbonus));
            editor.apply();
        }
        editor.putInt("exp",sharedPreferences.getInt("exp",0)+exptotal);
        if(sharedPreferences.getInt("exp",0)+exptotal>=Math.round(Math.pow(1.06f,(sharedPreferences.getInt("level",1))-1)*100)){
            editor.putInt("level",sharedPreferences.getInt("level",1)+1);
            lvlbonus=50;
            r3.setVisibility(View.VISIBLE);
            editor.putInt("exp",sharedPreferences.getInt("exp",0)+exptotal-(int)Math.round(Math.pow(1.06f,(sharedPreferences.getInt("level",1))-1)*100));
            editor.apply();
        }


        cd=new CountDownTimer(1800, 150) {
            int c=1;
            public void onTick(long millisUntilFinished) {
                if(c<=10) {
                    if((sharedPreferences.getInt("exp", 0) - exptotal + (((float) c / 10) * (exptotal)))<0){
                        exp.setText("Level Up!");
                        pg.setProgress((Math.round(100*((float)Math.round(sharedPreferences.getInt("exp", 0) - exptotal + (((float) c / 10) * (exptotal))) /Math.round(Math.pow(1.06f, Double.valueOf(sharedPreferences.getInt("level", 1)) - 1) * 100)))));
                        c++;
                    }else {
                        exp.setText("Exp: " + Math.round(sharedPreferences.getInt("exp", 0) - exptotal + (((float) c / 10) * (exptotal))) + "/" + Math.round(Math.pow(1.06f, Double.valueOf(sharedPreferences.getInt("level", 1)) - 1) * 100)+" (+"+exptotal+")");
                        pg.setProgress((Math.round(100*((float)Math.round(sharedPreferences.getInt("exp", 0) - exptotal + (((float) c / 10) * (exptotal))) /Math.round(Math.pow(1.06f, Double.valueOf(sharedPreferences.getInt("level", 1)) - 1) * 100)))));
                        c++;
                    }
                }
            }
            public void onFinish() {
            }
        }.start();
        exp.setText("Exp: "+ sharedPreferences.getInt("exp",0)+"/"+Math.round(Math.pow(1.06f,Double.valueOf(sharedPreferences.getInt("level",1))-1)*100));
        editor.putInt("x",-3);
        editor.apply();
        balance.setText("Balance: x"+sharedPreferences.getInt("balance",50));
        setIconInText(gain);
        setIconInText(balance);
        builder.setView(view);
        builder.show();
        builder.getWindow().setLayout(Resources.getSystem().getDisplayMetrics().widthPixels*8/10, Resources.getSystem().getDisplayMetrics().heightPixels*50/100); //Controlling width and height.

    }
    private void setIconInText(TextView b){
        b.setTransformationMethod(null);
        int konum=b.getText().toString().indexOf("x");
        SpannableString ss = new SpannableString(b.getText());
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.skull);
        d.setBounds(5, 5, b.getLineHeight(),b.getLineHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss.setSpan(span, konum, konum+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        b.setText(ss);
    }

    @Override
    public void onPause() {
        super.onPause();
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
    private void fillGeneralInfo(int x){
        hero=x;
        switch (x){
            case 0:
                oname.setText("King Edward");
                oimage.setImageResource(R.drawable.opponent_0);
                oppbonusgold=120;
                break;
            case 1:
                oname.setText("Loabrader Raider");
                oimage.setImageResource(R.drawable.opponent_1);
                oppbonusgold=175;
                break;
            case 2:
                oname.setText("Philopator Queen");
                oimage.setImageResource(R.drawable.opponent_2);
                oppbonusgold=220;
                break;
            case 3:
                oname.setText("Shah Musman");
                oimage.setImageResource(R.drawable.opponent_3);
                oppbonusgold=250;
                break;
            case 4:
                oname.setText("Emperor Tocha");
                oimage.setImageResource(R.drawable.opponent_4);
                oppbonusgold=380;
                break;
            case 5:
                oname.setText("Dothre Masamune");
                oimage.setImageResource(R.drawable.opponent_5);
                oppbonusgold=550;
                break;
            case 6:
                oname.setText("Joan Darkmai");
                oimage.setImageResource(R.drawable.opponent_6);
                oppbonusgold=850;
                break;
            case 100:
                oname.setText("Warrior Chief");
                oimage.setImageResource(R.drawable.ic_warrior);
                oppbonusgold=14;
                break;
            case 101:
                oname.setText("Fighter Captain");
                oimage.setImageResource(R.drawable.ic_warrior_2);
                oppbonusgold=8;
                break;
            case 102:
                oname.setText("Farmer Leader");
                oimage.setImageResource(R.drawable.ic_farmer);
                oppbonusgold=5;
                break;
            case 200:
                oname.setText("Farmer Leader");
                oimage.setImageResource(R.drawable.ic_haraccilar);
                oppbonusgold=35;
                break;
            case 103:
                oname.setText("No General");
                oimage.setImageResource(R.drawable.torch);
                break;
                default:
                    oname.setText("No General");
                    oimage.setImageResource(R.drawable.torch);
                    break;
        }
    }
    /*private void clearRows(){
        r1.setBackgroundColor(getResources().getColor(R.color.Transparent));
        r2.setBackgroundColor(getResources().getColor(R.color.Transparent));
        r3.setBackgroundColor(getResources().getColor(R.color.Transparent));
        r4.setBackgroundColor(getResources().getColor(R.color.Transparent));
        r5.setBackgroundColor(getResources().getColor(R.color.Transparent));
        r6.setBackgroundColor(getResources().getColor(R.color.Transparent));
    }*/
    private float focusAdjustment(float x){
        switch (focus){
            case 0:
                x=x*0.7f;
                break;
            case 1:
                x=x;
                break;
            case 2:
                x=x*1.3f;
                break;
            default:
                break;
        }
        return x;
    }
}
