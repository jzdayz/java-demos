package io.github.jzdayz.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class Demo {
    public static void main(String[] args) throws IOException {
        try (HikariDataSource ds = new HikariDataSource()) {
            ds.setJdbcUrl("jdbc:p6spy:mysql://localhost:3306/test1?useSSL=false");
            ds.setUsername("root");
            ds.setPassword("123123123");
            ds.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, ds);
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(TestMapper.class);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

            final String xmlFile = "mybatis/TestMapper.xml";
            ClassPathResource xml = new ClassPathResource(xmlFile);
            try (
                    InputStream inputStream = xml.getInputStream();
            ) {
                XMLMapperBuilder xmlParser = new XMLMapperBuilder(inputStream, configuration, xmlFile, configuration.getSqlFragments(), TestMapper.class.getName());
                xmlParser.parse();
            }


            try (
                    SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)
            ) {
                TestMapper mapper = configuration.getMapper(TestMapper.class, sqlSession);
                test2(sqlSession,mapper);
            }
        }
    }

    private static void test3(Configuration configuration) {
        MappedStatement mappedStatement =
                configuration.getMappedStatement(TestMapper.class.getName() + ".insertBase");
        Test t1 = new Test();
        t1.setName("name");
        BoundSql boundSql = mappedStatement.getBoundSql(t1);

        Test t2 = new Test();
        t2.setName1("name1");
        t2.setName("1111");
        BoundSql boundSql2 = mappedStatement.getBoundSql(t2);

        System.out.println(1);
    }

    private static void test2(SqlSession sqlSession, TestMapper mapper) {
        Test test1 = new Test();
        test1.setName("name222");
        mapper.insertBase(test1);
        Test test2 = new Test();
        test2.setName("name11111");
        mapper.insertBase(test2);
        sqlSession.flushStatements();
        sqlSession.commit();
        System.out.println(test1.getId());
        System.out.println(test2.getId());
    }

    private static void test1(SqlSession sqlSession, TestMapper mapper) {
        for (int i = 0; i < 190000; i++) {
            Test a = new Test();
            a.setName(i + "1111");
            a.setName1(i + "2222");
            mapper.insertBase(a);
            if (i % 2000 == 0) {
                sqlSession.flushStatements();
                System.out.println("flush");
            }
        }
        sqlSession.flushStatements();
        sqlSession.commit();
    }
}
