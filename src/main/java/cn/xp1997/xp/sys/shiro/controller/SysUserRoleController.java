package cn.xp1997.xp.sys.shiro.controller;


import cn.xp1997.xp.sys.base.Node;
import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysUserRole;
import cn.xp1997.xp.sys.shiro.service.impl.SysUserRoleServiceImpl;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
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
@RequestMapping("/sys/user-role")
public class SysUserRoleController {

    private final SysUserRoleServiceImpl userRoleService;

    public SysUserRoleController(SysUserRoleServiceImpl userRoleService) {
        this.userRoleService = userRoleService;
    }


    /**
     * 用户绑定角色
     */
    @RequiresPermissions("/sys/user-role/bindRole")
    @RequestMapping(value = "/bindRole", method = RequestMethod.POST)
    public Result bindRole(String userId, String roles){
        try {
            List<Node> layuiTrees = JSON.parseArray(roles, Node.class);

            //根据用户id查询绑定集合
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            List<SysUserRole> userRoleListQuery = userRoleService.list(queryWrapper);

            //将layui转换成实体对象
            List<SysUserRole> userRoleListParm = unpack(layuiTrees, userId);

            //循环遍历，删除两个集合中相同的元素
            Iterator<SysUserRole> iteratorParm = userRoleListParm.iterator();
            Iterator<SysUserRole> iteratorQuery = userRoleListQuery.iterator();
            while (iteratorParm.hasNext()){
                SysUserRole userRoleParm = iteratorParm.next();
                while (iteratorQuery.hasNext()){
                    SysUserRole userRoleQuery = iteratorQuery.next();
                    if(userRoleParm.getRoleId().equals(userRoleQuery.getRoleId())){
                        iteratorParm.remove();
                        iteratorQuery.remove();
                        break;
                    }
                }
            }

            //新增
            if(userRoleListParm.size() != 0){
                for (SysUserRole sysUserRole : userRoleListParm) {
                    sysUserRole.setUserId(userId);
                }
                userRoleService.saveBatch(userRoleListParm);
            }

            //删除
            if(userRoleListQuery.size() != 0){
                List<String> ids = new ArrayList<>();
                for (SysUserRole sysUserRole : userRoleListQuery) {
                    ids.add(sysUserRole.getId());
                }
                userRoleService.removeByIds(ids);
            }

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("绑定失败");
    }

    /**
     * 解绑
     */
    @RequiresPermissions("/sys/user-role/unBindRole")
    @RequestMapping(value = "/unBindRole", method = RequestMethod.POST)
    public Result unBindRole(String userId, String roleId){
        try {
            userRoleService.unbindRole(userId, roleId);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("解绑失败");
    }

    private List<SysUserRole> unpack(List<Node> layuiTrees, String userId){
        List<SysUserRole> userRoles = new ArrayList<>();
        for (Node layuiTree : layuiTrees) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId((String) layuiTree.getId());
            sysUserRole.setId(UUIDUtil.getUUID());
            userRoles.add(sysUserRole);
        }
        return userRoles;
    }
}
