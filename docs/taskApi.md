# 任务接口

### 创建任务
### URL: /api/task/create
### METHOD: post

* 参数（*号为必填）

```json
{
  "title": "*任务标题",
  "description": "描述信息",
  "startDate": "*开始日期 例如：2018-12-09T16:00:000Z",
  "startTime": "*开始时间 例如：12:00",
  "label": "标签",
  "priority": "优先级"
}
```

* 成功返回

```json
{
  "code": 1,
  "msg": "任务创建成功",
  "data": null
}
```


### 删除任务
### URL: /api/task/delete
### METHOD: delete

* 参数

```json
{
  "taskId": "任务Id"
}
```

* 成功返回

```json
{
  "code": 1,
  "msg": "任务删除成功",
  "data": null
}
```

### 修改任务
### URL: /api/task/modify
### METHOD: put

* 参数

```json
{
  "taskId": "任务ID",
  "title": "*任务标题",
  "description": "描述信息",
  "startDate": "*开始日期",
  "startTime": "*开始时间",
  "label": "标签",
  "priority": "优先级"
}
```

* 成功返回

```json
{
  "code": 1,
  "msg": "任务修改成功",
  "data": null
}
```

### 获取任务列表
### URL: /api/task/list
### METHOD: get

* 成功返回

```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "taskId": "任务Id",
      "title": "任务标题",
      "description": "任务描述",
      "type": "Integer，循环类型（必填）, 1仅一次，2每天任务，3每周任务，4每月任务",
      "isfinish": "是否完成，1完成，0未完成",
      "finishTime": "完成时间",
      "startTime": "开始时间",
      "endTime": "结束时间",
      "createTime": "创建时间",
      "updateTime": "更新时间",
      "weekday": ["星期几循环"],
      "label": "标签"
    }
  ]
}
```

### 获取当天任务
### URL: /api/task/today
### METHOD: get

* 成功返回

```json
{
  "code": 1,
  "msg": "成功",
  "data": [
    {
      "taskId": "任务Id",
      "title": "任务标题",
      "description": "任务描述",
      "type": "Integer，循环类型（必填）, 1仅一次，2每天任务，3每周任务，4每月任务",
      "isfinish": "是否完成，1完成，0未完成",
      "finishTime": "完成时间",
      "startTime": "开始时间",
      "endTime": "结束时间",
      "createTime": "创建时间",
      "updateTime": "更新时间",
      "weekday": ["星期几循环"],
      "label": "标签"
    }
  ]
}
```

### 取消完成任务
### URL: /api/task/unfinish
### METHOD: post

* 参数

```json
{
  "taskId": "任务ID"
}
```

* 成功返回

```json
{
  "code": 1,
  "msg": "成功",
  "data": "取消完成"
}
```
