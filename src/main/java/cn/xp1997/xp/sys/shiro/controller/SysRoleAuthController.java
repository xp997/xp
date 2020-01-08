package cn.xp1997.xp.sys.shiro.controller;


import cn.xp1997.xp.sys.base.Node;
import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.entity.SysRoleAuth;
import cn.xp1997.xp.sys.shiro.service.impl.SysRoleAuthServiceImpl;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/sys/role-auth")
public class SysRoleAuthController {

    private final SysRoleAuthServiceImpl roleAuthService;

    public SysRoleAuthController(SysRoleAuthServiceImpl roleAuthService) {
        this.roleAuthService = roleAuthService;
    }


    /**
     * 角色绑定权限
     */
    @RequiresPermissions("/sys/role-auth/bindAuth")
    @RequestMapping(value = "/bindAuth", method = RequestMethod.POST)
    public Result bindAuth(String roleId, String authId){
        try {
            SysRoleAuth sysRoleAuth = new SysRoleAuth();
            sysRoleAuth.setRoleId(roleId);
            sysRoleAuth.setAuthId(authId);
            sysRoleAuth.setId(UUIDUtil.getUUID());
            roleAuthService.save(sysRoleAuth);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 角色绑定权限(批量)
     */
    @RequiresPermissions("/sys/role-auth/batchBindAuth")
    @RequestMapping(value = "/batchBindAuth", method = RequestMethod.POST)
    public Result batchBindAuth(String roleId, String auths){
        try {
            List<Node> layuiTreeList = JSON.parseArray(auths, Node.class);
            List<SysAuth> authList = new ArrayList<>();
            unpack(authList, layuiTreeList);
            List<SysRoleAuth> roleAuths = new ArrayList<>();
            for (SysAuth sysAuth : authList) {
                SysRoleAuth roleAuth = new SysRoleAuth();
                roleAuth.setRoleId(roleId);
                roleAuth.setAuthId(sysAuth.getId());
                roleAuths.add(roleAuth);
            }
            roleAuthService.bind(roleId,roleAuths);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 角色解绑权限
     */
    @RequiresPermissions("/sys/role-auth/unBindAuth")
    @RequestMapping(value = "/unBindAuth", method = RequestMethod.POST)
    public Result unBindAuth(String roleId, String authId){
        try {
            roleAuthService.unBindAuth(roleId, authId);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    private void unpack(List<SysAuth> sysAuths, List<Node> layuiTrees){

        for (Node layuiTree : layuiTrees) {
            SysAuth sysAuth = new SysAuth();
            sysAuth.setId((String) layuiTree.getId());
            sysAuth.setAuthName(layuiTree.getTitle());
            List<Node> children = layuiTree.getChildren();
            if(children != null && layuiTree.getChildren().size() != 0){
                unpack(sysAuths, children);
            }
            sysAuths.add(sysAuth);
        }
    }

}
