package cn.xp1997.xp.sys.shiro.service;

import cn.xp1997.xp.sys.shiro.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 解绑user-role
     */
    void unbindRole(String userId, String roleId);
}
