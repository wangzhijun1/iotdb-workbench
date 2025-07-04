/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.admin.service;

import org.apache.iotdb.admin.common.exception.BaseException;
import org.apache.iotdb.admin.model.entity.Connection;
import org.apache.iotdb.admin.model.vo.*;

import java.util.List;

public interface MetricsService {
  List<MetricsListDataVO> getJVMMetricsDataList(Connection connection) throws BaseException;

  List<MetricsListDataVO> getCPUMetricsDataList(Connection connection) throws BaseException;

  List<MetricsListDataVO> getMemMetricsDataList(Connection connection) throws BaseException;

  List<MetricsListDataVO> getDiskMetricsDataList(Connection connection) throws BaseException;

  List<MetricsListDataVO> getWriteMetricsDataList(Connection connection) throws BaseException;

  MetircsQueryClassificationVO getMetircsQueryClassification(Integer serverId);

  QueryInfoVO getQueryInfo(
          Integer serverId,
          Integer queryClassificationId,
          Integer pageSize,
          Integer pageNum,
          String filterString,
          String startTimeStr,
          String endTimeStr,
          Integer executionResult)
          throws BaseException;

  MetricsDataCountVO getMetricsDataCount(Integer serverId) throws BaseException;

  MetricsDataForListVO getMetricsDataForList(Integer serverId, Integer metricsType)
          throws BaseException;
}