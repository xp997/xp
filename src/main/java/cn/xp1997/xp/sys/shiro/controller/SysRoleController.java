package cn.xp1997.xp.sys.shiro.controller;


import cn.xp1997.xp.sys.base.Node;
import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysRole;
import cn.xp1997.xp.sys.shiro.service.impl.SysRoleServiceImpl;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/sys/role")
public class SysRoleController {

    private final SysRoleServiceImpl roleService;


    public SysRoleController(SysRoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    /**
     * 根据username获取角色列表
     */
    @RequiresPermissions("/sys/role/getRole")
    @PostMapping("/getRole")
    public Result getRole(@RequestParam(value = "userId", required = false) String userId,
                          @RequestParam(value = "limit", required = false, defaultValue = "15") Integer limit,
                          @RequestParam(value = "page", required = false, defaultValue = "1") Integer page){
        try {
            Page<SysRole> paging = new Page<>(page, limit);
            IPage<SysRole> rolePage = roleService.getRolePage(paging, userId);
            return Result.success(rolePage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    @RequiresPermissions("/sys/role/getRoleList")
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public Result getRoleList(@RequestParam(value = "limit", required = false, defaultValue = "15") Integer limit,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page){
        try {
            Page<SysRole> paging = new Page<>(page, limit);
            IPage<SysRole> pageResult = roleService.page(paging);
            List<SysRole> records = pageResult.getRecords();
            long total = pageResult.getTotal();
            return Result.success(records, total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 修改角色信息
     */
    @RequiresPermissions("/sys/role/updateRole")
    @PostMapping("/updateRole")
    public Result updateRole(SysRole sysRole){
        try {
            if(roleService.updateById(sysRole)){

                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 删除角色信息
     */
    @RequiresPermissions("/sys/role/deleteRole")
    @PostMapping("/deleteRole")
    public Result deleteRole(SysRole sysRole){
        try {
            if(roleService.removeById(sysRole.getId())){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 删除角色信息
     */
    @RequiresPermissions("/sys/role/batchDeleteRole")
    @PostMapping("/batchDeleteRole")
    public Result batchDeleteRole(String ids){
        try {
            List<String> sysRolesList = JSON.parseArray(ids, String.class);
            if(roleService.removeByIds(sysRolesList)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    /**
     * 保存角色信息
     */
    @RequiresPermissions("/sys/role/saveRole")
    @PostMapping("/saveRole")
    public Result saveRole(SysRole sysRole){
        try {
            sysRole.setId(UUIDUtil.getUUID());
            if(roleService.save(sysRole)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    /**
     * 获取角色树
     */
    @RequiresPermissions("/sys/role/getRoleTree")
    @RequestMapping(value = "/getRoleTree", method = RequestMethod.POST)
    public Result getRoleTree(String userId){
        try {

            List<SysRole> roleList = roleService.list();
            List<Node> layuiTrees = new ArrayList<>();
            List<SysRole> roleListByUser = roleService.getRoleListByUser(userId);
            for (SysRole sysRole : roleList) {
                Node layuiTree = new Node();
                for (SysRole roleByUser : roleListByUser) {
                    if(sysRole.getId().equals(roleByUser.getId())){
                        layuiTree.setChecked(true);
                        break;
                    }
                }

                layuiTree.setId(sysRole.getId());
                layuiTree.setTitle(sysRole.getRoleName());
                layuiTrees.add(layuiTree);
            }
            return Result.success(layuiTrees);

        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

}
