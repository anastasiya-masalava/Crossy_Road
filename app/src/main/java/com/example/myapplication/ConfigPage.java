package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ConfigPage extends AppCompatActivity {
//    private Button startGamebtn;
    private TextView alertTextView;
    private boolean isLevelSelected = false;
    private boolean isSpriteSelected = false;
    private String difficultyLevel;
    private String spriteSelected;
    private String enteredName;

    public void setAlertTextView(TextView textView){
        this.alertTextView = textView;
    }
    public void setIsLevelSelected(boolean isSelected){
        this.isLevelSelected = isSelected;
    }

    public boolean getIsLevelSelected(){
        return isLevelSelected;
    }

    public void setDifficultyLevel(String difficultyLevel){
        this.difficultyLevel = difficultyLevel;
    }

    public String getDifficultyLevel(){
        return difficultyLevel;
    }

    public void setIsSpriteSelected(boolean isSelected){
        this.isSpriteSelected = isSelected;
    }

    public boolean getIsSpriteSelected(){
        return isSpriteSelected;
    }

    public void setSpriteSelected(String spriteSelected){
        this.spriteSelected = spriteSelected;
    }

    public String getSpriteSelected(){
        return spriteSelected;
    }

    public void setEnteredName(String enteredName){
        this.enteredName = enteredName;
    }

    public String getEnteredName(){
        return enteredName;
    }

    public void onLevelSelected(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        setIsLevelSelected(checked);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.easyLevel:
                if (checked)
                    setDifficultyLevel("Easy");
                    break;
            case R.id.mediumLevel:
                if (checked)
                    setDifficultyLevel("Medium");
                    break;

            case R.id.hardLevel:
                if (checked)
                    setDifficultyLevel("Hard");
                    break;
        }
    }

    public void onSpriteSelected(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        setIsSpriteSelected(checked);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.frog:
                if (checked)
                    setSpriteSelected("Frog");
                break;
            case R.id.fox:
                if (checked)
                    setSpriteSelected("Fox");
                break;

            case R.id.bunny:
                if (checked)
                    setSpriteSelected("Bunny");
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }
    public void makeAlertBuilder(String alertTitle, String alertMessage){
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
    public void startGame(View v){
        TextInputEditText textInputElement = findViewById(R.id.entered_name);
        String enteredName = textInputElement.getText().toString();
        setEnteredName(enteredName);

        String alertTitle = "";
        String alertMessage = "";

        if(getEnteredName().matches("")){
            alertTitle = "name not entered!";
            alertMessage = "please enter you name to proceed";
            makeAlertBuilder(alertTitle, alertMessage);
        } else if(!getIsLevelSelected()) {
            alertTitle = "difficulty level is not selected!";
            alertMessage = "please select a difficulty level to proceed";
            makeAlertBuilder(alertTitle, alertMessage);
        } else if(!getIsSpriteSelected()) {
            alertTitle = "sprite is not selected!";
            alertMessage = "please select a sprite to proceed";
            makeAlertBuilder(alertTitle, alertMessage);
        } else {
            startActivity(new Intent(ConfigPage.this, GamePage.class));
        }

    }

}
