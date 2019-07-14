package tk.mybatis.simple.mapper;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

public class CountryMapperTest extends BaseMapperTest
{
	/*
	private static SqlSessionFactory sqlSessionFactory;
	
	
	@BeforeClass
	public static void init()
	{
		try
		{
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}
		catch(IOException ignore)
		{
			ignore.printStackTrace();
		}
	}
	*/
	
	@Test
	public void testSelectAll()
	{
		SqlSession sqlSession = getSqlSession();
		try
		{
			/*通过SqlSession的selectList方法查找到CountryMapper.xml中id="selectAll"的方法，执行SQL查询。
			  MyBatis底层使用JDBC执行SQL,获得查询结果集ResultSet后，根据resultType的配置将结果映射为Country类型的集合，
			    返回查询结果,这样就得到了最后的查询结果countryList.
			*/
			List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
			
			//简单将结果输出到控制台。
			printCountryList(countryList);
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	private void printCountryList(List<Country> countryList)
	{
		for(Country country : countryList)
		{
			System.out.printf("%-4d%4s%4s\n", country.getId(),
					                          country.getCountryname(),
					                          country.getCountrycode());
		}
	}
}
