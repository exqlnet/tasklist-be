# An application of simple task manager
### 环境
* Linux/Windows
* Jdk 1.8
* Spring Boot 2.1.1
* Spring Data
* Maven 4.0.0
* Docker/docker-compose

### 下载和部署

* 下载项目代码
    
```
git clone https://github.com/exqlnet/tasklist-be
```
    
* 安装docker/docker-compose
    
* 启动
    
```
docker-compose up
```

或

```
docker-compose up -d
```
默认监听127.0.0.1:6789

### TODO
* 统一异常处理/优化异常处理
* 创建匿名用户类
* 松耦合: 优化Controller/Service
* 使用Redis解决验证码储存问题
* ...