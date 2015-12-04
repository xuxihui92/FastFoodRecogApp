package com.example.admin.foodanalyzer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.AlertDialog;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;


public class SecondActivity extends AppCompatActivity {

    private Button confirm;
    private RadioGroup rg;
    private View selectedRadioButton;
    private int radioButtonId = Integer.MIN_VALUE;
    private boolean checked = false;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    public int getRadioButtonId() {
        return radioButtonId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Restaurants List");
        setContentView(R.layout.activity_second);
        Log.d("SecondActivity", "onCreate execute");
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        confirm = (Button)findViewById(R.id.confirm);

        /** check if confirm button is clicked */
        confirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (!checked) {
                    AlertDialog alert= new AlertDialog.Builder(SecondActivity.this).create();
                    alert.setTitle("Exception:Complete the selection");
                    alert.setMessage("Please select one restaurant!");
                    Toast.makeText(SecondActivity.this,
                            "Please select one restaurant!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SecondActivity.this,
                            "You clicked Confirm", Toast.LENGTH_SHORT).show();
                    // create imageIntent to take a photo
                    Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // create a file to save the image
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                    // start the image capture Intent
                    startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            }
        });

    }

    /** check one of radio buttons is clicked */
    public void onRadioButtonClicked(View v) {
        checked = ((RadioButton) v).isChecked();
        selectedRadioButton = v;
        // Check which radio button was clicked
        if (checked){
            switch (selectedRadioButton.getId()) {
                case R.id.res1:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 1", Toast.LENGTH_SHORT).show();
                    radioButtonId = 1;
                    break;
                case R.id.res2:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 2", Toast.LENGTH_SHORT).show();
                    radioButtonId = 2;
                    break;
                case R.id.res3:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 3", Toast.LENGTH_SHORT).show();
                    radioButtonId = 3;
                    break;
                case R.id.res4:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 4", Toast.LENGTH_SHORT).show();
                    radioButtonId = 4;
                    break;
                case R.id.res5:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 5", Toast.LENGTH_SHORT).show();
                    radioButtonId = 5;
                    break;
                case R.id.res6:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 6", Toast.LENGTH_SHORT).show();
                    radioButtonId = 6;
                    break;
                case R.id.res7:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 7", Toast.LENGTH_SHORT).show();
                    radioButtonId = 7;
                    break;
                case R.id.res8:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 8", Toast.LENGTH_SHORT).show();
                    radioButtonId = 8;
                    break;
                case R.id.res9:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 9", Toast.LENGTH_SHORT).show();
                    radioButtonId = 9;
                    break;
                case R.id.res10:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 10", Toast.LENGTH_SHORT).show();
                    radioButtonId = 10;
                    break;
                case R.id.res11:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 11", Toast.LENGTH_SHORT).show();
                    radioButtonId = 11;
                    break;
                case R.id.res12:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 12", Toast.LENGTH_SHORT).show();
                    radioButtonId = 12;
                    break;
                case R.id.res13:
                    Toast.makeText(SecondActivity.this,
                            "You choose Restaurant 13", Toast.LENGTH_SHORT).show();
                    radioButtonId = 13;
                    break;
                default: radioButtonId = Integer.MIN_VALUE;
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
                Toast.makeText(this, "Image cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // Image capture failed, advise user
                Toast.makeText(this, "Image capture failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /** Create a file Uri for saving an image */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".png");
        } else {
            return null;
        }
        return mediaFile;
    }

}


