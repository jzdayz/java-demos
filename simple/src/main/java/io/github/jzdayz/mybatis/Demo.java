package io.github.jzdayz.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
                    SqlSession sqlSession = sqlSessionFactory.openSession();
            ) {
                TestMapper mapper = configuration.getMapper(TestMapper.class, sqlSession);
//                List<Test> all = mapper.all();
//                System.out.println(all);
                Map<String, Map<String, Object>> map = mapper.map(1);
                System.out.println(map);
            }
        }
    }
}
