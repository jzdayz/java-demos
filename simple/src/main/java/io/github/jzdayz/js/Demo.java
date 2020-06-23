package io.github.jzdayz.js;

import com.google.common.collect.Maps;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Demo {

  public static void main(String[] args) throws ScriptException, NoSuchMethodException {

    ScriptEngineManager manager = new ScriptEngineManager();
    List<ScriptEngineFactory> factories = manager.getEngineFactories();
    for (ScriptEngineFactory factory : factories) {
      System.err.println(factory.getNames());
    }
    ScriptEngine engine = manager.getEngineByName("js");
    String js = "load(\"nashorn:mozilla_compat.js\");\n" +
        "importPackage('io.github.jzdayz.js');\n" +
        "\n" +
        "\n" +
        "function main(map) {\n" +
        "    var r = Demo.show(111);\n" +
        "    print(\"java invoke res -> \"+r)\n" +
        "    return map['res']\n" +
        "\n" +
        "}";
    engine.eval(js);
    Invocable invocable = (Invocable) engine;
    HashMap<Object, Object> map = Maps.newHashMap();
    map.put("res", "1111111");

    String result = (String) invocable.invokeFunction("main", map);
    System.err.println(result);
  }

  public static String show(Integer arg) {
    MysqlDataSource ds = new MysqlDataSource();
    ds.setUser("jzdayz");
    ds.setURL("jdbc:mysql://rm-bp1k3o60fe2t8nh5tqo.mysql.rds.aliyuncs.com:3306/test?useSSL=false");
    try (
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
    ) {
      statement.execute("select 1");
      System.err.println("mysql success [js]");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "java";
  }
}
