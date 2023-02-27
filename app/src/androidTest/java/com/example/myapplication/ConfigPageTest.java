package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConfigPageTest
{
    @Mock
    private ConfigPage configPage;
    private AutoCloseable autoClosable;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void setAlertTextView() {
    }

    @Test
    public void isLevelSelectedTest() {
        Boolean input = true;
        configPage.setIsLevelSelected(input);
        Boolean output = configPage.getIsLevelSelected();

        Assert.assertEquals(input, output);
    }

    @Test
    public void difficultyLevelTest() {
        String input = "Easy";
        configPage.setDifficultyLevel(input);
        String output = configPage.getDifficultyLevel();

        Assert.assertEquals(input, output);
    }

    @Test
    public void isSpriteSelectedTest() {
        Boolean input = true;
        configPage.setIsSpriteSelected(input);
        Boolean output = configPage.getIsSpriteSelected();

        Assert.assertEquals(input, output);
    }

    @Test
    public void spriteSelectedTest() {
        String input = "Dog";
        configPage.setSpriteSelected(input);
        String output = configPage.getSpriteSelected();

        Assert.assertEquals(input, output);
    }

    @Test
    public void enteredNameTest() {
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