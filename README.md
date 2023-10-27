# echarts4java

#### 介绍
使用java生成echarts图片,支持多种图片格式 jpg/png/svg 等等

#### 软件架构
基于echarts 5.2.4 图表及 deno 打包成动态链接库(win: charming2jni.dll; linux: charming2jni.so)，使用jni调用。

#### 快速开始

- clone项目，运行App#main方法;
- 目前没有找到比较好加载resources目录下链接库的方法，现在是启动时读取charming2jni.dll写入到硬盘上，然后在通过System.load进行加载。 
- 
- **注意：Echarts.java 这个类所在的包名以及里面的方法名字很重要，必须叫这个名字**
#### 使用方法
核心就是charming2jni.dll、charming2jni.so两个文件，将项目clone到本地后，把这俩文件和top.magicpotato.Echarts复制到你自己的项目中即可。

由于是基于echarts进行渲染，所以本质上和前端使用echarts画图很相似。
去[ecahrt官网](https://echarts.apache.org/examples/zh/index.html) 选一个示例,例如选择:
![img.png](https://gitee.com/ychuanl/echarts4java/raw/master/img/img.png)

接下来只需要把option这个json对象传入方法就可以了：
```java
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
Echarts.save(1000,1000,"D:/123.png",json);
```

**123.png**
![img_1.png](https://gitee.com/ychuanl/echarts4java/raw/master/img/img_1.png)