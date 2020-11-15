各种可用的工具合集

> 工具合集引用方法

```xml
<!-- 目前版本：1.3 -->
<dependencies>
  <dependency>
    <groupId>online.yangcloud</groupId>
    <artifactId>yang-commons</artifactId>
    <version>1.3</version>
  </dependency>
</dependencies>
```

> 可用依赖

- `Hutool` - `Hutool`工具集，已集成到本依赖，无需再次添加。相关依赖可以点击<a target="_blank" href="https://hutool.cn/">官网</a>

> 更新中工具类

- `ResponseResult` - `response` 统一返回格式
```java
// 成功
static ResponseResult ok()
// 带有返回值的成功
static ResponseResult ok(Object data)
// 带有提示信息和返回值的成功
static ResponseResult ok(String msg, Object data)
// 请求执行错误
static ResponseResult errorMsg(String msg)
// token 错误
static ResponseResult errorTokenMsg(String msg)
// 自定义构建
static ResponseResult build(Integer status, String msg, Object data)
```
- `PagerHelper` - 分页工具类（链式调用）
```java
PagerHelper<User> helper = new PagerHelper<>()
    .setOffset(1)    // 页码偏移量 -> 当前页码（默认为1）
    .setCount(10)    // 每页显示的条目数（默认为10）
    .setTotal(1000L) // 数据总量 long 类型（默认为0）
    .setData(users); // 数据集，在上方创建实例时需要指定泛型
```
- `SimpleDateFormatUtil` - 线程安全下的时间日期格式化类
```java
// String 类型日期转为 Date 类型
public static Date safeParseDate(String dateStr) throws ParseException
// Date 类型日期转为 String 类型
public static String safeFormatDate(Date date)
```
- `IpUtil` - 获取客户端请求的 IP 地址
```java
// 获取请求的 IP 地址
static String getIpAddr(HttpServletRequest req)
```

> 已添加配置（无需再次手动配置）

- `CorsOrigin` - 配置跨域

> 注：具体使用方法，请查看源码，或者看上方整理的说明

个人网站：<a href="https://www.yangcloud.online/">一颗阳阳</a>