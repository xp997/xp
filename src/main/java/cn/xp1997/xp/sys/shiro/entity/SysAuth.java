package cn.xp1997.xp.sys.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xp
 * @since 2019-11-22
 */
public class SysAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    /**
     * 权限名称
     */
    @TableField("auth_name")
    private String authName;

    /**
     * 权限描述
     */
    @TableField("auth_desc")
    private String authDesc;

    /**
     * 权限url
     */
    @TableField("auth_url")
    private String authUrl;

    @TableField("parent_id")
    private String parentId;

    @TableField("is_menu")
    private Integer isMenu;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }
    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }
    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysAuth{" +
                "id='" + id + '\'' +
                ", authName='" + authName + '\'' +
                ", authDesc='" + authDesc + '\'' +
                ", authUrl='" + authUrl + '\'' +
                ", parentId='" + parentId + '\'' +
                ", isMenu=" + isMenu +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
