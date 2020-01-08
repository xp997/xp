package cn.xp1997.xp.blog.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author xp
 * @since 2020-01-08
 */
public class XpArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 作者id

     */
    @TableField("author_id")
    private String authorId;

    /**
     * 作者名称
     */
    @TableField("author_name")
    private String authorName;

    /**
     * 状态(0:正常, 1:冻结)
     */
    @TableField("status")
    private Integer status;

    /**
     * 标签 多个标签用（,）隔开
     */
    @TableField("tag")
    private String tag;

    /**
     * 删除状态(0:正常， 1:删除)
     */
    @TableField("delete_status")
    private Integer deleteStatus;

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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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
        return "XpArticle{" +
            "id=" + id +
            ", title=" + title +
            ", content=" + content +
            ", authorId=" + authorId +
            ", authorName=" + authorName +
            ", status=" + status +
            ", tag=" + tag +
            ", deleteStatus=" + deleteStatus +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
