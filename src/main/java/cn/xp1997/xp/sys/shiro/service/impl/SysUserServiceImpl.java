package cn.xp1997.xp.sys.shiro.service.impl;

import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.entity.vo.SysUserVo;
import cn.xp1997.xp.sys.shiro.mapper.SysUserMapper;
import cn.xp1997.xp.sys.shiro.service.ISysUserService;
import cn.xp1997.xp.sys.shiro.utils.ShiroUtil;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }


    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return user信息
     */
    @Override
    public SysUser getUserByUsername(String username) {
        return sysUserMapper.getUserByUsername(username);
    }

    /**
     * 用户注册
     */
    @Override
    public boolean register(SysUser sysUser){
        //设置id
        sysUser.setId(UUIDUtil.getUUID());
        //设置盐值
        sysUser.setSalt(UUIDUtil.getUUID());
        //密码加密
        String md5Password = ShiroUtil.saltMd5(sysUser.getPassword(), sysUser.getSalt());
        sysUser.setPassword(md5Password);
        return save(sysUser);
    }

    /**
     * 用户名是否存在
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean isUsernameExist(String username) {
        SysUser userByUsername = getUserByUsername(username);
        return !ObjectUtils.isEmpty(userByUsername);
    }

    /**
     * 根据用户名获取权限列表
     * @param username 用户名
     * @return 权限列表
     */
    @Override
    public List<SysAuth> getAuthListByUsername(String username) {
        return sysUserMapper.getAuthListByUsername(username);
    }

    /**
     * 分页查询user列表
     */
    public IPage<SysUserVo> getUsersPage(Page<SysUserVo> page, String roleName, String username) {
        return sysUserMapper.getUsersPage(page, roleName, username);
    }

    /**
     * 检查密码
     */
    @Override
    public boolean checkPassword(String id, String oldPassword, String username) {
        String md5Password = ShiroUtil.saltMd5(oldPassword, username);
        SysUser sysUser = sysUserMapper.checkPassword(id, md5Password);
        return sysUser != null;
    }

    @Override
    public void updatePassword(String id, String newPassword, String username) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(ShiroUtil.saltMd5(newPassword, username));
        sysUserMapper.updateById(sysUser);
    }
}
