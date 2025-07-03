package org.apache.iotdb.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.iotdb.admin.enums.IotDBEnum;
import org.apache.iotdb.admin.model.dto.MeasurementDTO;
import org.apache.tsfile.enums.TSDataType;
import org.apache.tsfile.file.metadata.enums.CompressionType;
import org.apache.tsfile.file.metadata.enums.TSEncoding;


public interface ISqlBuilderService {
    String buildListRole(String paramString);

    String buildListUserOfRole(String paramString);

    String upsertAuthorityPrivilege(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    String buildListPrivilegesOfRole(String paramString);

    String buildListPrivilegesOfUser(String paramString);

    String buildDropUser(String paramString);

    String buildDropRole(String paramString);

    String buildRevokeRoleFromUser(String paramString1, String paramString2);

    String buildGrantRoleToUser(String paramString1, String paramString2);

    String buildCreateUser(String paramString1, String paramString2);

    String buildAlterUserPassword(String paramString1, String paramString2);

    String buildCreateRole(String paramString);

    String buildGroupTtl(String paramString, long paramLong);

    String buildCancelGroupTtl(String paramString);

    String buildTTL();

    String buildShowTTL(String paramString);

    String buildShowTimeseries(String paramString, Integer paramInteger1, Integer paramInteger2);

    String buildCheckShowTimeseries(String paramString);

    String buildShowMeasurementByDevice(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, String paramString3, String paramString4, String paramString5);

    String buildDeleteTimeseries(String paramString);

    String buildDeleteTimeserieses(String paramString);

    String buildAlterTimeseriesAlias(String paramString1, String paramString2);

    String buildAlterTimeseriesDropTags(String paramString, Map<String, String> paramMap);

    String buildAlterAliasTagsAttri(String paramString1, String paramString2, Map<String, String> paramMap1, Map<String, String> paramMap2);

    String buildCreateTimeseries(String paramString1, String paramString2, TSDataType paramTSDataType, TSEncoding paramTSEncoding, CompressionType paramCompressionType, Map<String, String> paramMap1, Map<String, String> paramMap2, Map<String, String> paramMap3);

    String buildCreateAlignedTimeseries(String paramString, List<String> paramList, List<TSDataType> paramList1, List<TSEncoding> paramList2, List<CompressionType> paramList3, List<Map<String, String>> paramList4, List<Map<String, String>> paramList5);

    String buildShowDevices(String paramString, Integer paramInteger1, Integer paramInteger2);

    String buildShowDeviceKeyWord(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2);

    String buildCountDeviceKeyWord(String paramString1, String paramString2);

    String buildCheckShowDevice(String paramString);

    String buildCheckShowStorageGroup(String paramString);

    String buildDeleteStorageGroup(String paramString);

    String buildShowStorageGroup(String paramString);

    String buildCreateStorageGroup(String paramString);

    String buildShowChildNode(String paramString);

    String buildCountDevice(String paramString);

    String buildCountStorageGroup(String paramString);

    String buildCountTimeseries(String paramString);

    String buildCheckCountDevice(String paramString);

    String buildCheckCountStorageGroup(String paramString);

    String buildCheckCountTimeseries(String paramString);

    String buildCountTimeseriesPattern(String paramString);

    String buildShowVersion();

    String buildClusterDetails();

    String buildShowRegions(String paramString);

    String buildShowSchemaRegions(String paramString);

    String buildShowDataRegions(String paramString);

    String buildShowClusterParameters();

    String buildShowDataNodes();

    String buildDeleteTimeseriesData(String paramString1, String paramString2);

    String buildSelectValues(List<String> paramList, String paramString);

    String buildSelectValues(List<String> paramList, String paramString1, String paramString2, String paramString3, Boolean paramBoolean);

    String buildSelectLastValue(List<String> paramList, String paramString);

    String buildSelectCount(List<String> paramList, String paramString);

    String buildSelectLastMaxAvgMin(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

    String buildSelectLastMaxAvgMinGroup(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

    String buildSelectLimit(List<String> paramList, String paramString1, Long paramLong1, Long paramLong2, String paramString2, Boolean paramBoolean, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4);

    String buildSelectGroupBy(List<MeasurementDTO> paramList, List<String> paramList1, String paramString1, Long paramLong1, Long paramLong2, String paramString2, String paramString3, Boolean paramBoolean, String paramString4, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4);

    String buildSelectCount(List<String> paramList, String paramString, Long paramLong1, Long paramLong2, Integer paramInteger1, Integer paramInteger2);

    String buildShowType(String paramString1, String paramString2);

    String buildSelectMetricSql(List<String> paramList);

    String buildSelectMetricSql(List<String> paramList, long paramLong1, long paramLong2, String paramString, Integer paramInteger);

    String buildSelectMetricFieldColumnSql(List<String> paramList1, List<String> paramList2, long paramLong1, long paramLong2, String paramString, Integer paramInteger);

    String getMetric(String paramString, IotDBEnum paramIotDBEnum);

    String buildSelectMetricV11Sql(String paramString1, String paramString2, Integer paramInteger, IotDBEnum paramIotDBEnum);

    String buildSelectCountMetric(String paramString, long paramLong1, long paramLong2);

    String buildSelectSumAVGMetric(String paramString1, String paramString2, Date paramDate1, Date paramDate2);

    String buildSelectAuditLog(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    String buildSelectCountAuditLog(String paramString1, String paramString2, String paramString3);
}
