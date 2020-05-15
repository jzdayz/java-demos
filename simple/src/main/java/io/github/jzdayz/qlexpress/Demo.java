package io.github.jzdayz.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) throws Exception{
        demo();
    }

    @Named("科目")
    private static class Project{
        @Named("语文")
        private int ywScore;
        @Named("数学")
        private int sxScore;
        @Named("英语")
        private int yyScore;

        public int getYwScore() {
            return ywScore;
        }

        public void setYwScore(int ywScore) {
            this.ywScore = ywScore;
        }

        public int getSxScore() {
            return sxScore;
        }

        public void setSxScore(int sxScore) {
            this.sxScore = sxScore;
        }

        public int getYyScore() {
            return yyScore;
        }

        public void setYyScore(int yyScore) {
            this.yyScore = yyScore;
        }
    }



    private static void demo() throws Exception {

        Project project = new Project();
        project.setSxScore(100);
        project.setYyScore(1);
        project.setYwScore(1);

        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        fill(context,project);

        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("则", "then",null);
        runner.addOperatorWithAlias("否则", "else",null);
        runner.addOperatorWithAlias("返回", "return",null);
        System.err.println(context);
        String express = "如果(科目.语文+科目.数学+科目.英语>270) 则 {返回 1;} 否则 {返回 0;}";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    private static void fill(DefaultContext<String, Object> context, Object project) {
        Named name = project.getClass().getAnnotation(Named.class);
        Map<String,Object> map  = new HashMap<>();
        fillProperties(map,project,name.value());

        context.put(name.value(),map);

    }

    private static void fillProperties(Map<String, Object> context, Object project, String parent) {
        for (Field field : project.getClass().getDeclaredFields()) {
            Named annotation = field.getAnnotation(Named.class);
            if (annotation!=null){
                field.setAccessible(true);
                try {
                    context.put(annotation.value(),field.get(project));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
