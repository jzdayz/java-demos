package io.github.jzdayz.qlexpress;

import javax.tools.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptClassLoader extends ClassLoader {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> project1 = new ScriptClassLoader()
                .loadClass("Project1", ("    package io.github.jzdayz.myclass;\n" +
                        "    import io.github.jzdayz.qlexpress.Named;\n" +
                        "\n" +
                        "    @Named(\"科目\")\n" +
                        "    private static class Project{\n" +
                        "        @Named(\"语文\")\n" +
                        "        private int ywScore;\n" +
                        "        @Named(\"数学\")\n" +
                        "        private int sxScore;\n" +
                        "        @Named(\"英语\")\n" +
                        "        private int yyScore;\n" +
                        "\n" +
                        "        public int getYwScore() {\n" +
                        "            return ywScore;\n" +
                        "        }\n" +
                        "\n" +
                        "        public void setYwScore(int ywScore) {\n" +
                        "            this.ywScore = ywScore;\n" +
                        "        }\n" +
                        "\n" +
                        "        public int getSxScore() {\n" +
                        "            return sxScore;\n" +
                        "        }\n" +
                        "\n" +
                        "        public void setSxScore(int sxScore) {\n" +
                        "            this.sxScore = sxScore;\n" +
                        "        }\n" +
                        "\n" +
                        "        public int getYyScore() {\n" +
                        "            return yyScore;\n" +
                        "        }\n" +
                        "\n" +
                        "        public void setYyScore(int yyScore) {\n" +
                        "            this.yyScore = yyScore;\n" +
                        "        }\n" +
                        "    }").getBytes());

        System.out.println(project1.newInstance());
        ;
    }

    public Class<?> loadClass(String name, byte[] content) throws ClassNotFoundException {
        return defineClass(name, content, 0, content.length);
    }

    public boolean compiler(String encoding, String filePath, String outputFile,
                            DiagnosticCollector<JavaFileObject> diagnostics) throws Exception {

        String sourceDir = filePath;//java源文件存放目录
        String targetDir = "";//编译后class类文件存放目录

        // 获取编译器实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取标准文件管理器实例
        StandardJavaFileManager fileManager = compiler
                .getStandardFileManager(diagnostics, null, Charset.forName(encoding));
        try {
            //得到编译关联的jar
            List<File> jars = new ArrayList<File>();
//            getJarFiles(new File(filePath),jars);
            // 得到filePath目录下的所有java源文件
            File sourceFile = new File(sourceDir);
            List<File> sourceFileList = new ArrayList<File>();
//            getSourceFiles(sourceFile, sourceFileList);
            // 没有java文件，直接返回
            if (sourceFileList.size() == 0) {
                System.out.println(sourceDir + "目录下查找不到任何java文件");
                return false;
            }
            //加载依赖的jar文件和依赖的class文件
            List<File> dependencies = new ArrayList<File>();
            dependencies.addAll(jars);
            //dependencies.addAll(sourceFileList);
            fileManager.setLocation(StandardLocation.CLASS_PATH, dependencies);
            fileManager.setLocation(StandardLocation.SOURCE_PATH, sourceFileList);
            //编译后输出的地址
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                    Arrays.asList(new File[]{new File(outputFile)}));

            // 获取要编译的编译单元
            Iterable<? extends JavaFileObject> compilationUnits = fileManager
                    .getJavaFileObjectsFromFiles(sourceFileList);
            /**
             * 编译选项，在编译java文件时，编译程序会自动的去寻找java文件引用的其他的java源文件或者class。
             * -sourcepath选项就是定义java源文件的查找目录，有时我们编译一个Java源程式文件，而这个源程式文件需要另几个Java文件，
             *            而这些Java文件又在另外一个目录，那么这就需要为编译器指定这些文件所在的目录。
             * -classpath选项就是定义class文件的查找目录。
             * -d 是用来指定存放编译生成的.class文件的路径
             */
            //Iterable<String> options = Arrays.asList("-encoding", encoding, "-classpath", jars.toString(), "-d", targetDir, "-sourcepath", sourceDir);
            Iterable<String> options = Arrays.asList("-encoding", encoding, "-source", "1.8");
            JavaCompiler.CompilationTask compilationTask = compiler
                    .getTask(null, fileManager, diagnostics, options, null, compilationUnits);
            //运行编译任务
            return compilationTask.call();
        } finally {
            fileManager.close();
        }
    }

}
