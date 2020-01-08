package cn.xp1997.xp.sys.shiro.mapper;

import cn.xp1997.xp.sys.shiro.entity.SysUserRole;
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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 解绑用户角色
     */
    void remove(@Param("userId") String userId, @Param("roleId") String roleId);

}
