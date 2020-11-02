package io.github.jzdayz.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class Demo {
    public static void main(String[] args) throws IOException {
        try (HikariDataSource ds = new HikariDataSource()) {
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
            ds.setUsername("root");
            ds.setPassword("JKLjkl123");
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
//                Test test1 = new Test();
//                test1.setName("name");
//                mapper.insertBase(test1);
//                Test test2 = new Test();
//                test2.setName1("name1");
//                mapper.insertBase(test2);
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
//                System.out.println(test1.getId());
//                System.out.println(test2.getId());
            }
        }
    }
}
