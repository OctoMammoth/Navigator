package com.ludumdevo.navigator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.navigine.naviginesdk.Location;
import com.navigine.naviginesdk.NavigineSDK;

public class SplashActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback
{

  private Context   mContext     = this;
  private TextView  mStatusLabel = null;
  
  @Override public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    // Setting up NavigineSDK parameters
    NavigineSDK.setParameter(mContext, "debug_level", 2);
    NavigineSDK.setParameter(mContext, "actions_updates_enabled",  false);
    NavigineSDK.setParameter(mContext, "location_updates_enabled", true);
    NavigineSDK.setParameter(mContext, "location_loader_timeout",  60);
    NavigineSDK.setParameter(mContext, "location_update_timeout",  300);
    NavigineSDK.setParameter(mContext, "location_retry_timeout",   300);
    NavigineSDK.setParameter(mContext, "post_beacons_enabled",     true);
    NavigineSDK.setParameter(mContext, "post_messages_enabled",    true);
    
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_splash);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                         WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    mStatusLabel = findViewById(R.id.splash__status_label);
    
    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION,
                                                           Manifest.permission.ACCESS_COARSE_LOCATION,
                                                           Manifest.permission.READ_EXTERNAL_STORAGE,
                                                           Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
  }

  @Override public void onBackPressed()
  {
    moveTaskToBack(true);
  }
  
  @SuppressLint("SetTextI18n")
  @Override public void onRequestPermissionsResult(int requestCode,
                                                   String permissions[],
                                                   int[] grantResults)
  {
    boolean permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED &&
                                 ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    boolean permissionStorage  = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED &&
                                 ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    switch (requestCode)
    {
      case 101:
        if (!permissionLocation || (D.WRITE_LOGS && !permissionStorage))
          finish();
        else
        {
          if (NavigineSDK.initialize(mContext, D.USER_HASH, D.SERVER_URL))
          {
            NavigineSDK.loadLocationInBackground(D.LOCATION_NAME, 30,
              new Location.LoadListener()
              {
                @Override public void onFinished()
                {
                  Intent intent = new Intent(mContext, MainActivity.class);
                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  mContext.startActivity(intent);
                }
                @Override public void onFailed(int error)
                {
                  mStatusLabel.setText("Что-то пошло не так' (error " + error + ")! " +
                                       "Попробуйте в другой раз");
                }
                @Override public void onUpdate(int progress)
                {
                  mStatusLabel.setText("Загружается локация: " + progress + "%");
                }
              });
          }
          else
          {
            mStatusLabel.setText("Ошибка загрузки ядра");
          }
        }
        break;
    }
  }
}
