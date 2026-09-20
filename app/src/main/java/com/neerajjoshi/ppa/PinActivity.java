package com.neerajjoshi.ppa;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PinActivity extends AppCompatActivity {

    EditText pinED;
    String CORRECT_PIN = "1234";
    Vibrator vibrator;
    TextView pintv;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String FUNCTION_EXECUTED_KEY = "isPinSet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        pintv = findViewById(R.id.tv_PIN);
        if (!ispinSet()) {
            // Execute your function here
            setPin();

            // Set the flag to indicate that the function has been executed
            setFunctionExecutedFlag();
        }

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        pinED = findViewById(R.id.editTextNumberPassword);


        pinED.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.length() ==4)
                {
                    submitPIN(charSequence.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        pinED.requestFocus();

    }


    private void submitPIN(String pin)
    {
        if (pin.equals(getPinFromSharedPreferences())) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else {
            pinED.setText("");
            long[] vibrationPattern = {0, 100, 100, 100}; // Vibrate for 100 milliseconds, pause for 100 milliseconds, repeat
            vibrator.vibrate(vibrationPattern, -1); // -1 means do not repeat the pattern

  //          ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(pintv, "alpha", 0f, 1f);

            // Set the duration of the animation (in milliseconds)
//            alphaAnimator.setDuration(1000);

            // Start the animation
//            alphaAnimator.start();

            startAnimations();

            Toast.makeText(PinActivity.this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean ispinSet()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(FUNCTION_EXECUTED_KEY, false);
    }

    private void setFunctionExecutedFlag()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FUNCTION_EXECUTED_KEY, true);
        editor.apply();
    }

    private void savePinInSharedPreferences(String pin) {
        // Get shared preferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Get the editor to modify preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put the PIN value in the editor
        editor.putString("pinKey", pin);

        // Apply the changes
        editor.apply();
    }

    private String getPinFromSharedPreferences()
    {
        // Get shared preferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the PIN value using the key
        // The second parameter is the default value in case the key is not found
        return sharedPreferences.getString("pinKey", "");
    }

    private void setPin()
    {
        savePinInSharedPreferences("1234");
    }
    private void startAnimations() {
        // Create an ObjectAnimator to animate the translationX property of pintv
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat( pinED,"translationX", -20f, 20f);
        translationAnimator.setDuration(5);
        translationAnimator.setInterpolator(new LinearInterpolator());
        translationAnimator.setRepeatCount(70);
        translationAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        translationAnimator.start();
    }


    public void onKeyClick(View view) {
        pinED.append(((Button)view).getText().toString());
    }
}