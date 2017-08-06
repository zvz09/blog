package com.zvz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zvz.blog.common.CommonConstant;
import com.zvz.blog.common.CommonResult;
import com.zvz.blog.model.Guest;
import com.zvz.blog.service.IGuestService;
import com.zvz.blog.service.IMessageService;
import com.zvz.blog.util.ReturnUtil;
import com.zvz.blog.vo.ArticleMessageFormVo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/11/16.
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private IGuestService guestService;
    @Autowired
    private IMessageService messageService;


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult save(@Valid @RequestBody ArticleMessageFormVo messageFormVo,
                             BindingResult resultValidator) {
        if (resultValidator.hasErrors()) {
            List<String> resultMessage = new ArrayList<>();
            for (FieldError fe : resultValidator.getFieldErrors()) {
                resultMessage.add(fe.getDefaultMessage());
            }
            return new CommonResult(CommonConstant.FAIL_CODE, resultMessage.toString());
        } else {
            boolean resultData = messageService.guestMessage(messageFormVo.getGuest(), messageFormVo.getMessage());
            return new CommonResult(CommonConstant.SUCCESS_CODE, ReturnUtil.returnResult(resultData), resultData);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/guest/email", method = RequestMethod.POST)
    public CommonResult getGuestByEmail(String email) {
        if(email==null || email.trim().equals("")){
            return new CommonResult(CommonConstant.FAIL_CODE, "邮箱不能为空");
        }
        Map<String, Object> condition = new HashMap<>();
        condition.put("email", email);
        Guest guest = guestService.queryOneByCondition(condition);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,guest);

    }

    @ResponseBody
    @RequestMapping(value = "/guest/nickname", method = RequestMethod.POST)
    public CommonResult getGuestByNickname(String email,String nickname) {
        if(email==null || email.trim().equals("")){
            return new CommonResult(CommonConstant.FAIL_CODE, "邮箱不能为空");
        }
        if(nickname==null || nickname.trim().equals("")){
            return new CommonResult(CommonConstant.FAIL_CODE, "昵称不能为空");
        }
        Map<String, Object> condition = new HashMap<>();
        condition.put("nickname", nickname);
        Guest guest = guestService.queryOneByCondition(condition);
        if (guest != null) {
            if(guest.getEmail().equals(email)){
                return new CommonResult(CommonConstant.SUCCESS_CODE,null,false);
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE,null,true);
        }else{
            return new CommonResult(CommonConstant.SUCCESS_CODE,null,false);
        }
    }
}
