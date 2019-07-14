package tk.mybatis.simple.model;


import java.util.Date;
import java.util.List;

public class SysRole
{
    /*
            +-------------+-------------+------+-----+---------+----------------+
            | Field       | Type        | Null | Key | Default | Extra          |
            +-------------+-------------+------+-----+---------+----------------+
            | id          | bigint(20)  | NO   | PRI | NULL    | auto_increment |
            | role_name   | varchar(50) | YES  |     | NULL    |                |
            | enabled     | int(11)     | YES  |     | NULL    |                |
            | create_by   | bigint(20)  | YES  |     | NULL    |                |
            | create_time | datetime    | YES  |     | NULL    |                |
            +-------------+-------------+------+-----+---------+----------------+
            5 rows in set (0.13 sec)
        */
    private Long id;
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }

    private String roleName;
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    public String getRoleName()
    {
        return roleName;
    }

    private Integer enabled;
    public void setEnabled(Integer enabled)
    {
        this.enabled = enabled;
    }
    public Integer getEnabled()
    {
        return enabled;
    }

    private Long createBy;
    public void setCreateBy(Long createBy)
    {
        this.createBy = createBy;
    }
    public Long getCreateBy()
    {
        return createBy;
    }

    private Date createTime;
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 角色包含的权限列表
     */
    private List<SysPrivilege> privilegeList;
    public void setPrivilegeList(List<SysPrivilege> privilegeList)
    {
        this.privilegeList = privilegeList;
    }
    public List<SysPrivilege> getPrivilegeList()
    {
        return privilegeList;
    }
}
