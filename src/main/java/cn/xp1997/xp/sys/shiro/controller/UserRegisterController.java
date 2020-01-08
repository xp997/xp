package cn.xp1997.xp.sys.shiro.controller;

import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.service.impl.SysUserServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户注册控制器
 */
@RestController
@RequestMapping(value = "/register")
public class UserRegisterController {

    private final SysUserServiceImpl sysUserService;

    public UserRegisterController(SysUserServiceImpl sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 注册
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result register(SysUser user){
        try {
            //判断账号密码不为空
            if(!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getUsername())){
                //账号密码不为空  判断账号是否已经存在
                if(!sysUserService.isUsernameExist(user.getUsername())){
                    //账号不存在，可以注册
                    //写入数据库
                    boolean register = sysUserService.register(user);
                    if(register){
                        //注册成功
                        return Result.success();
                    }
                }else{
                    return Result.fail( "用户名已存在");
                }
            }else{
                return Result.fail("用户名密码不能为空");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("注册失败");
    }




}
