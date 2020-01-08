package cn.xp1997.xp.sys.shiro.mapper;


import cn.xp1997.xp.sys.shiro.entity.SysAuth;
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
public interface SysAuthMapper extends BaseMapper<SysAuth> {

    /**
     * 根据角色id查询权限集合
     * @param roleId 角色id
     * @return 权限集合
     */
    List<SysAuth> getAuthListByRole(String roleId);

    /**
     * 分页查询
     */
    IPage<SysAuth> getAuthPage(Page<SysAuth> page, String id);

    List<SysAuth> getAuthList();

    List<SysAuth> getParent();

}
