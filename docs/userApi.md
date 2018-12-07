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
