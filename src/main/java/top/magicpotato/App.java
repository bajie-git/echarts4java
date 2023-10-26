package top.magicpotato;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        // 数据说明： https://echarts.apache.org/examples/zh/editor.html?c=pie-roseType-simple
        String json = """
                {
                  legend: {
                    top: 'bottom'
                  },
                  toolbox: {
                    show: true,
                    feature: {
                      mark: { show: true },
                      dataView: { show: true, readOnly: false },
                      restore: { show: true },
                      saveAsImage: { show: true }
                    }
                  },
                  series: [
                    {
                      name: 'Nightingale Chart',
                      type: 'pie',
                      radius: [50, 250],
                      center: ['50%', '50%'],
                      roseType: 'area',
                      itemStyle: {
                        borderRadius: 8
                      },
                      data: [
                        { value: 40, name: 'rose 1' },
                        { value: 38, name: 'rose 2' },
                        { value: 32, name: 'rose 3' },
                        { value: 30, name: 'rose 4' },
                        { value: 28, name: 'rose 5' },
                        { value: 26, name: 'rose 6' },
                        { value: 22, name: 'rose 7' },
                        { value: 18, name: 'rose 8' }
                      ]
                    }
                  ]
                }
                """;

        // 生成png格式文件
        Echarts.save(1000,1000,"D:/charts.png",json);
        // 生成svg格式 byte数组
        byte[] bytes = Echarts.render(1000, 1000, "svg", json);
        Files.write(Path.of("charts.svg"), bytes);
    }
}
