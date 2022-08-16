package com.testcase;
import org.testng.annotations.Test;

public class TestDBConnection {
    @Test(enabled = false)
    public void runOtherTest1() {
        System.out.println("@Test - runOtherTest1");
    }

    @Test
    public void runOtherTest2() {
        System.out.println("@Test - runOtherTest2");
    }

}
