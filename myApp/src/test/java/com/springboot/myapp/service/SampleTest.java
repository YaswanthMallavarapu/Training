package com.springboot.myapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void demoTest(){
        String actual="hello";
        Assertions.assertEquals("hello",actual);
    }
}
