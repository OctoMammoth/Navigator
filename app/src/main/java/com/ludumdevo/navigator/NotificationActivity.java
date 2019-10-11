package com.ludumdevo.navigator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class NotificationActivity extends Activity
{
  // GUI parameters
  private TextView  mTitleLabel   = null;
  private TextView  mTextLabel    = null;
  
  private int       mId           = 0;
  private String    mName         = "";
  private String    mUuid         = "";
  
  /** Called when the activity is first created */
  @Override public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_notification);
    
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                         WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    
    mId   = getIntent().getIntExtra("zone_id", 0);
    mUuid = getIntent().getStringExtra("zone_uuid");
    mName = getIntent().getStringExtra("zone_name");

    mTitleLabel = (TextView)findViewById(R.id.notification__title_label);
    mTextLabel  = (TextView)findViewById(R.id.notification__text_label);
    
    mTextLabel.setText("Вы зашли в зону '" + mName + "'");
  }
  
  public void onClose(View v)
  {
    finish();
  }
  
}
