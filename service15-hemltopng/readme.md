# 将Html代码转化为图片格式（PNG、JPG）
+ 接口url
~~~bash
 /toImageService/toImage
~~~
+ 参数类型
~~~
htmlStr： String -> Html代码
path： String -> 图片保存路径+文件名称 如 E:\\1.png
~~~

+ 将本地图片，通过流的形式，在网页中显示出来，这样就可以达到Url中显示图片
效果