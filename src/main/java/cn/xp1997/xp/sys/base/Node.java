package cn.xp1997.xp.sys.base;



import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Node implements Serializable {
    private Serializable id;
    private String title;
    private String field;
    private List<Node> children;
    private String href;
    //节点最初是否展开
    private Boolean spread;
    //节点最初是否被选中
    private Boolean checked;
    //节点是否禁用状态，默认false
    private Boolean disabled;

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "LayuiTree{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", field='" + field + '\'' +
                ", children=" + children +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", checked=" + checked +
                ", disabled=" + disabled +
                '}';
    }


    /**
     * 获取node
     * @param nodes 原始数据
     * @param parentId 顶层父节点id
     * @param titleName title对应的参数名
     * @param idName id对应的参数名
     * @param parentIdName parentId的参数名
     * @return List<Node>
     */
    public static <T> List<Node> getNode(List<T> nodes,
                                         Serializable parentId,
                                         String titleName,
                                         String idName,
                                         String parentIdName) throws Exception{
        List<Node> nodeList = new ArrayList<>();
        String getTitleName = "get" + upFirstCap(titleName);
        String getIdName = "get" + upFirstCap(idName);
        String getParentIdName = "get" + upFirstCap(parentIdName);
        for (T t : nodes) {
            //反射获取方法
            Class<?> clazz =  t.getClass();
            Method getTitle = clazz.getMethod(getTitleName);
            Method getId = clazz.getMethod(getIdName);
            Method getParentId = clazz.getMethod(getParentIdName);

            //反射调用方法
            Serializable parendId = (Serializable) getParentId.invoke(t);
            Serializable id = (Serializable) getId.invoke(t);
            String title = (String) getTitle.invoke(t);

            //创建node
            if(parendId.equals(parentId)){
                Node node = new Node();
                node.setId(id);
                node.setTitle(title);
                //递归
                List<Node> nodeListResult = getNode(nodes, id, titleName, idName, parentIdName);
                node.setChildren(nodeListResult);
                nodeList.add(node);
            }

        }
        return nodeList;
    }

    /**
     * 首字母大写
     */
    private static String upFirstCap(String parm){
        char[] chars = parm.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char)(chars[0] - 32);
        }
        return new String(chars);
    }

}
