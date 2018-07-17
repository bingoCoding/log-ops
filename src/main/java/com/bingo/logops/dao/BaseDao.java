package com.bingo.logops.dao;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface BaseDao<T,ID> extends MongoRepository<T,ID>,BaseDaoCustom<T>{

}
