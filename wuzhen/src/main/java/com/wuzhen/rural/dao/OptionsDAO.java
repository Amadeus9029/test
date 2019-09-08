package com.wuzhen.rural.dao;



import com.wuzhen.rural.pojo.Options;
import com.wuzhen.rural.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionsDAO extends JpaRepository<Options,Integer>{
    List<Options> findByQuestion(Question question);
}
