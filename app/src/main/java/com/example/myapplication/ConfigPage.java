package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ConfigPage extends AppCompatActivity {

    private boolean isLevelSelected = false;
    private boolean isSpriteSelected = false;
    private String difficultyLevel;
    private String spriteSelected;

    private String enteredName;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
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
    public void startGame(View v){
        TextInputEditText textInputElement = findViewById(R.id.entered_name);
        String enteredName = textInputElement.getText().toString();
        setEnteredName(enteredName);
        System.out.println("entered name:" + getEnteredName());

        System.out.println("is level selected: " + getIsLevelSelected());
        System.out.println("Level: " + getDifficultyLevel());

        System.out.println("is sprite selected: " + getIsSpriteSelected());
        System.out.println("Level: " + getSpriteSelected());

        if(getEnteredName() == null){ //if the name is not written, it does not return null idk
            //Write error message
        } else if(!getIsLevelSelected()) {
            //Write error message
        } else if(!getIsSpriteSelected()) {
            //Write error message
        } else {
            startActivity(new Intent(ConfigPage.this, GamePage.class));
        }
    }

}
