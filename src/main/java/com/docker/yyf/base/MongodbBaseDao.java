package com.docker.yyf.base;


import com.docker.yyf.entity.BaseEntity;
import com.docker.yyf.util.ClassUtil;
import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.IndexOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.script.NamedMongoScript;

import javax.annotation.PostConstruct;
import javax.xml.stream.events.Comment;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 18329
 */

public abstract class MongodbBaseDao<T extends BaseEntity> implements IMongoBaseDao<T> {
    /**
     * spring mongodb　集成操作类　
     */
    @Autowired
    private  MongoTemplate mongoTemplate;
    /**
     * 通过条件查询实体(集合)
     *
     * @param record
     */
    @Override
    public List<T> select(T record) {
        Query query = new Query();
        try {
            this.setQuery(query,record);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return mongoTemplate.find(query, this.getEntityClass());
    }


    public void aggregate(T record){

    }
    /**
     * 通过一定的条件查询一个实体
     *
     * @param record
     * @return
     */
    @Override
    public T selectOne(T record) {
        Query query = new Query();
        try {
            this.setQuery(query,record);
        }catch (Exception e){
            System.err.println(e);
        }
        return mongoTemplate.findOne(query, this.getEntityClass());
    }
    /**
     * 查询出所有数据
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return this.mongoTemplate.findAll(getEntityClass());
    }

    @Override
    public List<T> select(T record,OtherQuery otherQuery){
        Query query = new Query();
        try {
            this.setQuery(query,record);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.setQuery(query,otherQuery);
        return mongoTemplate.find(query, this.getEntityClass());
    }
    @Override
    public BasePage<T> selectPage(T record, OtherQuery otherQuery, int pageSize, int pageNum){
        Query query = new Query();
        BasePage<T> page = new BasePage<T>();
        try {
            this.setQuery(query,record);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        long count = mongoTemplate.count(query, this.getEntityClass());
        page.setTotalCount(count);
        this.setQuery(query,otherQuery);
        query.skip((pageNum - 1) * pageSize);
        query.limit(pageSize);
        List<T> list = mongoTemplate.find(query, this.getEntityClass());
        page.setData(list);
        return page;
    }

    /**
     * 查询并且修改记录
     *
     * @param query
     * @param update
     * @return
     */
    @Override
    public T findAndModify(Query query, Update update) {
        return this.mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }
    /**
     * 按条件查询,并且删除记录
     *
     * @param query
     * @return
     */
    @Override
    public T findAndRemove(Query query) {
        return this.mongoTemplate.findAndRemove(query, this.getEntityClass());
    }
    /**
     * 通过条件查询更新数据
     *
     * @return
     */
    @Override
    public void updateFirst(T record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        record.setUpdateTime(new Date());
        Update update = new Update();
        try {
            setValue(update,record);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        WriteResult writeResult = mongoTemplate.updateFirst(query, update, this.getEntityClass());
        System.out.println(writeResult);
    }
    /**
     * 保存一个对象到mongodb
     *
     * @param record
     * @return
     */
    @Override
    public void save(T record) {
        Date date = new Date();
        if (StringUtils.isBlank(record.getId())){
            record.setId(getSequenceId(record));
        }
        record.setCreateTime(date);
        record.setUpdateTime(date);
        mongoTemplate.insert(record);
    }


    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    @Override
    public long count(T record){
        Query query = new Query();
        try {
            this.setQuery(query,record);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return mongoTemplate.count(query,getEntityClass());
    }


    /**
     * 获取需要操作的实体类class
     */
    protected abstract Class<T> getEntityClass();

    private String getSequenceId(T record){
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        boolean exists = scriptOps.exists("getNextSequence");

        Double call;
        try {
            call =  (Double)scriptOps.call("getNextSequence", getEntityClass().getSimpleName());
        }catch (UncategorizedMongoDbException e){
            mongoTemplate.upsert(new Query(Criteria.where("_id").is(getEntityClass().getSimpleName())),Update.update("seq", 0L),"counters");
            call = (Double) scriptOps.call("getNextSequence", getEntityClass().getSimpleName());
        }
        return String.valueOf(call.intValue());
    }

    private void setValue(Update update,T record) throws IllegalAccessException {
        if (record==null){
            return;
        }
        Class<? extends BaseEntity> recordClass = record.getClass();
        Field[] fs = recordClass.getDeclaredFields();
        for (Field field : fs) {
            field.setAccessible(true);
            if (field.get(record) !=null){
                update.set(field.getName(), field.get(record));
            }
        }
    }
    private void setQuery(Query query, T record) throws IllegalAccessException {
        if (record==null){
            return;
        }
        if (StringUtils.isNotBlank(record.getId())){
            query.addCriteria(Criteria.where("id").is(record.getId()));
        }
        Class<? extends BaseEntity> recordClass = record.getClass();
        Field[] fs = recordClass.getDeclaredFields();
        for (Field field : fs) {
            field.setAccessible(true);
            if (field.get(record) !=null){
                if(field.getType().isAssignableFrom(List.class)){
                    List list = (List) field.get(record);
                    for (int i=0;i<list.size();i++){
                        Class listClass= list.get(i).getClass();
                        Field[] fss = listClass.getDeclaredFields();
                        for (Field field1:fss){
                            field1.setAccessible(true);
                            if (field1.get(list.get(i))!=null){
                                query.addCriteria(Criteria.where(field.getName()).elemMatch(Criteria.where(field1.getName()).is(field1.get(list.get(i)))));
                            }
                        }
                    }
                }else {
                    query.addCriteria(Criteria.where(field.getName()).is(field.get(record)));
                }
            }
        }
    }
    private void setQuery(Query query,OtherQuery otherQuery){
        if (query==null||otherQuery==null){
            return;
        }
        if (otherQuery.getCreateTimeBegin()!=null){
            query.addCriteria(Criteria.where("createTime").gte(otherQuery.getCreateTimeBegin()));
        }
        if (otherQuery.getCreateTimeEnd()!=null){
            query.addCriteria(Criteria.where("createTime").gt(otherQuery.getCreateTimeEnd()));
        }
        if (otherQuery.getUpdateTimeBegin()!=null){
            query.addCriteria(Criteria.where("updateTime").gte(otherQuery.getUpdateTimeBegin()));
        }if (otherQuery.getUpdateTimeEnd()!=null){
            query.addCriteria(Criteria.where("updateTime").gt(otherQuery.getUpdateTimeEnd()));
        }
        if (StringUtils.isNotBlank(otherQuery.getOrder())){
            query.with(new Sort(new Sort.Order(("asc").equals(otherQuery.getSortBy())? Sort.Direction.ASC : Sort.Direction.DESC, otherQuery.getOrder())));
        }
    }

    @PostConstruct
    public void init(){
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        ArrayList<Class> allClassByInterface = ClassUtil.getAllClassByInterface(BaseEntity.class);
        for (Class indexClass:allClassByInterface){
            Field[] fields = indexClass.getDeclaredFields();
            for(Field field : fields){
                DBIndex dbIndex = field.getAnnotation(DBIndex.class);
                if(dbIndex!=null){
                    IndexOperations indexOps = mongoTemplate.indexOps(indexClass);
                    Index index = new Index();
                    index.on(field.getName(),dbIndex.direction());
                    if (dbIndex.unique()){
                        index.unique();
                    }
                    indexOps.ensureIndex(index);
                }
            }
        }
        scriptOps.register(new NamedMongoScript("getNextSequence","function(name){var ret=db.counters.findAndModify({query:{_id:name},update:{$inc:{seq:NumberLong(1)}},new:true});return ret.seq.floatApprox}"));
    }
}
