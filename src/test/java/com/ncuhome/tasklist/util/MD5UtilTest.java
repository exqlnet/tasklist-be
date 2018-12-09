package com.ncuhome.tasklist.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
public class MD5UtilTest {

    @Test
    public void md5() {
        MD5Util md5Util = new MD5Util();
        String res = md5Util.md5("123456");
        System.out.println(res);
    }
    @Test
    public void jsonTest(){
        Map<String, Object> json = JsonUtil.gson.fromJson("{\n" +
                "    \"msg\": \"Unauthorized token\",\n" +
                "    \"status\": 0\n" +
                "}", HashMap.class);
        log.info((String)json.get("msg"));

    }

    @Test
    public void timeFormat() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2017-10-10T12:00:00";
        String timeString = "16:00";

//        log.info("{}", simpleDateFormat.parse("2017-11-11T16:00:00" ));

    }
}