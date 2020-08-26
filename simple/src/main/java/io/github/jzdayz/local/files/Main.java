package io.github.jzdayz.local.files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class Main {

    private static final Pattern P1 =
            Pattern.compile("[\\s\\S]*(学历|毕业)[\\s\\S]*");
    private static final Pattern P2 =
            Pattern.compile("[\\s\\S]*(学位)[\\s\\S]*");
    private static final Pattern P3 =
            Pattern.compile("[\\s\\S]*(健康状况|资格|考试|等级|技术|培训|英语|辅修|承诺书)[\\s\\S]*");
    private static final Pattern P4 =
            Pattern.compile("[\\s\\S]*(身份)[\\s\\S]*");
    private static final Pattern P5 =
            Pattern.compile("[\\s\\S]*(离职)[\\s\\S]*");
    private static final Pattern P6 =
            Pattern.compile("[\\s\\S]*(劳动)[\\s\\S]*");

    private static final Pattern ALL =
            Pattern.compile("[\\s\\S]*");
    static List<File> noHandler = new ArrayList<>();
    static List<FileCopy> cpHandler = new ArrayList<>();
    static String outPath = "/Users/huqingfeng/Downloads/5_test";
    static String inPath = "/Users/huqingfeng/Downloads/5月三证资料";

    public static void main(String[] args) throws Exception {
        files(new File(inPath), noHandler);
        cpHandler.addAll(
                noHandler.stream().map(f ->
                        FileCopy.builder().file(f).s(S.PHOTO).build()
                ).collect(Collectors.toList())
        );
        dispatcherDir();
    }

    private static void dispatcherDir() {
        cpHandler.forEach(fc -> {
            File fcF = new File(outPath + File.separator + fc.getS().desc);
            ckCreate(fcF);
            check(fcF.toString() + File.separator + fc.file.getName());
            try {
                Files
                        .copy(fc.file.toPath(), Paths.get(fcF.toString() + File.separator + fc.file.getName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void check(String f) {
        if (new File(f).exists()) {
            throw new RuntimeException(" same file ");
        }
    }

    private static void ckCreate(File file) {
        try {
            if (!file.exists() && file.mkdirs()) {
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void files(File file, List<File> noHandler) {
        if (file.isDirectory()) {
            for (File fileL : Objects.requireNonNull(file.listFiles((dir, name) -> {
                if (name.startsWith(".") || name.equals("Thumbs.db")) {
                    log.warn("hiding file : {}", name);
                    return false;
                }
                return true;
            }))) {
                files(fileL, noHandler);
            }
            return;
        }
        if (!dispatcher(file)) {
            noHandler.add(file);
        }
    }

    private static boolean dispatcher(File file) {
        for (S s : S.values()) {
            String name = file.getName();
            if (s.pattern.matcher(name).matches()) {
                cpHandler.add(FileCopy.builder().file(file).s(s).build());
                return true;
            }
        }
        return false;
    }

    public enum S {
        //
        ACADEMIC(P1, "AttachType_1", "学历"),
        //
        DEGREE(P2, "AttachType_2", "学位"),
        //
        CERTIFICATION(P3, "AttachType_5", "资格证"),
        //
        ID(P4, "AttachType_9", "身份证"),
        //
        DIMISSION(P5, "AttachType_11", "离职证明"),
        //
        LABOUR(P6, "AttachType_3", "劳动合同"),
        // 最后一个
        PHOTO(ALL, "AttachType_6", "照片");

        private Pattern pattern;
        private String type;
        private String desc;

        S(Pattern pattern, String type, String desc) {
            this.pattern = pattern;
            this.type = type;
            this.desc = desc;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class FileCopy {

        private File file;
        private S s;

        @Override
        public String toString() {
            return "FileCopy{" +
                    "file=" + file +
                    '}';
        }
    }

}
