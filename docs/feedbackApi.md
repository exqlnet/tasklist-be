# 反馈接口

### 提交反馈
### URL: /api/feedback/submit
### METHOD: post

* 提交参数

```json
{
  "type": "反馈类型，直接填文本",
  "content": "反馈内容，文本"
}
``` 

* 成功返回

```json
{
  "code": 1,
  "msg": "成功",
  "data": "反馈成功"
}
```