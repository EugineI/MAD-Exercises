package com.example.lab7;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.KeyEvent;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends Activity {
    String tag = "Events";
    int request_Code = 1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//---hides the title bar---
//requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Log.d(tag, "In the onCreate() event");
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
        {
//startActivity(new Intent(“net.learn2develop.ACTIVITY2”));
//startActivity(new Intent(this, Activity2.class));
            startActivityForResult(new Intent("net.learn2develop.ACTIVITY2"),
                    request_Code);
        }
        return false;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,data.getData().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onStart() { //... }
    public void onRestart() { //... }
    public void onResume() { //... }
    public void onPause() { //... }
    public void onStop() { //... }
    public void onDestroy() { //... }
}