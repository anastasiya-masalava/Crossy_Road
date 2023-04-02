package com.example.myapplication;

import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigPageTest {
    @Mock
    private Context mApplicationContext;
    private ConfigPage configPage;
    //  private AutoCloseable autoClosable;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        configPage = new ConfigPage();
        when(mApplicationContext.getApplicationContext()).thenReturn(mApplicationContext);
    }

    //  MockitoAnnotations.initMocks(this);
    //  configPage = Mockito.mock(ConfigPage.class);
    //  }
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