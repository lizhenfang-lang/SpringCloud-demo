package com.lizhenfang.day01.dao;

import com.lizhenfang.day01.model.OptionLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionLogDao extends MongoRepository<OptionLog,String> {
}
