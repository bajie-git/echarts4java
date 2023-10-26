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
        Path path = Path.of("charming2jni.dll");
        try (InputStream resourceAsStream = Echarts.class.getResourceAsStream("/charming2jni.dll"); OutputStream os = Files.newOutputStream(path)) {
            Objects.requireNonNull(resourceAsStream,"无法加载链接库").transferTo(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = path.toFile();
        System.out.println(file.getAbsolutePath());
        System.load(file.getAbsolutePath());
    }

    /**
     * 渲染图表到指定路径
     * @param width 图表宽度 px
     * @param height 图表高度 px
     * @param path 文件生成路径，根据文件扩展名生成对应格式
     * @param data 图表渲染使用的json数据，参考echarts官网
     */
    public static native void save(int width, int height, String path, String data);

    /**
     * 渲染图表到指定路径
     * @param width
     * @param height
     * @param extension
     * @param data
     * @return
     */
    public static native byte[] render(int width, int height, String extension, String data);
}
