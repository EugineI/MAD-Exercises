package com.example.dialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {
    CharSequence[] items = {"Google", "Apple", "Microsoft"};
    boolean[] itemsChecked = new boolean[items.length];
    private ProgressDialog _progressDialog;
    private int _progress = 0;
    private Handler _progressHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to trigger the dialog
        Button btn = findViewById(R.id.btn_dialog);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(1);  // Show ProgressDialog with ID 1
                _progress = 0;  // Reset progress to 0
                _progressDialog.setProgress(0);
                _progressHandler.sendEmptyMessage(0);  // Start progress handler
            }
        });

        // Handler to update the ProgressDialog
        _progressHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (_progress >= 100) {
                    _progressDialog.dismiss();  // Dismiss when progress reaches 100
                } else {
                    _progress++;
                    _progressDialog.incrementProgressBy(1);  // Increment progress
                    _progressHandler.sendEmptyMessageDelayed(0, 100);  // Update every 100ms
                }
            }
        };
    }

    // Method to create and return Dialog based on ID
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                // ProgressDialog setup
                _progressDialog = new ProgressDialog(this);
                _progressDialog.setIcon(R.drawable.ic_launcher_background);  // Set icon (ensure drawable exists)
                _progressDialog.setTitle("Downloading files...");
                _progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                // "Hide" button in ProgressDialog
                _progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(), "Hide clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });

                // "Cancel" button in ProgressDialog
                _progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });
                return _progressDialog;  // Return configured ProgressDialog
        }
        return null;  // Return null if ID doesn't match
    }
}
