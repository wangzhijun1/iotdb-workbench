<!--

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

-->

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
![](https://img.shields.io/badge/java--language-1.8-blue.svg)

# IoTDB-Workbench

1.修改官方版本sqlite数据库为mysql

2.升级jar包版本为iotdb1.3.3

3.修改一下运行错误

## 后端服务运行

[后端服务设计及运行说明](backend/README.md)

## 前端服务运行

[前端服务运行说明](frontend/README.md)

## Casdoor运行

[Casdoor单点登录运行说明](casdoor.md)


## Docker

1、构建镜像:将前后后端分别打包构建出target和dist目录，然后在对应的目录下执行命令(当docker hub 有下面镜像时可以省去构建镜像步骤)

```shell script
  cd bacnkend
  docker build -t apache/iotdb-web-workbench:0.13.0-backend .
  cd frontend
  docker build -t apache/iotdb-web-frontend:0.13.0-frontend .
```

2、将backend/resources/sqlite目录下的iotdb拷贝到你需要挂载的路径，如/data/iotdb.db

3、在根目录下执行

`docker-compose up -d`

> 注意 docker-compose.yml中volumes挂载路径为步骤2中所指定路径；PORTS和你后端端口的值一样。
