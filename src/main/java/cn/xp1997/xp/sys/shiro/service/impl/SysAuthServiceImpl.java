package cn.xp1997.xp.sys.shiro.service.impl;

import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.mapper.SysAuthMapper;
import cn.xp1997.xp.sys.shiro.service.ISysAuthService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {

    private final SysAuthMapper sysAuthMapper;

    public SysAuthServiceImpl(SysAuthMapper sysAuthMapper) {
        this.sysAuthMapper = sysAuthMapper;
    }


    /**
     * 根据角色id查询权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    @Override
    public List<SysAuth> getAuthListByRole(String roleId) {
        return sysAuthMapper.getAuthListByRole(roleId);
    }

    /**
     * 分页查询auth
     */
    public IPage<SysAuth> getAuthPage(Page<SysAuth> page, String id) {
        return sysAuthMapper.getAuthPage(page, id);
    }

    @Override
    public List<SysAuth> getAuthList() {
        return sysAuthMapper.getAuthList();
    }

    @Override
    public List<SysAuth> getParent() {
        return sysAuthMapper.getParent();
    }
}
