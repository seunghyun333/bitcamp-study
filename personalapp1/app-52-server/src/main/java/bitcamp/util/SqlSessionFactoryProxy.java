package bitcamp.util;

import java.sql.Connection;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory{
	
	SqlSessionFactory original;
	
	ThreadLocal<SqlSession> sqlSessionBox = new ThreadLocal<>();
	
	public SqlSessionFactoryProxy(SqlSessionFactory original) {
		this.original = original;
	}
	
	public void clean() {
		SqlSession sqlSession = sqlSessionBox.get();
		if (sqlSession != null) {
			sqlSession.rollback();
			sqlSessionBox.remove();
		}
	}

	public SqlSession openSession() {
		return original.openSession();
	}

	public SqlSession openSession(boolean autoCommit) {
		
		if(!autoCommit) {
			SqlSession sqlSession = sqlSessionBox.get();
			
			if(sqlSession ==null) {
				sqlSession = original.openSession(false);
				sqlSessionBox.set(sqlSession);
			}
			return sqlSession;
		}
		return original.openSession(autoCommit);
	}

	public SqlSession openSession(Connection connection) {
		return original.openSession(connection);
	}

	public SqlSession openSession(TransactionIsolationLevel level) {
		return original.openSession(level);
	}

	public SqlSession openSession(ExecutorType execType) {
		return original.openSession(execType);
	}

	public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
		return original.openSession(execType, autoCommit);
	}

	public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
		return original.openSession(execType, level);
	}

	public SqlSession openSession(ExecutorType execType, Connection connection) {
		return original.openSession(execType, connection);
	}

	public Configuration getConfiguration() {
		return original.getConfiguration();
	}

	
	

}
