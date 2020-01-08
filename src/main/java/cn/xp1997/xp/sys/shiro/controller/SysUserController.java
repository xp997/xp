package cn.xp1997.xp.sys.shiro.controller;


import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.entity.vo.SysUserVo;
import cn.xp1997.xp.sys.shiro.service.impl.SysUserServiceImpl;
import cn.xp1997.xp.sys.shiro.utils.ShiroUtil;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    private final SysUserServiceImpl userService;

    public SysUserController(SysUserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * 获取用户列表
     */
    @RequiresPermissions("/sys/user/getUsers")
    @PostMapping("/getUsers")
    public Result getUsers(Integer limit, Integer page, String roleName, String username){
        try {
            Page<SysUserVo> pageing = new Page<>(page, limit);
            IPage<SysUserVo> userPage = userService.getUsersPage(pageing, roleName, username);
            return Result.success(userPage.getRecords(), userPage.getTotal());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 更新用户信息
     */
    @RequiresPermissions("/sys/user/updateUser")
    @PostMapping("/updateUser")
    public Result updateUser(SysUser sysUser){
        try {
            if(sysUser == null){
                return Result.fail();
            }
            sysUser.setUsername(null);
            sysUser.setUpdateTime(new Date());
            if(userService.updateById(sysUser)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 删除
     */
    @RequiresPermissions("/sys/user/deleteUser")
    @PostMapping("/deleteUser")
    public Result deleteUsers(String id){
        try {
            if(userService.removeById(id)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("/sys/user/batchDeleteUsers")
    @PostMapping("/batchDeleteUsers")
    public Result batchDeleteUsers(String ids){
        try {
            List<String> idlist = JSON.parseArray(ids, String.class);
            if(userService.removeByIds(idlist)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 保存
     */
    @RequiresPermissions("/sys/user/saveUser")
    @PostMapping("/saveUser")
    public Result saveUsers(SysUser sysUser){
        try {
            SysUser userByUsername = userService.getUserByUsername(sysUser.getUsername());
            if(userByUsername != null){
                return Result.fail( "用户名已存在");
            }
            sysUser.setId(UUIDUtil.getUUID());
            sysUser.setCreateTime(new Date());
            sysUser.setPassword(ShiroUtil.saltMd5(sysUser.getPassword(), sysUser.getUsername()));
            if(userService.save(sysUser)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    /**
     * 获取当前登录用户信息
     */
    @RequiresPermissions("/sys/user/getUser")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public Result getUser(){
        try {
            SysUser user = ShiroUtil.getUser();
            return Result.success(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 修改密码
     */
    @RequiresPermissions(("/sys/user/updatePassword"))
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Result updatePassword(String id, String oldPassword, String newPassword, String username){

        try {
            //检查旧密码是否正确
            if(userService.checkPassword(id, oldPassword, username)){
                //旧密码正确,修改密码
                userService.updatePassword(id, newPassword, username);
                //修改密码成功，退出登录
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
                return Result.success("修改密码成功");
            }else {
                //密码错误
                return Result.fail( "旧密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("修改密码失败");
    }


    /**
     * 修改密码(后台直接修改)
     * 测试时使用，可删除该接口
     */
    @RequiresPermissions(("/sys/user/changePassword"))
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Result changePassword(String id, String newPassword, String username){

        try {
                userService.updatePassword(id, newPassword, username);
                return Result.success("修改密码成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("修改密码失败");
    }

    @RequiresPermissions("/sys/user/getUserById")
    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public Result getUserById(String id){

        try {
            SysUser sysUser = userService.getById(id);
            return Result.success(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();

    }
}
