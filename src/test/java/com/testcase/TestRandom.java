package com.testcase;

import org.testng.annotations.Test;

import java.util.Objects;
import java.util.Random;

public class TestRandom {
    @Test
    public void testRandom1(){
        Random r=new Random();
        int randNum=r.nextInt(2);
        System.out.println(randNum);
        int b=0;
        b=4/randNum;
    }

}
