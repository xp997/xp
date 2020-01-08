package cn.xp1997.xp.sys.shiro.mapper;


import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.entity.vo.SysUserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户获取用户信息
     * @param username 用户名
     */
    SysUser getUserByUsername(String username);

    /**
     * 根据username获取权限列表
     * @param username username
     * @return 权限列表
     */
    List<SysAuth> getAuthListByUsername(String username);

    /**
     *分页查询
     */
    IPage<SysUserVo> getUsersPage(Page<SysUserVo> page, String roleName, String username);

    SysUser checkPassword(String id, String password);
}
