# Applet_Schedule
通过模拟登录爬取获取学校教务系统课表等信息

通过HttpClient实现模拟登录，验证码识别可以自己通过第三方的平台调用API接口实现即可。

通过解析请求获取响应返回的json数据，进而去解析json格式的字符串，转化为持久层的对象。