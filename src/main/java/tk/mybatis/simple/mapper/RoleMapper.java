package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {
	//3.1 @Select注解
	@Select(value = {  "select id, role_name, enabled," 
             + "create_by createBy, create_time createTime"
			 + " from sys_role"
             + " where id = #{id}"})
	SysRole selectById(Long id);
	
	//3.1.2 使用resultMap方式
	@Results(id = "roleResultMap", value = {
		@Result(property = "id",         column = "id", id = true),
		@Result(property = "roleName",   column = "role_name"),
		@Result(property = "enabled",    column = "enabled"),
		@Result(property = "createBy",   column = "create_by"),
		@Result(property = "createTime", column = "create_time")
	})
	@Select(value = { "select id, role_name, enabled, create_by, create_time"
			        + " from sys_role where id = #{id}" })
	SysRole selectById2(Long id);
	
	@ResultMap(value = "roleResultMap")
	@Select(value = {"select * from sys_role"})
	List<SysRole> selectAll();
	
	/*
	  * 6.1.2.1 collection集合的嵌套结果映射
	  * 获取所有的角色以及对应的所有权限
	  */
	List<SysRole> selectAllRolesAndPrivileges();
}
