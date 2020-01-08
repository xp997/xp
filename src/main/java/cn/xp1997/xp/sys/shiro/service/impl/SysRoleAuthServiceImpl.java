package cn.xp1997.xp.sys.shiro.service.impl;

import cn.xp1997.xp.sys.shiro.entity.SysRoleAuth;
import cn.xp1997.xp.sys.shiro.mapper.SysRoleAuthMapper;
import cn.xp1997.xp.sys.shiro.service.ISysRoleAuthService;
import cn.xp1997.xp.sys.shiro.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>sysUser
 *  服务实现类
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
@Service
@Transactional
public class SysRoleAuthServiceImpl extends ServiceImpl<SysRoleAuthMapper, SysRoleAuth> implements ISysRoleAuthService {

    private final SysRoleAuthMapper roleAuthMapper;


    public SysRoleAuthServiceImpl(SysRoleAuthMapper roleAuthMapper) {
        this.roleAuthMapper = roleAuthMapper;
    }

    /**
     * 解绑 role-auth
     */
    public void unBindAuth(String roleId, String authId) {
        roleAuthMapper.remove(roleId, authId);
    }

    @Override
    public void bind(String roleId, List<SysRoleAuth> roleAuthsParm){
        QueryWrapper<SysRoleAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<SysRoleAuth> roleAuthsQuery = roleAuthMapper.selectList(queryWrapper);

        Iterator<SysRoleAuth> roleAuthParmIterator = roleAuthsParm.iterator();
        Iterator<SysRoleAuth> roleAuthQueryIterator = roleAuthsQuery.iterator();

        while (roleAuthParmIterator.hasNext()){
            SysRoleAuth parm = roleAuthParmIterator.next();
            while (roleAuthQueryIterator.hasNext()){
                SysRoleAuth query = roleAuthQueryIterator.next();
                if(parm.getAuthId().equals(query.getAuthId())){
                    roleAuthParmIterator.remove();
                    roleAuthQueryIterator.remove();
                    break;
                }
            }
        }


        //roleAuthsQuery：删除
        //roleAuthsParm：添加
        if(roleAuthsParm.size() != 0){
            for (SysRoleAuth roleAuth : roleAuthsParm) {
                roleAuth.setId(UUIDUtil.getUUID());
            }
            super.saveBatch(roleAuthsParm);
        }

        if(roleAuthsQuery.size() != 0){
            List<String> ids = new ArrayList<>();
            for (SysRoleAuth roleAuth : roleAuthsQuery) {
                ids.add(roleAuth.getId());
            }
            super.removeByIds(ids);
        }


    }
}
