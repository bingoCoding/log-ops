package com.bingo.logops.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import com.bingo.logops.Pojo.Pagination;
import com.bingo.logops.dao.BaseDaoCustom;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


public class BaseDaoImpl<T> implements BaseDaoCustom<T> {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public boolean testMethod() {
        return false;
    }

    /**
     * 通过条件查询,查询分页结果
     *
     * @param pageNo		当前页
     * @param pageSize		每页数量
     * @param query			查询条件
     * @return				返回分页数据
     */
    public Pagination<T> getPage(int pageNo, int pageSize, Query query) {
        return _getPage(pageNo, pageSize, getDataCount(query), query);
    }

    /**
     * 通过条件查询,查询分页结果
     *
     * @param pageNo		当前页
     * @param pageSize		每页数量
     * @param totalCount	总记录数
     * @param query			查询条件
     * @return				返回分页数据
     */
    public Pagination<T> getPage(int pageNo, int pageSize, long totalCount, Query query) {
        return _getPage(pageNo, pageSize, totalCount, query);
    }

    /**
     * 通过条件查询,查询分页结果
     *
     * @param pageNo		当前页
     * @param pageSize		每页数量
     * @param totalCount	总记录数
     * @param query			查询条件
     * @return				返回分页数据
     */
    private Pagination<T> _getPage(int pageNo, int pageSize, long totalCount, Query query) {
        Pagination<T> page = new Pagination<T>(pageNo, pageSize, totalCount);
        // 跳过前面的记录
        query.skip(page.getFirstResult());
        // 然后取size条数据
        query.limit(pageSize);
        List<T> datas = this.find(query);
        page.setList(datas);
        return page;
    }

    /**
     * 获取查询数据的数量
     *
     * @param query		查询条件
     * @return			该条件下的数据
     */
    public Long getDataCount(Query query) {
        return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 通过条件查询实体(集合)
     *
     * @param query		查询条件
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query, getEntityClass());
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query		查询条件
     */
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, getEntityClass());
    }

    /**
     * 查询出所有数据
     */
    public List<T> findAll() {
        return mongoTemplate.findAll(getEntityClass());
    }

    /**
     * 查询并且修改记录
     *
     * @param query
     * @param update
     */
    public T findAndModify(Query query, Update update) {

        return mongoTemplate.findAndModify(query, update, getEntityClass());
    }

    /**
     * 按条件查询,并且删除记录
     *
     * @param query
     */
    public T findAndRemove(Query query) {
        return mongoTemplate.findAndRemove(query, getEntityClass());
    }

    /**
     * 按条件查询,并且删除所有符合的数据
     *
     * @param query
     * @return
     */
    public List<T> findAllAndRemove(Query query) {
        return mongoTemplate.findAllAndRemove(query, getEntityClass());
    }

    /**
     * 通过条件查询更新第一条数据
     *
     * @param query			查询
     * @param update		更新
     */
    public UpdateResult updateFirst(Query query, Update update) {
        return mongoTemplate.updateFirst(query, update, getEntityClass());
    }

    /**
     * 通过条件查询更新全部数据
     *
     * @param query			查询
     * @param update		更新
     * @return
     */
    public UpdateResult updateAll(Query query, Update update) {
        return mongoTemplate.updateMulti(query, update, getEntityClass());
    }

    /**
     * 存在更新,不存在插入(未测试)
     *
     * @param query			查询
     * @param update		更新
     * @return
     */
    @Deprecated
    public UpdateResult upsert(Query query, Update update) {
        return mongoTemplate.upsert(query, update, getEntityClass());
    }

