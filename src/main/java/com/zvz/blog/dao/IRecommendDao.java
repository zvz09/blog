package com.zvz.blog.dao;

import com.zvz.blog.common.PageConfig;
import com.zvz.blog.model.Recommend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pan on 2016/12/20.
 */
@Repository("iRecommendDao")
public interface IRecommendDao {

    Integer insert(Recommend recommend);

    Integer delete(@Param("id") Long recommendId);

    Integer update(Recommend recommend);

    Integer updateHits(Recommend recommend);

    List<Recommend> selectBy(@Param("recommend") Recommend recommend,
                             @Param("pageConfig") PageConfig pageConfig);

    Integer selectCountBy(@Param("recommend") Recommend recommend);

    Recommend selectById(@Param("id") Long id);
}
