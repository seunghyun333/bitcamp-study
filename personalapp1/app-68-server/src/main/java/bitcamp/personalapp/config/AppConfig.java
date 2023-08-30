package bitcamp.personalapp.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import bitcamp.util.SqlSessionFactoryProxy;

@ComponentScan(basePackages = 
{"bitcamp.personalapp.dao", "bitcamp.personalapp.controller", "bitcamp.personalapp.service"})
public class AppConfig {
	
	public AppConfig() {
		System.out.println("AppConfig() 호출됨!");
	}

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
	  System.out.println("AppConfig.sqlSessionFactory() 호출됨!");
    return new SqlSessionFactoryProxy(
    		new SqlSessionFactoryBuilder().build(
    				Resources.getResourceAsStream("bitcamp/personalapp/config/mybatis-config.xml")));
  }


}
