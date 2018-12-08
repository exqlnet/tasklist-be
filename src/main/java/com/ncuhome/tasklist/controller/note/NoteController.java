package com.ncuhome.tasklist.controller.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.form.NoteForm.CreateNoteForm;
import com.ncuhome.tasklist.form.NoteForm.ModifyNoteForm;
import com.ncuhome.tasklist.service.NoteService;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.JsonUtil;
import com.ncuhome.tasklist.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/note")
public class NoteController extends BaseController {


    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @Autowired
    ResultVOUtil resultVOUtil;

    @LoginRequired
    @PostMapping("/create")
    public Object createNote(@RequestBody CreateNoteForm createNoteForm){
        noteService.createNote(createNoteForm);
        return resultVOUtil.success("创建成功");
    }

    @LoginRequired
    @PutMapping("/modify")
    public Object modifyNote(@RequestBody ModifyNoteForm modifyNoteForm){
        noteService.modifyNote(modifyNoteForm);
        return resultVOUtil.success("保存成功");
    }

    @LoginRequired
    @DeleteMapping("/delete")
    public Object deleteNote(@RequestBody String jsonString){
        Map<String, Object> body = JsonUtil.gson.fromJson(jsonString, HashMap.class);
        Integer noteId = (Integer) body.get("noteId");
        if(noteId == null){
            return resultVOUtil.error("没有找到该便签",404);
        }

        if(!noteService.deleteNote(noteId))return resultVOUtil.error("删除失败");
        return resultVOUtil.success("删除成功");
    }

    @LoginRequired
    @GetMapping("/list")
    public Object listNote(){
        return resultVOUtil.success(noteService.listNote());
    }
}
