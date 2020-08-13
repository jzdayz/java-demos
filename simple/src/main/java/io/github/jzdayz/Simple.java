package io.github.jzdayz;

public class Simple {
    
    public static void main(String[] args) {
        String sql = "id, project_id, project_name, project_simple_name, project_num, job_number, job_name, project_belong_id, project_belong_name, project_status, operate_time, create_time, modify_time, seal_company, seal_company_name, sale_contract_info_id, contract_number, contract_name, total_plan_fee, total_plan_people, is_antecedent, project_start_date, pre_maintain_end_date, project_type_id, project_type_name, user_id, dept_id, is_delete";
        
        
        String[] a = sql.split(",");
        
        final String prefix = "c.";
    
        StringBuilder sb = new StringBuilder();
        for (final String s : a) {
            sb.append(prefix).append("`").append(s.trim()).append("`").append(",");
        }
        System.out.println(sb);
    }
    
}
