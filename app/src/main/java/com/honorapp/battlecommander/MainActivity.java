package com.honorapp.battlecommander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import java.util.ArrayList;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity implements AnaMenu.Listener,BattlePre.Listener,BattleScreen.Listener,ShopForAll.Listener,KingHall.Listener,fragment_bet.Listener  {
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    public static int adSwitch=0;
    private String unityGameID = "3516283";
    private Boolean testMode = true;
    private String placementId = "rewardedVideo";
    public static ArrayList<Integer> invnt = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        final UnityAdsListener myAdsListener = new UnityAdsListener ();
        UnityAds.initialize (this, unityGameID, myAdsListener, testMode);

        for(int i=0;i<30;i++){
            invnt.add(sharedPreferences.getInt(String.valueOf(i),0));
        }

        if(sharedPreferences.getInt("x",-1)>=0) {
            changeFragment(1);
        }else if(sharedPreferences.getInt("x",-1)==-2) {
            changeFragment(2);
        }else if(sharedPreferences.getInt("y",0)!=-1){
            changeFragment(6);
        }else{
            changeFragment(0);
        }
    }
    Fragment newFragment;

    @Override
    public void changeFragment(int id){
        if (id == 0) {
            newFragment = new AnaMenu();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == 1) {
            newFragment = new BattlePre();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == 2) {
            newFragment = new BattleScreen();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == 3) {
            newFragment = new ShopForAll();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == 4) {
            newFragment = new KingHall();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == 5) {
            newFragment = new fragment_bet();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (id == 6) {
            newFragment = new Event();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.EkranFrame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if(id==99){
            DisplayRewardedVideoAd();
        }

    }
    public void DisplayRewardedVideoAd () {
        if (UnityAds.isReady (placementId)) {
            UnityAds.show (this);
        }else{
            Toast.makeText(this, "Check Your Internet Connection",
                    Toast.LENGTH_LONG).show();
        }
    }
    private class UnityAdsListener implements IUnityAdsListener {
        @Override
        public void onUnityAdsReady (String placementId) {
            // Implement functionality for an ad being ready to show.
        }

        @Override
        public void onUnityAdsStart (String placementId) {
            // Implement functionality for a user starting to watch an ad.
        }

        @Override
        public void onUnityAdsFinish (String placementId, UnityAds.FinishState finishState) {
            // Implement conditional logic for each ad completion status:
            if (finishState == UnityAds.FinishState.COMPLETED) {
                SharedPreferences  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                switch (adSwitch){
                    case 0:
                        editor.putInt("health",100);
                        editor.apply();
                        break;
                    case 1:
                        editor.putBoolean("kingNextFight",true);
                        editor.apply();
                        break;
                    case 2:
                        editor.putInt("levelcheck",sharedPreferences.getInt("level",1));
                        editor.putInt("balance",sharedPreferences.getInt("balance",50)+250);
                        editor.apply();
                        break;
                    default:
                        break;
                }
            } else if (finishState == UnityAds.FinishState.SKIPPED) {
                // Do not reward the user for skipping the ad.
            } else if (finishState == UnityAds.FinishState.ERROR) {
                // Log an error.
            }
        }

        @Override
        public void onUnityAdsError (UnityAds.UnityAdsError error, String message) {
            // Implement functionality for a Unity Ads service error occurring.
        }
    }
}
