# Spring Cloud Gateway

## 1.路由配置
 - routes
   - 
   - *id*
     - route唯一标识 
     
   - *uri*
     - 目标地址，也就是转发服务地址
       - http模式 http://www.csdn.cn
       - websocket模式 http://www.csdn.cn
       - 注册中心服务模式 lb://ruoyi-system  已注册服务名称，并且该模式可负载均衡
       
   - *predicates*
     - 断言，判断该请求是否需要转发
     - Gateway常见断言
       - Path 例如 Path=/system/**  请求路径是否包含system
       - After、Before、Between 例如 After=2021-07-04T19:16:43.338+08:00[Asia/Shanghai] 时间在此之后的请求
       Before、Between也是时间相关处理
       - Cookie 例如 Cookie=username, ybz  判断Cookie中是否有key为username，value为ybz的内容
       - Header 例如 Header=X-Request-Id, \d+，这个路由规则匹配Header中包含X-Request-Id并且值为纯数字的请求
       - Host 例如 Host=**.ybz.com ，标识请求域名需要包含**.ybz.com
       - Method 例如 Method=GET,POST，只处理GET、POST请求
       - Query 例如 Query=red, gree. 匹配请求参数，请求中包含red参数，并且值为green或greet等能够匹配gree.规则的请求，判断匹配成功
     
   - *filters*
     - 过滤器，分为网关配置过滤，自定义全局过滤
     - AddRequestHeader=X-Request-Foo, Bar，在filters中添加该配置，会在请求头上添加名称为X-Request-Foo值为Bar的键值对
     - 自定义全局过滤，需要创建过滤器并实现GlobalFilter，Ordered两个接口并重写方法
     - 自定义局部过滤，需要创建过滤器并继承AbstractGatewayFilterFactory并重写方法

## 2.网关鉴权

> 常见自定义过滤器实现GlobalFilter, Ordered，Ordered是为了指定执行的顺序优先级，
在自定义过滤器需要重写filter方法，方法参数有ServerWebExchange、GatewayFilterChain
分别为服务网络交换器包含了请求的具体数据、网关过滤责任链包含了下一个需要执行的过滤器
 >> 实现后可以获取请求参数，做鉴权具体处理，判断请求的Token是否有效、登录状态等，有问题的话抛出异常，
没问题就执行下一个过滤器

## 3.网关限流 依赖Redis
> 在配置文件中 filters位置下新增RequestRateLimiterGatewayFilterFactory过滤器工厂
>> name: RequestRateLimiter <br>
args:<br>
redis-rate-limiter.replenishRate: 1 # 令牌桶每秒填充速率<br>
redis-rate-limiter.burstCapacity: 2 # 令牌桶总容量<br>
key-resolver: "#{@pathKeyResolver}" # 使用 SpEL 表达式按名称引用 bean

### 3.1网关集成Sentinel限流
> 创建GatewayConfig、限流异常处理器(SentinelFallbackHandler)，在GatewayConfig中初始化Bean  -> SentinelFallbackHandler
>> 可以在Config中初始化网关限流规则、限流分组等
><br> 也可以在json文件中定义限流规则，配置保存在配置中心 




   