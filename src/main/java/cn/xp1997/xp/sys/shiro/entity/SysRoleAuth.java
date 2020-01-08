package cn.xp1997.xp.sys.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
public class SysRoleAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("auth_id")
    private String authId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "SysRoleAuth{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", authId=" + authId +
        "}";
    }
}
