package cn.xp1997.xp.sys.shiro.service;


import cn.xp1997.xp.sys.shiro.entity.SysAuth;
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
public interface ISysAuthService extends IService<SysAuth> {
    /**
     * 根据角色id查询权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
     List<SysAuth> getAuthListByRole(String roleId);

    /**
     * 分页查询Auth
     */
     IPage<SysAuth> getAuthPage(Page<SysAuth> page, String id);

    List<SysAuth> getAuthList();

    List<SysAuth> getParent();

}
