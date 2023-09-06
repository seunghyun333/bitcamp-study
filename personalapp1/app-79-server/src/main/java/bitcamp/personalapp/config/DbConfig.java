package bitcamp.personalapp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Application을 실행하는데 필요한 객체를 설정하는 일을 한다.

@PropertySource({"classpath:bitcamp/personalapp/config/ncloud/jdbc.properties"})
@EnableTransactionManagement
public class DbConfig {

  {
    System.out.println("DbConfig() 호출됨!");
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
    System.out.println("DbConfig.transactionManager() 호출됨!");

    return new DataSourceTransactionManager(dataSource);
  }

}
