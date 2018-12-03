package com.ncuhome.tasklist.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void md5() {
        MD5Util md5Util = new MD5Util();
        String res = md5Util.md5("hello world");
        System.out.println(res);
    }
}