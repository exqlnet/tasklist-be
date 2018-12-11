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
      "code": 1,
      "msg": "成功",
      "data": {
        "token": "Token"
      }
    }
    ```
    * 失败
    ```json
    {
        "code": 0,
        "msg": "失败",
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
  "code": 1,
  "msg": "成功",
  "data": "ok"
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
  "code": 1,
  "msg": "成功",
  "data": "ok"
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
  "code": 1,
  "msg": "成功",
  "data": "用户存在"
}
```

* 失败返回
```json
{
  "code": 0,
  "msg": "失败",
  "data": "未找到用户"
}
```


### 忘记密码
### URL: /api/user/forget
### METHOD: post
和注册一样，先通过/api/user/sendVcode 接口发送验证码
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
  "code": 1,
  "msg": "成功",
  "data": "密码重置成功"
}
```

* 失败返回

```json
{
  "code": 0,
  "msg": "失败",
  "data": "用户未找到，请检查邮箱是否正确。"
}
```
```json
{
  "code": 0,
  "msg": "失败",
  "data": "验证码错误"
}
```
