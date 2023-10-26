package top.magicpotato;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Echarts {
    static {
        String fileName;
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            // Windows操作系统
            fileName = "charming2jni.dll";
        } else if (os.startsWith("Linux")) {
            // Linux操作系统
            fileName = "libcharming2jni.so";
        } else {
            // 其他操作系统
            throw new RuntimeException("不支持的系统");
        }
        Path path = Path.of(fileName);
        try (InputStream resourceAsStream = Echarts.class.getResourceAsStream("/" + fileName); OutputStream outputStream = Files.newOutputStream(path)) {
            Objects.requireNonNull(resourceAsStream, "无法加载链接库").transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = path.toFile();
        System.out.println(file.getAbsolutePath());
        System.load(file.getAbsolutePath());
    }

    /**
     * 渲染图表到指定路径
     *
     * @param width  图表宽度 px
     * @param height 图表高度 px
     * @param path   文件生成路径，根据文件扩展名生成对应格式
     * @param data   图表渲染使用的json数据，参考echarts官网
     */
    public static native void save(int width, int height, String path, String data);

    /**
     * 渲染图表到指定路径
     *
     * @param width
     * @param height
     * @param extension
     * @param data
     * @return
     */
    public static native byte[] render(int width, int height, String extension, String data);
}
