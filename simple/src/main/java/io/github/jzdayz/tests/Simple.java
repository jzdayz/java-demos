package io.github.jzdayz.tests;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("ConstantConditions")
@Slf4j
public class Simple {

    public static void main(String[] args) {
        String sql = "id, project_id, plan_type, plan_status, plan_audit_person_id, plan_audit_person_name, plan_version, deleted, create_time, modify_time,owner_user_id,owner_dep_id,approve_time,approve_opinion";


        String[] a = sql.split(",");

        final String prefix = "";
        final String asPrefix = "overview";

        StringBuilder sb = new StringBuilder();
        for (final String s : a) {
            sb.append(prefix).append("`").append(s.trim()).append("`");
            if (asPrefix.length() > 0) {
                sb.append(" as ").append(asPrefix).append("_").append(s.trim());
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        log.info("==========================================================");
        log.info("\n{}",sb);
        log.info("==========================================================");
    }

}