//    /**
//     * 保存一个对象到mongodb
//     */
//    public T save(T model) {
//        mongoTemplate.save(model);
//        return model;
//    }

    /**
     * 插入多个对象到mongodb
     */
    public Collection<T> insertAll(Collection<T> lstData) {
        mongoTemplate.insertAll(lstData);
        return lstData;
    }

    /**
     * 通过ID获取记录
     *
     * @param id	id
     */
    public T findById(String id) {
        return mongoTemplate.findById(id, getEntityClass());
    }

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     *
     * @param id
     * @param collectionName 集合名
     */
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, getEntityClass(), collectionName);
    }

    /**
     * 获取集合对象
     *
     * @param collectionName		集合名称
     */
    public MongoCollection<Document> getCollection(String collectionName) {
        return mongoTemplate.getCollection(collectionName);
    }

    /**
     * 执行json命令
     *
     * @param jsonCommand		命令
     */
    public Document executeCommand(String jsonCommand) {
        return mongoTemplate.executeCommand(jsonCommand);
    }

    /**
     * 执行 DBObject 命令
     *
     * @param command			命令
     */
    public Document executeCommand(Document command) {
        return mongoTemplate.executeCommand(command);
    }

    /**
     * 聚合运算
     *
     * @param typedAggregation		聚合
     * @param outputType			返回数据类型
     */
    public <R> AggregationResults<R> aggregate(TypedAggregation<?> typedAggregation, Class<R> outputType) {
        return mongoTemplate.aggregate(typedAggregation, outputType);
    }

    /**
     * 聚合运算
     *
     * @param typedAggregation		聚合
     * @param inputCollectionName	输入集合名
     * @param outputType			返回数据类型
     */
    public <R> AggregationResults<R> aggregate(TypedAggregation<?> typedAggregation, String inputCollectionName,
                                               Class<R> outputType) {
        return mongoTemplate.aggregate(typedAggregation, inputCollectionName, outputType);
    }

    /**
     * 聚合运算
     *
     * @param aggregation			聚合
     * @param inputType				传入数据类型
     * @param outputType			返回数据类型
     * @return
     */
    public <R> AggregationResults<R> aggregate(Aggregation aggregation, Class<?> inputType, Class<R> outputType) {
        return mongoTemplate.aggregate(aggregation, inputType, outputType);
    }

    /**
     * 聚合运算
     *
     * @param aggregation			聚合
     * @param collectionName	输入集合名
     * @param outputType			返回数据类型
     * @return
     */
    public <R> AggregationResults<R> aggregate(Aggregation aggregation, String collectionName, Class<R> outputType) {
        return mongoTemplate.aggregate(aggregation, collectionName, outputType);
    }

    /**
     * MapReduce 运算
     *
     * @param inputCollectionName	输入集合名
     * @param mapFunction			map方法
     * @param reduceFunction		reduce方法
     * @param entityClass			实体类
     * @return
     */
    public MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
                                         Class<T> entityClass) {
        return mongoTemplate.mapReduce(inputCollectionName, mapFunction, reduceFunction, entityClass);
    }

    /**
     * MapReduce 运算
     *
     * @param inputCollectionName	输入集合名
     * @param mapFunction			map方法
     * @param reduceFunction		reduce方法
     * @param mapReduceOptions		操作属性
     * @param entityClass			实体类
     * @return
     */
    public MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
                                         MapReduceOptions mapReduceOptions, Class<T> entityClass) {
        return mongoTemplate.mapReduce(null, inputCollectionName, mapFunction, reduceFunction, mapReduceOptions, entityClass);
    }

    /**
     * MapReduce 运算
     *
     * @param query					查询
     * @param inputCollectionName	输入集合名
     * @param mapFunction			map方法
     * @param reduceFunction		reduce方法
     * @param entityClass			实体类
     * @return
     */
    public MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction,
                                         String reduceFunction, Class<T> entityClass) {
        return mongoTemplate.mapReduce(query, inputCollectionName, mapFunction, reduceFunction, new MapReduceOptions().outputTypeInline(),
                entityClass);
    }

    /**
     * MapReduce 运算
     *
     * @param query					查询
     * @param inputCollectionName	输入集合名
     * @param mapFunction			map方法
     * @param reduceFunction		reduce方法
     * @param mapReduceOptions		操作属性
     * @param entityClass			实体类
     * @return
     */
    public MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction,
                                         String reduceFunction, MapReduceOptions mapReduceOptions, Class<T> entityClass) {
        return mongoTemplate.mapReduce(query, inputCollectionName, mapFunction, reduceFunction, mapReduceOptions, entityClass);
    }

    /**
     * 获取连接的DB对象
     */
    public MongoDatabase getDB() {
        return mongoTemplate.getDb();
    }

    /**
     * 获取需要操作的实体类class
     */
    private Class<T> getEntityClass() {
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType)type;
            return (Class<T>) pt.getActualTypeArguments()[0];
        }
        return null;
    }

}
