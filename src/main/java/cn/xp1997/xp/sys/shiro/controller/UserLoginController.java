package cn.xp1997.xp.sys.shiro.controller;

import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录控制器
 */
@RestController
@RequestMapping("/login")
public class UserLoginController {


    /**
     * 登录
     */
    @RequestMapping(method = RequestMethod.POST)
     public Result login(String username, String password, boolean rememberMe) {
                 Subject subject = SecurityUtils.getSubject();
                 UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
                 usernamePasswordToken.setRememberMe(rememberMe);
                 String msg ;
                 if(!subject.isAuthenticated()){
                     try {
                         subject.login(usernamePasswordToken);
                         //身份认证成功
                         return Result.success();
                     } catch (UnknownAccountException e) {
                         e.printStackTrace();
                         msg = "账号不存在！";
                     } catch (LockedAccountException e) {
                         e.printStackTrace();
                         msg = "账号被锁定！";
                     } catch (DisabledAccountException e) {
                         e.printStackTrace();
                         msg = "账号被禁用！";
                     } catch (IncorrectCredentialsException e) {
                         e.printStackTrace();
                         msg = "凭证/密码错误！";
                     } catch (ExpiredCredentialsException e) {
                         e.printStackTrace();
                         msg = "凭证/密码过期！";
                     } catch (ExcessiveAttemptsException e) {
                         e.printStackTrace();
                         msg = "登录失败次数过多！";
                     }
                  }else{
                     return Result.fail( "已经登录");
                 }
            return Result.fail(msg);
    }


    @RequestMapping(value = "/unAuthorized", method = RequestMethod.GET)
    public Result unAuthorized(){
        return Result.fail("unAuthorized");
     }

    @RequestMapping(value = "/unLogin", method = RequestMethod.GET)
    public Result unLogin(){
        return Result.fail("unLogin");
    }


    /**
     * 获取当前登录用户信息
     */
    @RequiresPermissions("/login/userInfo")
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public Result getUserInfo(){
        try {
            SysUser user = ShiroUtil.getUser();
            return Result.success(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }
}
