package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;

public class RoleMapperTest extends BaseMapperTest{
	@Test
	public void testSelectById()
	{
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			
			//调用selectById方法，查询 id = 1 的角色
			SysRole role = roleMapper.selectById(1L);
			
			//role不为空
			Assert.assertNotNull(role);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectById2()
	{
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			
			//调用selectById2方法，查询 id = 1的角色
			SysRole role = roleMapper.selectById2(1L);
			
			//role不为空
			Assert.assertNotNull(role);
			
			//roleName = 管理员
			Assert.assertEquals("管理员", role.getRoleName());
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll()
	{
		SqlSession sqlSession = getSqlSession();
		
		try
		{
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			
			//调用selectAll方法查询所有角色
			List<SysRole> roleList = roleMapper.selectAll();
			
			//结果不为空
			Assert.assertNotNull(roleList);
			
			//角色数量大于0
			Assert.assertTrue(roleList.size() > 0);
		}
		finally
		{
			sqlSession.close();
		}
	}
}
