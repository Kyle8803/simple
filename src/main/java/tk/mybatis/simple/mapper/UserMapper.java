package tk.mybatis.simple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public interface UserMapper {
	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	SysUser selectById(Long id);
	
	/*
	 * 查询全部用户
	 */
	List<SysUser> selectAll();
	
	/*
	 * 2.4.1 简单的insert方法
	 * @param sysUser
	 * @return 
	 */
	int insert(SysUser sysUser);
	
	/*
	 * 2.4.3 使用selectKey返回主键的值
	 * 新增用户-使用selectKey方式
	 * @param sysUser
	 * @return
	 */
	int insert3(SysUser sysUser);
	
	/*
	 * 4.1.3 在insert动态插入列中使用if
	 */
	int insert5(SysUser sysUser);
	
	/*
	 * 2.5 update用法
	 * 根据主键更新
	 * @param sysUser
	 * @return
	 */
	int updateById(SysUser sysUser);
	
	/*
	 * 2.6 delete用法
	 * 通过主键删除
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	
	/*
	 * 2.7 多个接口参数的用法
	 * 根据用户id和角色的enabled状态获取用户的角色
	 * @param userId
	 * @param enabled
	 * @return
	 */
	
	/*
	 * 给参数配置@Param注解后，MyBatis就会自动将参数封装成Map类型，@Param注解值会作为Map中的key,
	 * 因此在SQL部分就可以通过配置的注解值来使用参数。
	 */
	 List<SysRole> selectRolesByUserIdAndRoleEnabled(
			@Param("userId")    Long userId,
			@Param("enabled")Integer enabled);
	 
	 /*
	  * 4.1.1 在where条件中使用if
	  * 根据动态条件查询用户信息
	  * @param sysUser
	  * @return
	  */
	 List<SysUser> selectByUser(SysUser sysUser);
	 
	 /*
	  * 4.1.2 在update更新列中使用if
	  * 根据主键更新
	  * @param sysUser
	  * @return
	  */
	 int updateByIdSelective(SysUser sysUser);
	 
	 /*
	  * 4.2 choose用法
	  * 根据用户 id或用户名查询
	  * @param sysUser
	  * @return
	  */
	 SysUser selectByIdOrUserName(SysUser sysUser);
	 
	 /*
	  * 4.3.1 where用法
	  * where标签的作用:如果该标签包含的元素中有返回值，就插入一个where;如果where后面的字符串是以AND
	            和 OR开头的，就将它们剔除. 
	    根据动态条件查询用户信息        
	  */
	 List<SysUser> selectByUser2(SysUser sysUser);
	 
	 /*
	  * 4.4.1 foreach实现in集合
	  * 根据用户id集合查询
	  * @param idList
	  * @return
	  */
	 List<SysUser> selectByIdList(List<Long> idList);
	 
	 /*
	  * 4.4.2 foreach实现批量插入
	  * 批量插入用户信息
	  * @param userList
	  * @return
	  */
	 int insertList(List<SysUser> userList);
	 
	 /*
	  * 4.4.3 foreach实现动态update
	  * 通过Map更新列
	  * @param map
	  * @return
	  */
	 int updateByMap(Map<String, Object> map);
	 
	 /*
	  * 6.1.1.1使用自动映射处理一对一关系
	  * 根据用户id获取用户信息和用户的角色信息
	  * @param id
	  * @return
	  */
	 SysUser selectUserAndRoleById(Long id);
	 
	 //6.1.1.3使用resultMap的association标签配置一对一映射
	 SysUser selectUserAndRoleById2(Long id);
	 
	 /*
	  * 6.1.2.1 collection集合的嵌套结果映射
	  * 获取所有的用户以及对应的所有角色
	  */
	 List<SysUser> selectAllUserAndRoles();
}
