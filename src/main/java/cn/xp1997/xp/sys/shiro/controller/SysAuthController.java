package cn.xp1997.xp.sys.shiro.controller;


import cn.xp1997.xp.sys.base.Node;
import cn.xp1997.xp.sys.base.Result;
import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.service.impl.SysAuthServiceImpl;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
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
@RequestMapping("/sys/auth")
public class SysAuthController {

    private final SysAuthServiceImpl authService;

    public SysAuthController(SysAuthServiceImpl authService) {
        this.authService = authService;
    }


    /**
     * 根据角色id查询权限
     */
    @RequiresPermissions("/sys/auth/getAuth")
    @PostMapping("/getAuth")
    public Result getAuth(@RequestParam(value = "roleId", required = false) String roleId,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageCurrent", required = false, defaultValue = "1") Integer pageCurrent){
        try {
            Page<SysAuth> page = new Page<>(pageCurrent, pageSize);
            IPage<SysAuth> authPage = authService.getAuthPage(page, roleId);
            return Result.success(authPage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();

    }



    /**
     * 修改权限信息
     */
    @RequiresPermissions("/sys/auth/updateAuth")
    @PostMapping("/updateAuth")
    public Result getAuth(SysAuth sysAuth){


        try {
            if(authService.updateById(sysAuth)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.fail();
    }

    /**
     * 删除权限信息
     */
    @RequiresPermissions("/sys/auth/deleteAuth")
    @PostMapping("/deleteAuth")
    public Result deleteAuth(SysAuth sysAuth){
        try {
            if(authService.removeById(sysAuth.getId())){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 保存权限信息
     */
    @RequiresPermissions("/sys/auth/saveAuth")
    @PostMapping("/saveAuth")
    public Result saveAuth(SysAuth sysAuth){
        try {
            if(sysAuth == null){
                return Result.fail();
            }
            if(sysAuth.getAuthUrl() == null || sysAuth.getAuthUrl().equals("")){
                sysAuth.setAuthUrl("null");
            }
            sysAuth.setId(UUIDUtil.getUUID());
            if(authService.save(sysAuth)){
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 查询权限列表
     */
    @RequiresPermissions("/sys/auth/getAuthList")
    @RequestMapping(value = "getAuthList", method = RequestMethod.GET)
    public Result getAuthList(){
        try {
            List<SysAuth> authList = authService.getAuthList();
            return Result.success(authList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    /**
     * 获取父级
     */
    @RequiresPermissions("/sys/auth/getParent")
    @RequestMapping(value = "/getParent", method = RequestMethod.POST)
    public Result getParent(){
        try {
            List<SysAuth> authList = authService.getParent();
            return Result.success(authList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 根据id查询权限
     */
    @RequiresPermissions("/sys/auth/getAuthById")
    @RequestMapping(value = "/getAuthById", method = RequestMethod.POST)
    public Result getById(String id){
        try {
            SysAuth sysAuth = authService.getById(id);
            return Result.success(sysAuth);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    /**
     * 查询权限树
     */
    @RequiresPermissions("/sys/auth/getAuthTree")
    @RequestMapping(value = "/getAuthTree", method = RequestMethod.POST)
    public Result getRoleTree(String roleId){
        try {

            List<SysAuth> authListByRole = authService.getAuthListByRole(roleId);
            //查询
            List<SysAuth> authList = authService.getAuthList();
            List<Node> layuiTrees = packTree(authList, authListByRole, "0");
            return Result.success(layuiTrees);

        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    /**
     * 查询权限树
     */
    @RequiresPermissions("/sys/auth/getAuthTree")
    @RequestMapping(value = "/getAuthTree", method = RequestMethod.GET)
    public Result getRoleTree(){
        try {
            List<SysAuth> authList = authService.getAuthList();
            List<Node> node = Node.getNode(authList, "0", "authName", "id", "parentId");
            return Result.success(node);

        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 封装layuiTree
     */
    private List<Node> packTree(List<SysAuth> sysAuths, List<SysAuth> sysAuthByRole, String parentId){
        List<Node> treeList = new ArrayList<>();
        for (SysAuth sysAuth : sysAuths) {
            if(sysAuth.getParentId().equals(parentId)){
                Node layuiTree = new Node();
                for (SysAuth authRole : sysAuthByRole) {
                    if(authRole.getId().equals(sysAuth.getId())){
                        if (sysAuth.getIsMenu() == 1){
                            layuiTree.setChecked(true);
                        }

                        break;
                    }
                }
                layuiTree.setId(sysAuth.getId());
                layuiTree.setTitle(sysAuth.getAuthName());
                List<Node> layuiTrees = packTree(sysAuths, sysAuthByRole, sysAuth.getId());
                layuiTree.setChildren(layuiTrees);
                treeList.add(layuiTree);
            }
        }
        return treeList;
    }
}
