package cn.xp1997.xp.sys.shiro.service;

import cn.xp1997.xp.sys.shiro.entity.SysRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据用户id查询角色集合
     * @param userId 用户id
     * @return 角色集合
     */
     List<SysRole> getRoleListByUser(String userId);

    /**
     *分页查询role
     */
    IPage<SysRole> getRolePage(Page<SysRole> page, String id);
}
