package cn.xp1997.xp.sys.shiro.mapper;

import cn.xp1997.xp.sys.shiro.entity.SysRoleAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
@Repository
public interface SysRoleAuthMapper extends BaseMapper<SysRoleAuth> {

    /**
     * 解绑角色权限
     */
    void remove(@Param("roleId") String roleId, @Param("authId") String authId);

}
