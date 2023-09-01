package bitcamp.personalapp.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
<<<<<<< HEAD
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

//Application을 실행하는데 필요한 객체를 설정하는 일을 한다.
//
@ComponentScan(basePackages = {
     "bitcamp.personalapp.dao",
     "bitcamp.personalapp.controller",
     "bitcamp.personalapp.service"})
@PropertySource({"classpath:bitcamp/personalapp/config/ncloud/jdbc.properties"})
@MapperScan("bitcamp.personalapp.dao") // Mybatis가 자동으로 생성할 DAO 객체의 인터페이스 패키지 지정
public class AppConfig {

public AppConfig() {
 System.out.println("AppConfig() 호출됨!");
}
=======
import bitcamp.util.SqlSessionFactoryProxy;

@ComponentScan(basePackages = {"bitcamp.personalapp.dao", "bitcamp.personalapp.controller",
    "bitcamp.personalapp.service"})
public class AppConfig {

  public AppConfig() {
    System.out.println("AppConfig() 호출됨!");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    System.out.println("AppConfig.sqlSessionFactory() 호출됨!");
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/personalapp/config/mybatis-config.xml")));
  }
>>>>>>> b3bba5088185532d3ec7440c22cbd82ba5729fb1

@Bean
public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) throws Exception {
 System.out.println("AppConfig.sqlSessionFactory() 호출됨!");

 // Mybatis에서 Log4j 2.x 버전을 사용하도록 활성화시킨다.
 // 활성화시키지 않으면 Mybatis에서 로그를 출력하지 않는다.
 org.apache.ibatis.logging.LogFactory.useLog4J2Logging();

 SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
 factoryBean.setDataSource(dataSource);
 factoryBean.setTypeAliasesPackage("bitcamp.personalapp.vo");
 factoryBean.setMapperLocations(appCtx.getResources("classpath:bitcamp/personalapp/dao/mysql/*Dao.xml"));

 return factoryBean.getObject();
}

@Bean
public DataSource dataSource(
       @Value("${jdbc.driver}") String driver,
       @Value("${jdbc.url}") String url,
       @Value("${jdbc.username}") String username,
       @Value("${jdbc.password}") String password) {
 System.out.println("AppConfig.dataSource() 호출됨!");

 DriverManagerDataSource ds = new DriverManagerDataSource();
 ds.setDriverClassName(driver);
 ds.setUrl(url);
 ds.setUsername(username);
 ds.setPassword(password);

 return ds;
}

@Bean
public PlatformTransactionManager transactionManager(DataSource dataSource) {
 System.out.println("AppConfig.transactionManager() 호출됨!");

 return new DataSourceTransactionManager(dataSource);
}

}
