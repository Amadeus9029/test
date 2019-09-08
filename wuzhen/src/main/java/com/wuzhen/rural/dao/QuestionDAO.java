package com.wuzhen.rural.dao;


import com.wuzhen.rural.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDAO extends JpaRepository<Question,Integer>{
    @Query(value="SELECT * FROM question",nativeQuery = true)
     List<Question> questionList();



}
