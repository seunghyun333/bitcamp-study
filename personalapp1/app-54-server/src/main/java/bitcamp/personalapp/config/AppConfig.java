package bitcamp.personalapp.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.util.Bean;
import bitcamp.util.ComponentScan;
import bitcamp.util.SqlSessionFactoryProxy;

@ComponentScan(basePackages = {"bitcamp.personalapp.dao", "bitcamp.personalapp.handler"})
public class AppConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/personalapp/config/mybatis-config.xml")));
  }


}
