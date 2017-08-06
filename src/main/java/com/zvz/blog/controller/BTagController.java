package com.zvz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zvz.blog.common.CommonConstant;
import com.zvz.blog.common.CommonResult;
import com.zvz.blog.model.Article;
import com.zvz.blog.model.Tag;
import com.zvz.blog.service.IBArticleService;
import com.zvz.blog.service.IBTagService;
import com.zvz.blog.vo.ArticleVo;
import com.zvz.blog.vo.TagVo;

import java.util.List;

/**
 * Created by Pan on 2016/12/4.
 */
@RequestMapping(value = "")
@Controller
public class BTagController {
    @Autowired
    private IBTagService iTagService;
    @Autowired
    private IBArticleService iArticleService;

    @ResponseBody
    @RequestMapping(value = "/blog/tag",method = RequestMethod.GET)
    public CommonResult listTags(){
        List<TagVo> listTags = iTagService.getAllVoBy(CommonConstant.ACTICLE_STATUS_BLOG);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",listTags);
    }
    @ResponseBody
    @RequestMapping(value = "/blog/tag/{name:.+}",method = RequestMethod.GET)
    public CommonResult listTagArticle(@PathVariable("name")String name){
        List<ArticleVo> listArticles = iArticleService.getAllByTagName(name);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",listArticles);
    }




    /*管理url开始*/
    @ResponseBody
    @RequestMapping(value = "/manage/tag", method = RequestMethod.GET)
    public CommonResult listMTags() {
        List<TagVo> listTags = iTagService.getAllVoBy(null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listTags);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/tag", method = RequestMethod.PUT)
    public CommonResult updateTag(@RequestBody Tag tag) {
        Boolean result = iTagService.edit(tag);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/tag", method = RequestMethod.POST)
    public CommonResult saveTag(@RequestBody Tag tag) {
        Boolean result = iTagService.add(tag);
        ;
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/tag/{tagId}", method = RequestMethod.GET)
    public CommonResult listTagArticles(@PathVariable("tagId") Integer tagId) {
        List<Article> listTagArticles = iArticleService.getAllByTagId(tagId, null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listTagArticles);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/tag/{tagId}", method = RequestMethod.DELETE)
    public CommonResult deleteTag(@PathVariable("tagId") Integer tagId) {
        Boolean result = iTagService.deleteById(tagId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/tag/{tagId}/{articleId}", method = RequestMethod.DELETE)
    public CommonResult deleteTag(@PathVariable Integer tagId, @PathVariable Integer articleId) {
        Boolean result = iArticleService.deleteTag(tagId, articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }

}
