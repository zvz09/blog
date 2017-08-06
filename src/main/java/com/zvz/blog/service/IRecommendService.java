package com.zvz.blog.service;


import com.zvz.blog.common.PageConfig;
import com.zvz.blog.common.PageInfoResult;
import com.zvz.blog.model.Recommend;

/**
 * Created by Pan on 2016/12/20.
 */
public interface IRecommendService {

    long add(Recommend recommendVo);
    long edit(Recommend recommendVo);
    boolean remove(long id);
    PageInfoResult<Recommend> list(PageConfig pageConfig, Recommend recommend);
    boolean hits(Long id);

    Recommend get(long id);
}
