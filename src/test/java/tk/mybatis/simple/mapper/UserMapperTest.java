package tk.mybatis.simple.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest{
	
	@Test
	public void testSelectById()
	{
		//调用父类BaseMapperTest的getSqlSession()获取sqlSession
		SqlSession sqlSession = getSqlSession();
		
		try {
			//获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//调用selectById方法，查询id=1的用户
			SysUser user = userMapper.selectById(1L);
			
			Assert.assertNotNull(user);

			Assert.assertEquals("admin", user.getUserName());
			
		}finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll()
	{
		
		SqlSession sqlSession = getSqlSession();
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//调用selectAll方法查询所有用户
			List<SysUser> userList = userMapper.selectAll();
			
			//结果不为空
			Assert.assertNotNull(userList);
			
			//用户数量大于0
			Assert.assertTrue(userList.size() > 0);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//2.4.1 简单的insert方法
	@Test
	public void testInsert()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			SysUser user = new SysUser();
			
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			
			//正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[] {1, 2, 3});
			
			user.setCreateTime(new Date());
			
			//将新建的对象插入数据库中，result是执行的SQL影响的行数
			int result =userMapper.insert(user);
			
			//只插入1条数据
			Assert.assertEquals(1, result);
			
			//id为null,没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		}
		finally
		{
			//为了不影响其他测试，这里选择回滚
			sqlSession.rollback();
			
			sqlSession.close();
		}
	}
	
	//2.4.2 使用JDBC方式返回主键自增的值
	@Test
	public void testInsert2()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			SysUser user = new SysUser();
			
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			
			//正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[] {1, 2, 3});
			
			user.setCreateTime(new Date());
			
			//将新建的对象插入数据库中，result是执行的SQL影响的行数
			int result =userMapper.insert(user);
			
			//只插入1条数据
			Assert.assertEquals(1, result);
			
			//id为null,没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		}
		finally
		{
			//为了不影响其他测试，这里选择回滚
			sqlSession.rollback();
			
			sqlSession.close();
		}
	}
	
	//2.4.3 使用selectKey返回主键的值
	@Test
	public void testInsert3()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			SysUser user = new SysUser();
			
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			
			//正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[] {1, 2, 3});
			
			user.setCreateTime(new Date());
			
			//将新建的对象插入数据库中，result是执行的SQL影响的行数
			int result =userMapper.insert(user);
			
			//只插入1条数据
			Assert.assertEquals(1, result);
			
			//id为null,没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		}
		finally
		{
			//为了不影响其他测试，这里选择回滚
			sqlSession.rollback();
			
			sqlSession.close();
		}
	}
	
	//2.5 update用法
	@Test
	public void testUpdateById()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//从数据库查询1个user对象
			SysUser user = userMapper.selectById(1L);
			
			//当前userName为admin
			Assert.assertEquals("admin", user.getUserName());
		}
		finally
		{
			//由于默认的sqlSessionFactory.openSession()是不自动提交的，因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	/*
	 * 2.6 delete用法
	 * @param id
	 * @return
	 */
	@Test
	public void testDeleteById()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//从数据库查询1个user对象，根据id=1查询
			SysUser user1 = userMapper.selectById(1L);
			
			//现在还能查询出user对象
			Assert.assertNotNull(user1);
			
			//调用方法删除
			Assert.assertEquals(1, userMapper.deleteById(1L));
			
			//再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1L));
		}
		finally
		{
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	//2.7 多个接口参数的用法
	@Test
	public void testSelectRolesByUserIdAndRoleEnabled()
	{
		SqlSession sqlSession = getSqlSession();
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			
			//结果不为空
			Assert.assertNotNull(userList);
			
			//角色数量大于0个
			Assert.assertTrue(userList.size() > 0);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//4.1.1 在where条件中使用if
	@Test
	public void testSelectByUser()
	{
		SqlSession sqlSession = getSqlSession();
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//只查询用户名时
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			//只查询用户邮箱时
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			//当同时查询用户名和邮箱时
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			//由于没有同时符合这两个条件的用户，因此查询结果数为0
			Assert.assertTrue(userList.size() == 0);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//4.1.2 在update更新列中使用if
	@Test
	public void testUpdateByIdSelective()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//创建一个新的user对象
			SysUser user = new SysUser();
			
			//更新id = 1的用户
			user.setId(1L);
			
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			
			//更新邮箱，特别注意，这里的返回值result执行的是SQL影响的行数
			int result = userMapper.updateByIdSelective(user);
			
			//只更新了1条数据
			Assert.assertEquals(1, result);
			
			//根据当前id查询修改后的数据
			user = userMapper.selectById(1L);
			
			//修改后的名字保持不变，但是邮箱变成了新的
			Assert.assertEquals("admin", user.getUserName());
			
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}
		finally
		{
			//为了不影响其他测试，这里选择回滚
			sqlSession.rollback();
			
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	//<!-- 4.1.3 在insert动态插入列中使用if -->
	@Test
	public void testInsert5Selective()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//创建一个user对象
			SysUser user = new SysUser();
			
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			
			//插入数据库
			userMapper.insert5(user);
			
			//获取插入的这条数据
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}
		finally
		{
			sqlSession.rollback();
			
			sqlSession.close();
		}
	}
	
	//<!-- 4.2 choose用法 -->
	@Test
	public void testSelectByIdOrUserName()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//只查询用户名时
			SysUser query = new SysUser();
			query.setId(1L);
			query.setUserName("admin");
			
			SysUser user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			
			//当没有id时
			query.setId(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			
			//当id和name都为空时
			query.setUserName(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNull(user);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	 /*
	  * 4.3.1 where用法
	  * where标签的作用:如果该标签包含的元素中有返回值，就插入一个where;如果where后面的字符串是以AND
	            和 OR开头的，就将它们剔除. 
	    根据动态条件查询用户信息        
	  */
	@Test
	public void testSelectByUser2()
	{
		SqlSession sqlSession = getSqlSession();
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//只查询用户名时
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			//只查询用户邮箱时
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			//当同时查询用户名和邮箱时
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			//由于没有同时符合这两个条件的用户，因此查询结果数为0
			Assert.assertTrue(userList.size() == 0);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//4.4.1 foreach实现in集合 
	@Test
	public void testSelectByIdList()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Long> idList = new ArrayList<Long>();
			idList.add(1L);
			idList.add(1001L);
			
			//业务逻辑中必须校验idList.size() > 0
			List<SysUser> userList = userMapper.selectByIdList(idList);
			Assert.assertEquals(2, userList.size());
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//4.4.2 foreach实现批量插入
	@Test
	public void testInsertList()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//声明一个List<SysUser>类型的对象引用 userList
			List<SysUser> userList = new ArrayList<SysUser>();
			
			for(int i=0; i<2; i++)
			{
				SysUser user = new SysUser();
				user.setUserName("test" + i);
				user.setUserPassword("123456");
				user.setUserEmail("test@mybatis.tk");
				
				userList.add(user);
			}
			
			//将新建的对象批量插入数据库中
			//特别注意，这里的返回值result是执行SQL影响的行数
			int result = userMapper.insertList(userList);
			Assert.assertEquals(2, result);
		}
		finally
		{
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	//4.4.3 foreach实现动态update
	@Test
	public void testUpdateByMap()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			//查询条件，同样也是更新字段，必须保证该值存在
			map.put("id", 1L);
			
			//要更新的其他字段
			map.put("user_email", "test@mybatis.tk");
			map.put("user_password", "12345678");
			
			//更新数据
			userMapper.updateByMap(map);
			
			//根据当前id查询修改后的数据
			SysUser user = userMapper.selectById(1L);
			
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}
		finally
		{
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	//6.1.1.1 使用自动映射处理一对一关系
	@Test
	public void testSelectUserAndRoleById()
	{
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//特别注意，在测试数据中，id = 1L的用户有两个角色，不适合这个例子
			//这里使用只有一个角色的用户 (id = 1001L)
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			
			//user不为空
			Assert.assertNotNull(user);
			
			//user.role也不为空
			Assert.assertNotNull(user.getRole());
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//6.1.1.3使用resultMap的association标签配置一对一映射
	@Test
	public void testSelectUserAndRoleById2()
	{
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//特别注意，在测试数据中，id = 1L的用户有两个角色，不适合这个例子
			//这里使用只有一个角色的用户 (id = 1001L)
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			
			//user不为空
			Assert.assertNotNull(user);
			
			//user.role也不为空
			Assert.assertNotNull(user.getRole());
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	/*
	 * 6.1.2.1 collection集合的嵌套结果映射
	 * 获取所有的用户以及对应的所有角色
	 */
	@Test
	public void testSelectAllUserAndRoles()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAllUserAndRoles();
			System.out.println("用户数:" + userList.size());
			
			for(SysUser user : userList) {
				System.out.println("用户名: " + user.getUserName());
				for(SysRole role : user.getRoleList())
				{
					System.out.println("角色名: " + role.getRoleName());
				}
				System.out.println();
			}
		}finally
		{
			sqlSession.close();
		}
	}
}
