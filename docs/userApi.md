#用户接口

### 登陆

### URL: /api/user/login

* 参数

```json
{
  "email": "邮箱",
  "password": "密码"
}
```

* 返回
    * 成功
    
    ```json
    {
      "status": 1,
      "message": "成功",
      "data": "Token"
    }
    ```
    
    * 失败
    ```json
    {
        "status": 0,
        "message": "失败",
        "data": "密码错误"
    }
    
    ```


### 注册
### 发送邮件验证码
### URL: /api/user/sendVcode

* 参数

```json
{
  "email": "邮箱地址"
}

```
* 成功返回

```json
{
  "status": 1,
  "message": "发送成功",
  "data": null
}
```

### 注册
### URL: /api/user/register
### METHOD: post

* 参数

```json
{
  "email": "邮箱",
  "password": "密码",
  "verifyCode": "验证码"
}
```

* 成功返回
```json
{
  "status": 1,
  "message": "注册成功",
  "data": null
}
```

### 检查用户是否存在
### URL: /api/user/exist
### METHOD: get

* 参数

```json
{
  "email": "用户的邮箱地址"
}
```
* 成功返回

```json
{
  "status": 1,
  "message": "成功",
  "data": "用户存在"
}
```

* 失败返回
```json
{
  "status": 0,
  "message": "失败",
  "data": "未找到用户"
}
```


### 忘记密码
### URL: /api/user/forget
### METHOD: post

和注册一样，先通过/api/user/sendVstatus 接口发送验证码

* 参数
```json
{
  "email": "邮箱地址",
  "verifyCode": "验证码",
  "newPassword": "新密码"
}
```

* 成功返回

```json
{
  "status": 1,
  "message": "密码重置成功",
  "data": null
}
```

* 失败返回

```json
{
  "status": 0,
  "message": "用户未找到",
  "data": null
}
```

```json
{
  "status": 0,
  "message": "邮箱验证失败",
  "data": null
}
```

### 获取用户信息
### url: /api/user/info
### method: GET
### requirement: 带Token

* 成功返回

```
{
    "status": 1,
    "message": "获取成功",
    "data": {
        "userId": "用户id",
        "email": "用户邮箱",
        "description": "用户描述信息",
        "avatarUrl": "头像链接(暂时null)"
    }
}
```
