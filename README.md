# echarts4java

#### 介绍
使用java生成echarts图片,支持多种图片格式 jpg/png/svg 等等

**有问题请提issues我会积极解决**
**目前动态链接库仅支持X86架构**

#### 软件架构
基于echarts 5.5.1 图表及 deno 打包成动态链接库(win: charming2jni.dll; linux: charming2jni.so; macos: libcharming2jni.dylib)，使用jni调用。

#### 快速开始

- clone项目，运行App#main方法;
- 目前没有找到比较好加载resources目录下链接库的方法，现在是启动时读取charming2jni.dll写入到硬盘上，然后在通过System.load进行加载。如果你有更好的办法欢迎讨论 
- 
- **注意：Echarts.java 这个类所在的包名以及里面的方法名字很重要，必须叫这个名字**
#### 使用方法
核心就是charming2jni.dll、charming2jni.so、libcharming2jni.dylib，将项目clone到本地后，把这三个链接库文件和top.magicpotato.Echarts复制到你自己的项目中即可。

由于是基于echarts进行渲染，所以本质上和前端使用echarts画图很相似。
去[ecahrt官网](https://echarts.apache.org/examples/zh/index.html) 选一个示例,例如选择:
![img.png](https://gitee.com/ychuanl/echarts4java/raw/master/img/img.png)

接下来只需要把option这个json对象传入方法就可以了：
```java
// 数据说明： https://echarts.apache.org/examples/zh/editor.html?c=pie-roseType-simple
// 注意必须是json字符串，key必须有双引号，不是json对象
String json = """
              {
                "legend": {
                    "top": "bottom"
                },
                "toolbox": {
                    "show": true,
                    "feature": {
                        "mark": {
                            "show": true
                        },
                        "dataView": {
                            "show": true,
                            "readOnly": false
                        },
                        "restore": {
                            "show": true
                        },
                        "saveAsImage": {
                            "show": true
                        }
                    }
                },
                "series": [{
                    "name": "Nightingale Chart",
                    "type": "pie",
                    "radius": [50, 250],
                    "center": ["50%", "50%"],
                    "roseType": "area",
                    "itemStyle": {
                        "borderRadius": 8
                    },
                    "data": [{
                        "value": 40,
                        "name": "rose 1"
                    }, {
                        "value": 38,
                        "name": "rose 2"
                    }, {
                        "value": 32,
                        "name": "rose 3"
                    }, {
                        "value": 30,
                        "name": "rose 4"
                    }, {
                        "value": 28,
                        "name": "rose 5"
                    }, {
                        "value": 26,
                        "name": "rose 6"
                    }, {
                        "value": 22,
                        "name": "rose 7"
                    }, {
                        "value": 18,
                        "name": "rose 8"
                    }]
                }]
              }
        """;

// 生成png格式文件
Echarts.save(1000,1000,"D:/123.png",json);
```

**123.png**
![img_1.png](https://gitee.com/ychuanl/echarts4java/raw/master/img/img_1.png)


#### 下一步
1. 增加arm架构支持
2. ~~公开链接库源码~~ [这里](https://github.com/bajie-git/charming2jni)
3. ~~更新echarts到5.5.1~~
4. 增加可执行文件，允许通过命令行调用


#### FAQ

##### Glibc 版本  

libc6 2.35 

如果你得版本不匹配可以拉取链接库源码自行编译（编译之后的版本欢迎pr回来）

##### 中文乱码  

1. 原因是缺少中文字体，可以将windows上的中文字体复制到 /usr/share/fonts/truetype/ 目录下,然后分别执行 mkfontscale、mkfontdir、fc-cache -fv 更新字体缓存。
2. 或者直接解压项目中fonts.zip覆盖 /usr/share/fonts 目录，然后分别执行 mkfontscale、mkfontdir、fc-cache -fv 更新字体缓存。**fonts.zip 里面是我打包好的字体，适合大多数场景。**
```shell
# 如果提示 mkfontscale: command not found
# 在Ubuntu下运行如下命令
# sudo apt-get install ttf-mscorefonts-installer
# 在cent os下运行如下命令
# yum install mkfontscale 
mkfontscale
mkfontdir

# 如果提示 fc-cache: command not found
# 在Ubuntu下运行如下命令
# sudo apt-get install fontconfig
# 在cent os下运行如下命令
# yum install fontconfig
fc-cache -fv 
```

##### 字体不清晰
参考[此处](https://gitee.com/ychuanl/echarts4java/issues/IC5SD1)