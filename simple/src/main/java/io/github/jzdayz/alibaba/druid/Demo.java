package io.github.jzdayz.alibaba.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlSelectParser;
import com.alibaba.druid.sql.parser.SQLSelectParser;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements("select * from information_schema.tables where table_schema = 'center_data' and table_type = 'VIEW' and table_name in (\n" +
                "\n" +
                "'cd_project',  \n" +
                "'cd_V_PROJECT_INFOS', 'cd_VIEW_SALE_CONTRACT_LIST_ALL',\n" +
                "'cd_salescontact',\n" +
                "'cd_project_contact_calculation_proportion',  'cd_bi_t_contractstatistics','cd_bd_t_materialrelationcontract',\n" +
                "'cd_V_SALE_MAINTENANCE_TERM', \n" +
                "'every_month_person_cost', \n" +
                "'cd_v_project_fee_month_info',   \n" +
                "'old_pay_data',    \n" +
                "'project_plan_milestone',   \n" +
                "'milestone_project_relation',  \n" +
                "'pm_person_plan_cost', \n" +
                "'v_cd_sale_maintenance_term',  'v_cd_re_project_sale',\n" +
                "'log_plan',   \n" +
                "'cd_v_project_fee_month_info',   \n" +
                "'cd_V_CS_RESOURCE_COMPETENCE',    \n" +
                "'v_sale_receivables_plan',    \n" +
                "'v_cd_sale_maintenance_term',   \n" +
                "'cd_VIEW_SALE_CONTRACT_LIST_ALL','cd_V_PROJECT_INFOS'\n" +
                "\n" +
                ");", JdbcConstants.MYSQL);
        
    }
}
