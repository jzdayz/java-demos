package io.github.jzdayz.jsqlparser;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.MySQLIndexHint;
import net.sf.jsqlparser.expression.SQLServerHints;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Pivot;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.UnPivot;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

public class Demo {
    public static void main(String[] args) throws Exception {
        // 修改sql语句的表名
        String sql = "select * from test b";
        Select select = (Select)CCJSqlParserUtil.parse(sql);

        ExpressionDeParser expressionDeParser = new ExpressionDeParser();
        StringBuilder buffer = new StringBuilder();

        SelectDeParser deparser = new SelectDeParser(expressionDeParser, buffer) {
            @Override
            public void visit(Table tableName) {
                buffer.append("test111");
                Alias alias = tableName.getAlias();
                if (alias != null) {
                    buffer.append(alias);
                }
                Pivot pivot = tableName.getPivot();
                if (pivot != null) {
                    pivot.accept(this);
                }
                UnPivot unpivot = tableName.getUnPivot();
                if (unpivot != null) {
                    unpivot.accept(this);
                }
                MySQLIndexHint indexHint = tableName.getIndexHint();
                if (indexHint != null) {
                    buffer.append(indexHint);
                }
                SQLServerHints sqlServerHints = tableName.getSqlServerHints();
                if (sqlServerHints != null) {
                    buffer.append(sqlServerHints);
                }
            }
        };
        expressionDeParser.setSelectVisitor(deparser);
        expressionDeParser.setBuffer(buffer);
        select.getSelectBody().accept(deparser);
        //End of value modification

        System.out.println(buffer.toString());

    }
}
