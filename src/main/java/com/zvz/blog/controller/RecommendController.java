package com.zvz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zvz.blog.common.CommonConstant;
import com.zvz.blog.common.CommonResult;
import com.zvz.blog.common.PageConfig;
import com.zvz.blog.common.PageInfoResult;
import com.zvz.blog.model.Recommend;
import com.zvz.blog.service.IRecommendService;
import com.zvz.blog.util.ExecuteSecurity;

/**
 * Created by Pan on 2016/12/20.
 */
@Controller
@RequestMapping(value = "")
public class RecommendController {
    @Autowired
    private IRecommendService iRecommendService;
    @ResponseBody
    @RequestMapping(value = "/recommend",method = RequestMethod.POST)
    public CommonResult list(PageConfig pageConfig){
        PageInfoResult<Recommend> list = iRecommendService.list(pageConfig,null);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,list);
    }

    @ResponseBody
    @RequestMapping(value = "/recommend/hits",method = RequestMethod.POST)
    public CommonResult hits(Long id){
        iRecommendService.hits(id);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null);
    }


    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/recommend",method = RequestMethod.POST)
    public CommonResult mlist(PageConfig pageConfig){
        PageInfoResult<Recommend> list = iRecommendService.list(pageConfig,null);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,list);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/recommend/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable long id){
        Recommend recommend = iRecommendService.get(id);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,recommend);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/recommend/save",method = RequestMethod.POST)
    public CommonResult add(@RequestBody Recommend recommend){
        long id  = iRecommendService.add(recommend);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,id);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/recommend/update",method = RequestMethod.PUT)
    public CommonResult edit(@RequestBody Recommend recommend){
        long id  = iRecommendService.edit(recommend);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,id);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/recommend/{id}",method = RequestMethod.DELETE)
    public CommonResult edit(@PathVariable long id){
        iRecommendService.remove(id);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null);
    }


















}
