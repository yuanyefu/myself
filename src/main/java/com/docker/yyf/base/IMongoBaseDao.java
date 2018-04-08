package com.docker.yyf.base;

import com.docker.yyf.entity.BaseEntity;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author 18329
 */
public interface IMongoBaseDao<T extends BaseEntity> {

    List select(T record);

    T selectOne(T record);

    List<T> selectAll();

    List<T> select(T record,OtherQuery otherQuery);

    BasePage<T> selectPage(T record, OtherQuery otherQuery, int pageSize, int pageNum);

    T findAndModify(Query query, Update update);

    T findAndRemove(Query query);

    void updateFirst(T record);

    void save(T record);

    T selectById(String id);

    long count(T record);
}
