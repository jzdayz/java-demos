package io.github.jzdayz.hibernate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.SQLServer2008Dialect;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.schema.internal.Helper;
import org.hibernate.tool.schema.spi.ScriptTargetOutput;
import org.hibernate.tool.schema.spi.TargetDescriptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 */
@Slf4j
public class Demo {

    private static final MySQL8Dialect MY_SQL_8_DIALECT = new MySQL8Dialect();
    private static final Oracle10gDialect ORACLE_10_G_DIALECT = new Oracle10gDialect();
    private static final SQLServer2008Dialect SQL_SERVER_2008_DIALECT = new SQLServer2008Dialect();

    public static void main(String[] args) throws Exception {
        Table ta = Table.builder().comment("测试的").tableName("TEST").columns(
                Arrays.asList(
                        Column.builder().unique(false).notNull(true).comment("名称").length(100).name("name")
                                .type("string").uniqueKey("TTT").build(),
                        Column.builder().unique(true).uniqueKey("DDDSSS").comment("U名称").length(100)
                                .name("u_name").type("string").build(),
                        Column.builder().comment("U日期").length(100).name("u_date").type("date").build(),
                        Column.builder().length(211).name("id").type("string").id(true).build()
                )
        ).build();
        generate(MY_SQL_8_DIALECT, ta);
        generate(ORACLE_10_G_DIALECT, ta);
        generate(SQL_SERVER_2008_DIALECT, ta);
    }

    private static void generate(Dialect dialect, Table table) throws Exception {

        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", dialect.getClass())
                        .build());

//        metadata.addAnnotatedClass(A.class);
//        try (
//                InputStream resourceAsStream = Demo.class.getClassLoader().getResourceAsStream("test_hibernate_generator.xml");
//        ){
//            metadata.addInputStream(resourceAsStream);
//        }

        metadata.addInputStream(gen(table));

        SchemaExport export = new SchemaExport();
        export.setFormat(true);
        Metadata mt = metadata.buildMetadata();

//        export.createOnly(EnumSet.of(TargetType.STDOUT),
//                mt);

        StandardServiceRegistry serviceRegistry = ((MetadataImplementor) mt)
                .getMetadataBuildingOptions().getServiceRegistry();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ScriptTargetOutput scriptTargetOutput;
        try (
                OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream)
        ) {
            scriptTargetOutput = Helper.interpretScriptTargetSetting(
                    writer,
                    serviceRegistry.getService(ClassLoaderService.class),
                    (String) serviceRegistry.getService(ConfigurationService.class).getSettings()
                            .get(AvailableSettings.HBM2DDL_CHARSET_NAME)
            );
            export.doExecution(
                    SchemaExport.Action.CREATE,
                    false,
                    mt,
                    serviceRegistry,
                    new TargetDescriptor() {

                        @Override
                        public EnumSet<TargetType> getTargetTypes() {
                            return EnumSet.of(TargetType.SCRIPT);
                        }

                        @Override
                        public ScriptTargetOutput getScriptTargetOutput() {
                            return scriptTargetOutput;
                        }
                    }
            );
        }

        log.info("{},生成SQL->{}", dialect.getClass(), new String(byteArrayOutputStream.toByteArray()));
    }

    public static InputStream gen(Table table) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(Demo.class, "/");
        Template temp = cfg.getTemplate("hibernate_generator.tmp");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Map<String, Object> map = new HashMap<>();
        map.put("table", table);
        temp.process(map, new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8));

        byte[] buf = byteArrayOutputStream.toByteArray();
//        log.info("数据->{}",new String(buf));
        return new ByteArrayInputStream(buf);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Table {

        private String tableName;
        private String comment;
        private List<Column> columns;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Column {

        private String name;
        private String type;
        private int length;
        private String comment;
        private String uniqueKey;
        private boolean id;
        private boolean notNull;
        private boolean unique;
    }
}
