/**
 * 
 */
package tk.utbc.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SQLSessionTest {
	

		@Inject
		SqlSessionFactory ssf;
		
		@Test
		public void testFactory(){
			System.out.println("SQLSessionFactory 가 dataSource 를 만드는데 성공했음"+ssf);
		}
		
		@Test
		public void testSession() throws Exception{
			try(SqlSession session = ssf.openSession()){
				System.out.println("세션을 만드는 것도 성공함 : "+session);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}

