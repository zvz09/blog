package com.zvz.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zvz.blog.common.CommonConstant;
import com.zvz.blog.common.PageConfig;
import com.zvz.blog.common.PageInfoResult;
import com.zvz.blog.dao.IProjectDao;
import com.zvz.blog.model.Project;
import com.zvz.blog.service.IProjectService;
import com.zvz.blog.util.ReturnUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Pan on 2016/12/27.
 */
@Transactional
@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private IProjectDao iProjectDao;

    @Override
    public long add(Project project) {
        project.setHits(0);
        project.setPubTime(new Date());
        Integer result = iProjectDao.insert(project);
        if (ReturnUtil.returnResult(result)){
            return project.getId();
        }
        return 0;
    }

    @Override
    public long edit(Project project) {
        Integer result = iProjectDao.update(project);
        if(ReturnUtil.returnResult(result)){
            return project.getId();
        }
        return 0;
    }

    @Override
    public boolean remove(long id) {
        Integer result = iProjectDao.delete(id);
        return ReturnUtil.returnResult(result);
    }

    @Override
    public List<Project> list(boolean status) {
        return iProjectDao.selectBy(new Project(status),null);
    }

    @Override
    public PageInfoResult<Project> list(PageConfig pageConfig, Project project) {
        List<Project> list = iProjectDao.selectBy(project,pageConfig);
        int counts = iProjectDao.selectCountBy(project);
        PageInfoResult<Project> pageInfoResult = new PageInfoResult<>(list,pageConfig,counts);
        return pageInfoResult;
    }

    @Override
    public boolean hits(Long id) {
        Project project = iProjectDao.selectById(id, CommonConstant.PROJECT_ALL);
        project.setHits(project.getHits()+1);
        iProjectDao.updateHits(project);
        return false;
    }

    @Override
    public Project get(long id,boolean status) {
        return iProjectDao.selectById(id,status);
    }
}
