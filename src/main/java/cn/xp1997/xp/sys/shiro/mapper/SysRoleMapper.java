package cn.xp1997.xp.sys.shiro.mapper;


import cn.xp1997.xp.sys.shiro.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查询角色集合
     * @param userId 用户id
     * @return 角色集合
     */
    List<SysRole> getRoleListByUser(String userId);


    /**
     * 分页查询
     */
    IPage<SysRole> getRolePage(Page<SysRole> page, String userId);
}
