# 任务接口

### 创建任务

* 参数
```json
{
  "title": "任务标题（必填）",
  "type": "Integer，循环类型（必填）, 1仅一次，2每天任务，3每周任务，4每月任务",
  "description": "描述信息",
  "startTime": "开始时间",
  "endTime": "结束时间",
  "weekday": "星期几循环",
  "monthday": "每个月几号循环",
  "label": "标签"
}
```

* 成功返回
```json
{
  "code": 1,
  "msg": "成功",
  "data": "创建成功"
}
```