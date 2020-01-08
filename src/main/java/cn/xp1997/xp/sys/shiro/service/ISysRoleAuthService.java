package cn.xp1997.xp.sys.shiro.service;

import cn.xp1997.xp.sys.shiro.entity.SysRoleAuth;
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
public interface ISysRoleAuthService extends IService<SysRoleAuth> {
    /**
     * 解绑角色权限
     */
    void unBindAuth(String roleId, String authId);

    void bind(String roleId, List<SysRoleAuth> roleAuths) throws Exception;
}
