package com.bingo.logops.service.impl;

import com.bingo.logops.Pojo.AccessLogVO;
import com.bingo.logops.Pojo.Pagination;
import com.bingo.logops.common.constant.AccessLogConstant;
import com.bingo.logops.dao.IAccessLogDao;
import com.bingo.logops.dao.impl.AccessLogDao;
import com.bingo.logops.entity.AccessLogModel;
import com.bingo.logops.service.IAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AccessLogService implements IAccessLogService {

    @Autowired
    private IAccessLogDao accessLogDao;

    /**
     * 保存访问日志
     *
     * @param model
     */
    public void saveAccessLog(AccessLogModel model) {
        accessLogDao.save(model);
    }

    /**
     * 删除访问日志（date之前的数据）
     *
     * @param date		日期
     */
    public int deleteAccessLog(Date date) {
        return accessLogDao.findAllAndRemove(
                new Query(new Criteria(AccessLogConstant.ACCESS_DATE).lt(date))).size();
    }

    /**
     * 获取访问日志列表
     *
     * @param vo		查询参数
     */
    @Override
    public Pagination<AccessLogModel> getAccessLogList(AccessLogVO vo) {

        Query query = new Query();
        Criteria criteria = null;

//        // 日期条件
//        if (vo.getDateBegin().getTime() == vo.getDateEnd().getTime()) {
//            criteria = Criteria.where(AccessLogConstant.ACCESS_DATE).is(vo.getDateBegin());
//        } else {
//            criteria = Criteria.where(AccessLogConstant.ACCESS_DATE).gte(vo.getDateBegin()).lte(vo.getDateEnd());
//        }
//
//        // app名称
//        if (StringUtil.isNotEmpty(vo.getAppName())) {
//            criteria.and(AccessLogConstant.APP_NAME).is(vo.getAppName());
//        }
//
//        // uid
//        if (vo.getUid() != null) {
//            criteria.and(AccessLogConstant.UID).is(vo.getUid());
//        }
//
//        // url
//        if (StringUtil.isNotEmpty(vo.getUrl())) {
//            Criteria cUrl = new Criteria(AccessLogConstant.URL).regex(Pattern.compile("^.*" + vo.getUrl() + ".*$", Pattern.CASE_INSENSITIVE));
//            criteria.andOperator(cUrl);
//        }
//
//        // 客户端ip
//        if (StringUtil.isNotEmpty(vo.getIp())) {
//            Criteria forward = new Criteria(AccessLogConstant.FORWARDED_FOR).is(vo.getIp());
//            Criteria remote = new Criteria(AccessLogConstant.REOMOTE_IP).is(vo.getIp());
//            criteria.orOperator(forward, remote);
//        }
//
//        // 参数
//        if (StringUtil.isNotEmpty(vo.getParam())) {
//            Criteria cPost = new Criteria(AccessLogConstant.POST).regex(Pattern.compile("^.*" + vo.getParam() + ".*$", Pattern.CASE_INSENSITIVE));
//            Criteria cQuery = new Criteria(AccessLogConstant.QUERY).regex(Pattern.compile("^.*" + vo.getParam() + ".*$", Pattern.CASE_INSENSITIVE));
//            criteria.orOperator(cPost, cQuery);
//        }

        return accessLogDao.getPage(vo.getPageNum(), vo.getPageSize(),
                query.addCriteria(criteria)
                        .with(new PageRequest(
                                vo.getPageNum(), vo.getPageSize(), Sort.Direction.DESC, AccessLogConstant.ACCESS_TIME)));
    }

    /**
     * 获取URL调用数据
     *
     * @param date		日期
     */
    @SuppressWarnings("rawtypes")
    public List<HashMap> getUrlCallData(Date date) {
        // 只查询指定日期的数据
        Criteria criteria = Criteria.where(AccessLogConstant.ACCESS_DATE).is(date);

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(AccessLogConstant.APP_NAME, AccessLogConstant.URL).count().as("count")
                        .avg(AccessLogConstant.PROCESS_TIME).as("process_time"),
                Aggregation.project(Fields.from(Fields.field("count"),
                        Fields.field("process_time"),
                        Fields.field("url", AccessLogConstant.URL),
                        Fields.field("appName", AccessLogConstant.APP_NAME)))
                        .andExclude(AccessLogConstant.ID)
        );

        return accessLogDao.aggregate(agg,
                AccessLogConstant.COLLECTION_NAME, HashMap.class).getMappedResults();
    }



    /**
     * 获取各个应用的用户访问数据
     *
     * @param date		日期
     */
    @SuppressWarnings("rawtypes")
    public List<HashMap> getUserUseDataByAppName(Date date) {
        // 只查询指定日期的数据
        Criteria criteria = Criteria.where(AccessLogConstant.ACCESS_DATE).is(date);

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(AccessLogConstant.APP_NAME, AccessLogConstant.UID),
                Aggregation.group(AccessLogConstant.APP_NAME).count().as("count"),
                Aggregation.project(Fields.from(Fields.field("count"),
                        Fields.field("appName", AccessLogConstant.ID)))
                        .andExclude(AccessLogConstant.ID)
        );

        return accessLogDao.aggregate(agg,
                AccessLogConstant.COLLECTION_NAME, HashMap.class).getMappedResults();
    }
}
