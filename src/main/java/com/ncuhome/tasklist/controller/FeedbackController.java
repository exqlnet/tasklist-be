package com.ncuhome.tasklist.controller;


import com.google.gson.JsonObject;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.Feedback;
import com.ncuhome.tasklist.repository.FeedbackRepository;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.JsonUtil;
import com.ncuhome.tasklist.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController extends BaseController {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    ResultVOUtil resultVOUtil;

    @LoginRequired
    @PostMapping("/submit")
    public Object submit(@RequestBody String jsonString){
        Map<String, String> body = new HashMap<>();
        body = JsonUtil.gson.fromJson(jsonString, body.getClass());
        String content = body.get("content");
        String type = body.get("type");
        Feedback feedback = new Feedback(content,type,getUser().getUserId());
        feedbackRepository.save(feedback);
        return resultVOUtil.success("反馈成功");
    }
}
