package cn.xp1997.xp.sys.shiro.service.impl;

import cn.xp1997.xp.sys.shiro.entity.SysRole;
import cn.xp1997.xp.sys.shiro.mapper.SysRoleMapper;
import cn.xp1997.xp.sys.shiro.service.ISysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    /**
     * 根据用户id查询角色列表
     * @param userId 用户id
     */
    @Override
    public List<SysRole> getRoleListByUser(String userId) {
        return sysRoleMapper.getRoleListByUser(userId);
    }

    /**
     * 分页查询role
     */
    @Override
    public IPage<SysRole> getRolePage(Page<SysRole> page, String id) {

        return sysRoleMapper.getRolePage(page, id);
    }
}
