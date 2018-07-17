package com.bingo.logops.dao;

import com.bingo.logops.Pojo.Pagination;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

public interface BaseDaoCustom<T> {
    boolean testMethod();

    Pagination<T> getPage(int pageNo, int pageSize, Query query);

    Pagination<T> getPage(int pageNo, int pageSize, long totalCount, Query query);

    Long getDataCount(Query query);

    List<T> find(Query query);

    T findOne(Query query);

    List<T> findAll();

    T findAndModify(Query query, Update update) ;

    T findAndRemove(Query query);

    List<T> findAllAndRemove(Query query);

    UpdateResult updateFirst(Query query, Update update) ;

    UpdateResult updateAll(Query query, Update update) ;

    UpdateResult upsert(Query query, Update update) ;

//    /**
//     * 保存一个对象到mongodb
//     */
//    public T save(T model) {
//        mongoTemplate.save(model);
//        return model;
//    }

    Collection<T> insertAll(Collection<T> lstData);

//    T findById(String id);

    T findById(String id, String collectionName);

    MongoCollection<Document> getCollection(String collectionName);

    Document executeCommand(String jsonCommand);

    Document executeCommand(Document command);

    <R> AggregationResults<R> aggregate(TypedAggregation<?> typedAggregation, Class<R> outputType) ;

    <R> AggregationResults<R> aggregate(TypedAggregation<?> typedAggregation, String inputCollectionName,
                                               Class<R> outputType) ;

    <R> AggregationResults<R> aggregate(Aggregation aggregation, Class<?> inputType, Class<R> outputType) ;

    <R> AggregationResults<R> aggregate(Aggregation aggregation, String collectionName, Class<R> outputType) ;

    MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
                                         Class<T> entityClass) ;

    MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
                                         MapReduceOptions mapReduceOptions, Class<T> entityClass);

    MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction,
                                         String reduceFunction, Class<T> entityClass) ;

    MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction,
                                         String reduceFunction, MapReduceOptions mapReduceOptions, Class<T> entityClass) ;

    MongoDatabase getDB();

}
