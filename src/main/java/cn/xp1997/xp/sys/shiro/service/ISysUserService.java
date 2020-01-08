package cn.xp1997.xp.sys.shiro.service;

import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.entity.vo.SysUserVo;
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
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return boolean
     */
    SysUser getUserByUsername(String username);

    boolean register(SysUser sysUser);

    boolean isUsernameExist(String username);

    /**
     * 根据username获取权限列表
     * @param username 用户名
     * @return 权限列表
     */
    List<SysAuth> getAuthListByUsername(String username);

    /**
     * 分页查询user列表
     */
    IPage<SysUserVo> getUsersPage(Page<SysUserVo> page, String roleName, String username);

    /**
     * 检查密码
     */
    boolean checkPassword(String id, String oldPassword, String username);

    void updatePassword(String id, String newPassword, String username);
}
