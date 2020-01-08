package cn.xp1997.xp.blog.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xp
 * @since 2020-01-08
 */
public class XpArticleCommon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 0:正常 1:屏蔽
     */
    @TableField("status")
    private Integer status;

    /**
     * 删除状态 0:正常。1:删除
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * Ip地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 评论作者名称
     */
    @TableField("author_name")
    private String authorName;

    /**
     * 评论作者id
     */
    @TableField("author_id")
    private String authorId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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

    @Override
    public String toString() {
        return "XpArticleCommon{" +
            "id=" + id +
            ", content=" + content +
            ", status=" + status +
            ", deleteStatus=" + deleteStatus +
            ", ip=" + ip +
            ", authorName=" + authorName +
            ", authorId=" + authorId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
