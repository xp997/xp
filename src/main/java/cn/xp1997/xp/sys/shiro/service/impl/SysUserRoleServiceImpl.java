package cn.xp1997.xp.sys.shiro.service.impl;

import cn.xp1997.xp.sys.shiro.entity.SysUserRole;
import cn.xp1997.xp.sys.shiro.mapper.SysUserRoleMapper;
import cn.xp1997.xp.sys.shiro.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    private final SysUserRoleMapper userRoleMapper;

    public SysUserRoleServiceImpl(SysUserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * 解绑 user-role
     */
    @Override
    public void unbindRole(String userId, String roleId) {
        userRoleMapper.remove(userId, roleId);

    }
}
