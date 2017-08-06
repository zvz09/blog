package com.zvz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zvz.blog.common.CommonConstant;
import com.zvz.blog.common.CommonResult;
import com.zvz.blog.common.PageConfig;
import com.zvz.blog.common.PageInfoResult;
import com.zvz.blog.model.Friendlink;
import com.zvz.blog.service.IFriendlinkService;
import com.zvz.blog.util.ExecuteSecurity;

import java.util.List;

/**
 * Created by Pan on 2016/12/1.
 */
@RequestMapping(value = "")
@Controller
public class FriendlinkController {

    @Autowired
    private IFriendlinkService iFriendlinkService;

    @ResponseBody
    @RequestMapping(value = "/friendlink", method = RequestMethod.GET)
    public CommonResult list() {
        List<Friendlink> list = iFriendlinkService.list();
        return new CommonResult(CommonConstant.SUCCESS_CODE, null, list);
    }

    @ResponseBody
    @RequestMapping(value = "/friendlink/hits", method = RequestMethod.POST)
    public CommonResult hits(Integer id) {
        iFriendlinkService.hits(id);
        return new CommonResult(CommonConstant.SUCCESS_CODE, null);
    }




    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/friendlink", method = RequestMethod.POST)
    public CommonResult pagingList(PageConfig pageConfig) {
        PageInfoResult<Friendlink> list = iFriendlinkService.pagingList(pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE, null, list);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/friendlink/{fId}", method = RequestMethod.GET)
    public CommonResult get(@PathVariable("fId")int fId) {
        String message= null;
        boolean isSuccess=true;
        Friendlink friendlink = null;
        try {
            friendlink  = iFriendlinkService.get(fId);
        }catch (Exception e){
            isSuccess = false;
            message = e.getMessage();
        }finally {
            if(isSuccess){
                return new CommonResult(CommonConstant.SUCCESS_CODE,message,friendlink);
            }else{
                return new CommonResult(CommonConstant.FAIL_CODE,message);
            }
        }
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/friendlink/{fId}", method = RequestMethod.DELETE)
    public CommonResult delete(@PathVariable("fId")int fId) {
        String message= null;
        boolean isSuccess=true;
        try {
            isSuccess  = iFriendlinkService.delete(fId);
        }catch (Exception e){
            isSuccess = false;
            message = e.getMessage();
        }finally {
            if(isSuccess){
                return new CommonResult(CommonConstant.SUCCESS_CODE,message);
            }else{
                return new CommonResult(CommonConstant.FAIL_CODE,message);
            }

        }
    }

    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/friendlink/save", method = RequestMethod.POST)
    public CommonResult add(@RequestBody Friendlink friendlink) {
        String message= null;
        boolean isSuccess=true;
        try {
            isSuccess  = iFriendlinkService.add(friendlink);
        }catch (Exception e){
            isSuccess = false;
            message = e.getMessage();
        }finally {
            if(isSuccess){
                return new CommonResult(CommonConstant.SUCCESS_CODE,message);
            }else{
                return new CommonResult(CommonConstant.FAIL_CODE,message);
            }

        }
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/friendlink/update", method = RequestMethod.PUT)
    public CommonResult update(@RequestBody Friendlink friendlink) {
        String message= null;
        boolean isSuccess=true;
        try {
            isSuccess  = iFriendlinkService.update(friendlink);
        }catch (Exception e){
            isSuccess = false;
            message = e.getMessage();
        }finally {
            if(isSuccess){
                return new CommonResult(CommonConstant.SUCCESS_CODE,message);
            }else{
                return new CommonResult(CommonConstant.FAIL_CODE,message);
            }

        }
    }
}
