package com.score;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScoreApp extends Base {
    @BeforeTest
    public void launchApp() throws Exception {
        Base baseclass = new Base();
        baseclass.InitiateDriver();

    }

    @Test
    public void myTest1() throws Exception {
       runScenario();
    }

}
