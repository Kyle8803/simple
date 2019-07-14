package tk.mybatis.simple.model;

//角色权限表
public class SysPrivilege
{
  /**
   mysql> describe sys_privilege;
   +----------------+--------------+------+-----+---------+----------------+
   | Field          | Type         | Null | Key | Default | Extra          |
   +----------------+--------------+------+-----+---------+----------------+
   | id             | bigint(20)   | NO   | PRI | NULL    | auto_increment |
   | privilege_name | varchar(50)  | YES  |     | NULL    |                |
   | privilege_url  | varchar(200) | YES  |     | NULL    |                |
   +----------------+--------------+------+-----+---------+----------------+
   */
  /**
   * 权限ID
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

  private String privilegeName;
  public void setPrivilegeName(String privilegeName)
  {
      this.privilegeName = privilegeName;
  }
  public String getPrivilegeName()
  {
      return privilegeName;
  }

  private String privilegeUrl;
  public void setPrivilegeUrl(String privilegeUrl)
  {
      this.privilegeUrl = privilegeUrl;
  }
  public String getPrivilegeUrl()
  {
      return privilegeUrl;
  }
}

