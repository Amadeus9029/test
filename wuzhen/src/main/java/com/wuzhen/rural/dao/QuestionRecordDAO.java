package com.wuzhen.rural.dao;


import com.wuzhen.rural.pojo.Question_Record;
import com.wuzhen.rural.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRecordDAO extends JpaRepository<Question_Record,Integer>{
        List<Question_Record> findByUser(User user);
}
