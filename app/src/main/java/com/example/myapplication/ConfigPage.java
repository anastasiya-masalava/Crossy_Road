package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ConfigPage extends AppCompatActivity {
    private boolean isLevelSelected = false;
    private boolean isSpriteSelected = false;
    private String difficultyLevel;
    private String spriteSelected;
    private String enteredName;

    public void onLevelSelected(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        this.isLevelSelected = checked;

        // Check which radio button was clicked
        switch (view.getId()) {
        case R.id.easyLevel:
            if (checked) {
                this.difficultyLevel = "Easy";
            }
            break;
        case R.id.mediumLevel:
            if (checked) {
                this.difficultyLevel = "Medium";
            }
            break;
        case R.id.hardLevel:
            if (checked) {
                this.difficultyLevel = "Hard";
            }
            break;
        default:
            throw new RuntimeException("Impossible Case.");
        }
    }

    public void onSpriteSelected(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        this.isSpriteSelected = checked;

        // Check which radio button was clicked
        switch (view.getId()) {
        case R.id.frog:
            if (checked) {
                this.spriteSelected = "Frog";
            }
            break;
        case R.id.fox:
            if (checked) {
                this.spriteSelected = "Fox";
            }
            break;

        case R.id.bunny:
            if (checked) {
                this.spriteSelected = "Bunny";
            }
            break;
        default:
            throw new RuntimeException("Impossible Case.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }

    public void makeAlertBuilder(String alertTitle, String alertMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfigPage.this);

        builder.setCancelable(false);
        builder.setTitle(alertTitle);
        builder.setMessage(alertMessage);

        builder.setNegativeButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private boolean validateUserName(String userName) {
        return (userName == null || userName.trim().length() < 1);
    }

    public void startGame(View v) {
        TextInputEditText textInputElement = findViewById(R.id.entered_name);
        String enteredName = textInputElement.getText().toString();
        this.enteredName = enteredName;

        String alertTitle = "";
        String alertMessage = "";

        if (validateUserName(enteredName)) {
            alertTitle = "Name not entered!";
            alertMessage = "Please enter a valid name to proceed";
            makeAlertBuilder(alertTitle, alertMessage);
        } else if (!isLevelSelected) {
            alertTitle = "Difficulty level is not selected!";
            alertMessage = "Please select a difficulty level to proceed";
            makeAlertBuilder(alertTitle, alertMessage);
        } else if (!isSpriteSelected) {
            alertTitle = "Sprite is not selected!";
            alertMessage = "Please select a sprite to proceed";
            makeAlertBuilder(alertTitle, alertMessage);
        } else {
            System.out.println("ENTERED name: " + enteredName);
            Bundle extras = new Bundle();
            extras.putString("player_name", enteredName);
            extras.putString("difficulty", difficultyLevel);
            extras.putString("sprite", spriteSelected);
            Intent intent = new Intent(ConfigPage.this, GamePage.class);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }
}
