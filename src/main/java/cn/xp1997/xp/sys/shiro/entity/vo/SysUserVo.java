package cn.xp1997.xp.sys.shiro.entity.vo;

import cn.xp1997.xp.sys.shiro.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;

public class SysUserVo extends SysUser {

    @TableField(exist = false)
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
