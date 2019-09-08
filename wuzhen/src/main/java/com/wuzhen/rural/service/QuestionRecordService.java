package com.wuzhen.rural.service;

import com.wuzhen.rural.dao.QuestionRecordDAO;
import com.wuzhen.rural.pojo.Question_Record;
import com.wuzhen.rural.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionRecordService {
    @Autowired
    QuestionRecordDAO questionRecordDAO;

    public List<Question_Record> list(User user){
        return questionRecordDAO.findByUser(user);
    }
    //添加
    public void add(Question_Record question_record){
        questionRecordDAO.save(question_record);
    }
}

