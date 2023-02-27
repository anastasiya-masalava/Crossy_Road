package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class ConfigPageTest {

    private ConfigPage configPage;

    @Test
    public void setAlertTextView() {
    }

    @Test
    public void isLevelSelectedTest() {
        configPage = new ConfigPage();
        Boolean input = true;
        configPage.setIsLevelSelected(input);
        Boolean output = configPage.getIsLevelSelected();

        Assert.assertEquals(input, output);
    }

    @Test
    public void difficultyLevelTest() {
        configPage = new ConfigPage();
        String input = "Easy";
        configPage.setDifficultyLevel(input);
        String output = configPage.getDifficultyLevel();

        Assert.assertEquals(input, output);
    }

    @Test
    public void isSpriteSelectedTest() {
        configPage = new ConfigPage();
        Boolean input = true;
        configPage.setIsSpriteSelected(input);
        Boolean output = configPage.getIsSpriteSelected();

        Assert.assertEquals(input, output);
    }

    @Test
    public void spriteSelectedTest() {
        configPage = new ConfigPage();
        String input = "Dog";
        configPage.setSpriteSelected(input);
        String output = configPage.getSpriteSelected();

        Assert.assertEquals(input, output);
    }

    @Test
    public void enteredNameTest() {
        configPage = new ConfigPage();
        String input = "Brandi";
        configPage.setEnteredName(input);
        String output = configPage.getEnteredName();

        Assert.assertEquals(input, output);
    }

    @Test
    public void onLevelSelected() {
    }

    @Test
    public void onSpriteSelected() {
    }
}