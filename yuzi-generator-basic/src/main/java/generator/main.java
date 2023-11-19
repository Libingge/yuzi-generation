package generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class main {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String inputPath = projectPath +File.separator + "yuzi-generator-demo-projects" + File.separator + "acm-template";
        String outputPath = projectPath + File.separator + "yuzi-generator-basic" +  File.separator;
        copyFilesByRecursive(inputPath,outputPath);
    }

    /**
     *用于复制文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    private static void copyFilesByRecursive(String inputPath,String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile,outputFile);
        } catch (IOException e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    private static void copyFileByRecursive(File inputFile,File outputFile) throws IOException {
        if (inputFile.isDirectory()){

            File destOutputFile = new File(outputFile, inputFile.getName());
            if (!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            File[] files = inputFile.listFiles();
            if (ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                copyFileByRecursive(file,destOutputFile);
            }
        }else {
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
