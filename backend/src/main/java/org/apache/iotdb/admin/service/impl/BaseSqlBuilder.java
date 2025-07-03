
package org.apache.iotdb.admin.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.iotdb.admin.constants.CommonConstant;
import org.apache.iotdb.admin.enums.IotDBEnum;
import org.apache.iotdb.admin.model.dto.MeasurementDTO;
import org.apache.iotdb.admin.service.ISqlBuilderService;
import org.apache.iotdb.admin.utils.TsDataTypeSwitchUtils;
import org.apache.tsfile.enums.TSDataType;
import org.apache.tsfile.file.metadata.enums.CompressionType;
import org.apache.tsfile.file.metadata.enums.TSEncoding;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class BaseSqlBuilder implements ISqlBuilderService {
    private static final String CREATE_TS_PREFIX = "CREATE TIMESERIES ";

    private static final String CREATE_ATS_PREFIX = "CREATE ALIGNED TIMESERIES ";

    private static final String DELETE_TS_PREFIX = "DELETE TIMESERIES ";

    private static final String SHOW_TS_PREFIX = "SHOW TIMESERIES ";

    public String buildListRole(String userName) {
        if (StringUtils.isNotBlank(userName))
            return "list all role of user " + userName;
        return "list role";
    }

    public String buildListUserOfRole(String roleName) {
        if (StringUtils.isNotBlank(roleName))
            return "list all user of role " + roleName;
        return "list user";
    }

    public String upsertAuthorityPrivilege(String operationType, String userOrRole, String name, String privilegesStr, String path) {
        return operationType + " " + userOrRole + " " + name + " privileges " + privilegesStr + " on " + path;
    }

    public String buildListPrivilegesOfRole(String roleName) {
        return "list role privileges " + roleName;
    }

    public String buildListPrivilegesOfUser(String userName) {
        return "list user privileges " + userName;
    }

    public String buildDropUser(String userName) {
        return "drop user " + userName;
    }

    public String buildDropRole(String roleName) {
        return "drop role " + roleName;
    }

    public String buildRevokeRoleFromUser(String roleName, String userName) {
        return "revoke " + roleName + " from " + userName;
    }

    public String buildGrantRoleToUser(String roleName, String userName) {
        return "grant " + roleName + " to " + userName;
    }

    public String buildCreateUser(String userName, String password) {
        return "create user " + userName + "'" + password + "'";
    }

    public String buildAlterUserPassword(String userName, String newPassword) {
        return "alter user " + userName + " set password '" + newPassword + "'";
    }

    public String buildCreateRole(String roleName) {
        return "create role " + roleName;
    }

    public String buildGroupTtl(String groupName, long ttl) {
        return "set ttl to " + groupName + " " + ttl;
    }

    public String buildCancelGroupTtl(String groupName) {
        return "unset ttl to " + groupName;
    }

    public String buildTTL() {
        return "ttl";
    }

    public String buildShowTTL(String groupName) {
        return "show ttl on " + groupName;
    }

    public String buildShowTimeseries(String path, Integer pageNum, Integer pageSize) {
        String sql = "show timeseries ";
        if (StringUtils.isNotBlank(path)) {
            sql = sql + path + ".** ";
            if (!ObjectUtils.isEmpty(pageNum))
                sql = sql + " limit " + pageSize + " offset " + (pageSize.intValue() * (pageNum.intValue() - 1));
        }
        return sql;
    }

    public String buildCheckShowTimeseries(String path) {
        return "show timeseries " + path;
    }

    public String buildShowMeasurementByDevice(String sqlCount, String formatPath, Integer pageNum, Integer pageSize, String keyword, String tagKey, String tagValue) {
        String queryShowSql = "show timeseries ";
        int sqlCountSize = -1;
        if (StringUtils.isNotBlank(keyword)) {
            queryShowSql = queryShowSql + keyword;
            if (StringUtils.isNotBlank(tagKey) &&
                    StringUtils.isNotBlank(tagValue)) {
                queryShowSql = queryShowSql + " where " + tagKey + " = " + tagValue;
            } else {
                int pageStart = (pageNum.intValue() == 1) ? 0 : ((pageNum.intValue() - 1) * pageSize.intValue());
                queryShowSql = queryShowSql + " limit " + pageSize + " offset " + pageStart;
            }
        } else if (StringUtils.isNotBlank(tagKey) &&
                StringUtils.isNotBlank(tagValue)) {
            queryShowSql = queryShowSql + " where " + tagKey + " = " + tagValue;
        } else if (StringUtils.isNotBlank(sqlCount)) {
            queryShowSql = queryShowSql + formatPath + ".**";
            sqlCountSize = Integer.parseInt(sqlCount);
            int pageStart = (pageNum.intValue() == 1) ? 0 : ((pageNum.intValue() - 1) * pageSize.intValue());
            if (sqlCountSize > pageStart)
                queryShowSql = queryShowSql + " limit " + pageSize + " offset " + pageStart;
        }
        return queryShowSql;
    }

    public String buildDeleteTimeseries(String path) {
        if (StringUtils.isNotBlank(path))
            return "delete timeseries " + path;
        return "";
    }

    public String buildDeleteTimeserieses(String path) {
        if (StringUtils.isNotBlank(path))
            return "delete timeseries " + path + ".**";
        return "";
    }

    public String buildAlterTimeseriesAlias(String timeseries, String alias) {
        return "alter timeseries " + timeseries + " upsert alias=" + alias;
    }

    public String buildAlterTimeseriesDropTags(String timeseries, Map<String, String> dropTags) {
        return "alter timeseries " + timeseries + " drop " + String.join(",", dropTags.keySet());
    }

    public String buildAlterAliasTagsAttri(String timeseries, String alias, Map<String, String> tags, Map<String, String> attributes) {
        String sql = "ALTER timeseries " + timeseries + " UPSERT";
        if (!StringUtils.isEmpty(alias) && !alias.equals("null"))
            sql = sql + " ALIAS=" + alias;
        if (!MapUtils.isEmpty(tags)) {
            sql = sql + " TAGS(";
            for (String tag : tags.keySet())
                sql = sql + tag + "=" + (String)tags.get(tag) + ",";
            sql = sql.substring(0, sql.length() - 1);
            sql = sql + ")";
        }
        if (!MapUtils.isEmpty(attributes)) {
            sql = sql + " ATTRIBUTES(";
            for (String attribute : attributes.keySet())
                sql = sql + attribute + "=" + (String)attributes.get(attribute) + ",";
            sql = sql.substring(0, sql.length() - 1);
            sql = sql + ")";
        }
        return sql;
    }

    @Override
    public String buildCreateTimeseries(String paramString1, String paramString2, TSDataType paramTSDataType, TSEncoding paramTSEncoding, CompressionType paramCompressionType, Map<String, String> paramMap1, Map<String, String> paramMap2, Map<String, String> paramMap3) {
        return null;
    }

    public String buildCreateAlignedTimeseries(String deviceName, List<String> paths, List<TSDataType> dataTypes, List<TSEncoding> encodings, List<CompressionType> compressors, List<Map<String, String>> tagsList, List<Map<String, String>> attributesList) {
        StringBuffer sql = (new StringBuffer("CREATE ALIGNED TIMESERIES ")).append(deviceName).append("(");
        for (int i = 0; i < paths.size(); i++) {
            sql.append(paths.get(i))
                    .append(" ")
                    .append(((TSDataType)dataTypes.get(i)).name())
                    .append(" ENCODING=")
                    .append(((TSEncoding)encodings.get(i)).name());
            CompressionType compressor = compressors.get(i);
            if (!compressor.equals(CompressionType.UNCOMPRESSED))
                sql.append(" COMPRESSOR=").append(compressor.name());
            Map<String, String> tags = tagsList.get(i);
            if (!MapUtils.isEmpty(tags)) {
                List<String> list = new ArrayList<>(tags.keySet());
                sql.append(" tags(");
                for (int j = 0; j < list.size(); j++) {
                    String key = list.get(j);
                    sql.append(key + "=" + (String)tags.get(key));
                    if (j < list.size() - 1)
                        sql.append(",");
                }
                sql.append(")");
            }
            sql.append(" ");
            Map<String, String> attributes = attributesList.get(i);
            if (!MapUtils.isEmpty(attributes)) {
                List<String> list = new ArrayList<>(attributes.keySet());
                sql.append(" attributes(");
                for (int j = 0; j < list.size(); j++) {
                    String key = list.get(j);
                    sql.append(key + "=" + (String)attributes.get(key));
                    if (j < list.size() - 1)
                        sql.append(",");
                }
                sql.append(")");
            }
            if (i < paths.size() - 1)
                sql.append(",");
        }
        sql.append(")");
        return sql.toString();
    }

    public String buildShowDevices(String path, Integer pageNum, Integer pageSize) {
        if (StringUtils.isNotBlank(path))
            return "show devices " + path + ".** limit " + pageSize + " offset " + ((

                    (pageNum.intValue() > 1) ? (pageNum.intValue() - 1) : 0) * pageSize.intValue());
        return "show devices limit " + pageSize + " offset " + ((pageNum.intValue() - 1) * pageSize.intValue());
    }

    public String buildShowDeviceKeyWord(String pathName, String keyword, Integer pageNum, Integer pageSize) {
        return "show devices " + pathName + ".*" + keyword + "*  limit " + pageSize + " offset " + ((pageNum

                .intValue() - 1) * pageSize.intValue());
    }

    public String buildCountDeviceKeyWord(String pathName, String keyword) {
        return "count devices " + pathName + ".*" + keyword + "* ";
    }

    public String buildCheckShowDevice(String path) {
        if (StringUtils.isNotBlank(path))
            return "show devices " + path;
        return "show devices";
    }

    public String buildCheckShowStorageGroup(String path) {
        if (StringUtils.isNotBlank(path))
            return "show storage group " + path;
        return "show storage group";
    }

    public String buildDeleteStorageGroup(String groupName) {
        return "delete storage group " + groupName;
    }

    public String buildShowStorageGroup(String path) {
        if (StringUtils.isNotBlank(path))
            return "show storage group " + path + ".**";
        return "show storage group";
    }

    public String buildCreateStorageGroup(String groupName) {
        return "create storage group " + groupName;
    }

    public String buildShowChildNode(String formatPath) {
        return "show child nodes " + formatPath;
    }

    public String buildCountDevice(String path) {
        if (StringUtils.isNotBlank(path)) {
            if (path.endsWith(".**"))
                return "count devices " + path.substring(0, path.lastIndexOf(".**"));
            return "count devices " + path + ".**";
        }
        return "count devices";
    }

    public String buildCountStorageGroup(String path) {
        if (StringUtils.isNotBlank(path)) {
            if (path.endsWith(".**"))
                return "count storage group " + path.substring(0, path.lastIndexOf(".**"));
            return "count storage group " + path;
        }
        return "count storage group";
    }

    public String buildCountTimeseries(String path) {
        if (StringUtils.isNotBlank(path)) {
            if (path.endsWith(".**"))
                return "count timeseries " + path;
            return "count timeseries " + path + ".**";
        }
        return "count timeseries";
    }

    public String buildCheckCountDevice(String path) {
        return "count devices " + path;
    }

    public String buildCheckCountStorageGroup(String path) {
        return "count storage group " + path;
    }

    public String buildCheckCountTimeseries(String path) {
        return "count timeseries " + path;
    }

    public String buildCountTimeseriesPattern(String path) {
        if (StringUtils.isNotBlank(path))
            return "count timeseries " + path + ".**";
        return "count timeseries";
    }

    public String buildShowVersion() {
        return "show version";
    }

    public String buildClusterDetails() {
        return "show cluster details";
    }

    public String buildShowRegions(String path) {
        if (StringUtils.isNotBlank(path))
            return "show regions of storage group " + path;
        return "show regions of storage group";
    }

    public String buildShowSchemaRegions(String path) {
        if (StringUtils.isNotBlank(path))
            return "show schema regions of storage group " + path;
        return "show schema regions of storage group";
    }

    public String buildShowDataRegions(String path) {
        if (StringUtils.isNotBlank(path))
            return "show data regions  of  storage group " + path;
        return "show data regions  of  storage group";
    }

    public String buildShowClusterParameters() {
        return "show variables ";
    }

    public String buildShowDataNodes() {
        return "show datanodes";
    }

    public String buildDeleteTimeseriesData(String timeseries, String timestamp) {
        return "delete from " + timeseries + " where time=" + timestamp;
    }

    public String buildSelectValues(List<String> series, String path) {
        String sql = "select ";
        for (String ser : series)
            sql = sql + ser + ",";
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " from " + path + " order by time desc limit 200";
        return sql;
    }

    public String buildSelectValues(List<String> series, String path, String startTime, String endTime, Boolean ascOrDesc) {
        String sql = "select ";
        for (String ser : series)
            sql = sql + ser + ",";
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " from " + path;
        if (StringUtils.isNotBlank(startTime)) {
            sql = sql + " where time >= " + startTime;
            if (StringUtils.isNotBlank(endTime))
                sql = sql + " and time <= " + endTime;
        } else if (StringUtils.isNotBlank(endTime)) {
            sql = sql + " where time <= " + endTime;
        }
        sql = sql + " order by time ";
        if (ascOrDesc.booleanValue()) {
            sql = sql + " asc ";
        } else {
            sql = sql + " desc ";
        }
        return sql;
    }

    public String buildSelectLastValue(List<String> series, String path) {
        if (!CollectionUtils.isEmpty(series)) {
            String sql = "select ";
            for (String value : series)
                sql = sql + "LAST( " + value + "),";
            sql = sql.substring(0, sql.length() - 1);
            sql = sql + " from " + path;
            return sql;
        }
        return "";
    }

    public String buildSelectCount(List<String> series, String path) {
        if (!CollectionUtils.isEmpty(series)) {
            String sql = "select ";
            for (String value : series)
                sql = sql + "count(" + value + "),";
            sql = sql.substring(0, sql.length() - 1);
            sql = sql + " from " + path;
            return sql;
        }
        return "";
    }

    public String buildSelectLastMaxAvgMin(String timeseriesLeaf, String deviceName, String startTime, String endTime, String limit) {
        String sql = "select ";
        sql = sql + "LAST_VALUE(" + timeseriesLeaf + "),MAX_VALUE(" + timeseriesLeaf + "),AVG(" + timeseriesLeaf + "),MIN_VALUE(" + timeseriesLeaf + ") from " + deviceName + " ";
        if (StringUtils.isNotBlank(startTime)) {
            sql = sql + " where time >= " + startTime;
            if (StringUtils.isNotBlank(endTime))
                sql = sql + " and time <= " + endTime;
        } else if (StringUtils.isNotBlank(endTime)) {
            sql = sql + " where time <= " + endTime;
        }
        sql = sql + " order by time desc  limit " + limit;
        return sql;
    }

    public String buildSelectLastMaxAvgMinGroup(String timeseriesLeaf, String deviceName, String startTime, String endTime, String timeUnitInterval, String limit) {
        String sql = "select ";
        sql = sql + "LAST_VALUE(" + timeseriesLeaf + "),MAX_VALUE(" + timeseriesLeaf + "),AVG(" + timeseriesLeaf + "),MIN_VALUE(" + timeseriesLeaf + ") from " + deviceName + " ";
        if (startTime != null && endTime != null)
            sql = sql + " group by ([" + startTime + "," + endTime + ")," + timeUnitInterval + ")";
        return sql + " order by time desc limit " + limit;
    }

    public String buildSelectLimit(List<String> newMeasurementList, String deviceName, Long startTime, Long endTime, String orderByDesc, Boolean ascOrDesc, Integer limitSize, Integer offsetSize, Integer slimitSize, Integer soffsetSize) {
        String sql = "select " + String.join(",", (Iterable)newMeasurementList) + " from " + deviceName;
        if (startTime != null && endTime != null) {
            sql = sql + " where time >= " + startTime + " and time <= " + endTime;
        } else if (startTime == null && endTime != null) {
            sql = sql + " where time <= " + endTime;
        } else if (startTime != null && endTime == null) {
            sql = sql + " where time >= " + startTime;
        }
        sql = sql + " order by " + orderByDesc;
        if (ascOrDesc.booleanValue()) {
            sql = sql + " asc ";
        } else {
            sql = sql + " desc ";
        }
        sql = sql + " limit " + limitSize + " offset " + offsetSize;
        sql = sql + " slimit " + slimitSize + " soffset " + soffsetSize;
        return sql;
    }

    public String buildSelectCount(List<String> newMeasurementList, String deviceName, Long startTime, Long endTime, Integer slimitSize, Integer soffsetSize) {
        String sql = "select count(" + (String)newMeasurementList.get(0) + ") from " + deviceName;
        if (startTime != null && endTime != null) {
            sql = sql + " where time >= " + startTime + " and time <= " + endTime;
        } else if (startTime == null && endTime != null) {
            sql = sql + " where time <= " + endTime;
        } else if (startTime != null && endTime == null) {
            sql = sql + " where time >= " + startTime;
        }
        sql = sql + " limit 1 ";
        sql = sql + " slimit " + slimitSize + " soffset " + soffsetSize;
        return sql;
    }

    public String buildShowType(String type, String path) {
        return "show " + type + " " + path + ".**";
    }

    public String buildSelectGroupBy(List<MeasurementDTO> measurements, List<String> newMeasurementList, String deviceName, Long startTime, Long endTime, String timeUnitInterval, String orderByDesc, Boolean ascOrDesc, String aggregation, Integer limitSize, Integer offsetSize, Integer slimitSize, Integer soffsetSize) {
        String sql = "select ";
        if (!CollectionUtils.isEmpty(newMeasurementList) && ((String)newMeasurementList.get(0)).equals("*")) {
            sql = sql + aggregation + "(*)  ";
        } else {
            for (String measurement : newMeasurementList) {
                List<MeasurementDTO> collect = (List<MeasurementDTO>)measurements.stream().filter(t -> (t.getTimeseries().substring(t.getTimeseries().lastIndexOf(".") + 1, t.getTimeseries().length()).equals(measurement) && TsDataTypeSwitchUtils.checkGroupType(t.getDatatype()).booleanValue())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    sql = sql + aggregation + "(" + measurement + "),";
                    continue;
                }
                sql = sql + "last_value(" + measurement + "),";
            }
        }
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " from " + deviceName;
        if (startTime != null && endTime != null)
            sql = sql + " group by ([" + startTime + "," + endTime + ")," + timeUnitInterval + ") ";
        sql = sql + " order by " + orderByDesc;
        if (ascOrDesc.booleanValue()) {
            sql = sql + " asc ";
        } else {
            sql = sql + " desc ";
        }
        sql = sql + " limit " + limitSize + " offset " + offsetSize;
        sql = sql + " slimit " + slimitSize + " soffset " + soffsetSize;
        return sql;
    }

    public String buildSelectMetricSql(List<String> metrics) {
        String sql = "select * from ";
        for (String metric : metrics)
            sql = sql + metric + CommonConstant.comma;
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " order by time desc limit 1";
        return sql;
    }

    public String buildSelectMetricSql(List<String> metrics, long startTime, long endTime, String ascOrDesc, Integer limit) {
        String sql = "select * from ";
        for (String metric : metrics)
            sql = sql + metric + CommonConstant.comma;
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " where time>=" + startTime + " and time<=" + endTime;
        sql = sql + " order by time " + ascOrDesc;
        if (!ObjectUtils.isEmpty(limit))
            sql = sql + " limit " + limit;
        return sql;
    }

    public String buildSelectMetricFieldColumnSql(List<String> fieldColumn, List<String> metrics, long startTime, long endTime, String ascOrDesc, Integer limit) {
        String sql = "select ";
        if (!CollectionUtils.isEmpty(fieldColumn))
            if (fieldColumn.size() == 1) {
                sql = sql + (String)fieldColumn.get(0) + " from ";
            } else {
                for (String filed : fieldColumn)
                    sql = sql + filed + ",";
                sql = sql.substring(0, sql.length() - 1);
                sql = sql + " from ";
            }
        for (String metric : metrics)
            sql = sql + metric + CommonConstant.comma;
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + " where time>=" + startTime + " and time<=" + endTime;
        sql = sql + " order by time " + ascOrDesc;
        if (!ObjectUtils.isEmpty(limit))
            sql = sql + " limit " + limit;
        return sql;
    }

    public String getMetric(String monitorHostAndPort, IotDBEnum em) {
        String value = em.name();
        if (StringUtils.isBlank(value))
            value = "";
        return "root.__system.metric.\"" + monitorHostAndPort + "\"." + value;
    }

    public String buildSelectMetricV11Sql(String clusterName, String nodeType, Integer nodeId, IotDBEnum em) {
        String value = em.name();
        if (StringUtils.isBlank(value))
            value = "";
        String sql = "root.__system.metric." + clusterName + "." + nodeType.toUpperCase() + ".`" + nodeId + ".`" + value;
        return sql;
    }

    public String buildSelectCountMetric(String metric, long startTime, long endTime) {
        return "select count from " + metric + " where time>=" + startTime + " and time<=" + endTime + " order by time asc ";
    }

    public String buildSelectSumAVGMetric(String metric, String type, Date startTime, Date endTime) {
        return "select " + type + "(*) from " + metric + " group by ([" +

                DateUtil.formatDate(startTime) + "T" +

                DateUtil.formatTime(startTime) + ", " +

                DateUtil.formatDate(endTime) + "T" +

                DateUtil.formatTime(endTime) + "),1m)";
    }

    public String buildSelectAuditLog(String userName, String startTime, String endTime, int pageSize, int pageStart) {
        return "select username,address,log from root.__system.audit._" + userName + " where time >" + startTime + " and time < " + endTime + " order by time desc limit " + pageSize + " offset " + pageStart;
    }

    public String buildSelectCountAuditLog(String userName, String startTime, String endTime) {
        return "select count(log) from root.__system.audit._" + userName + " where time > " + startTime + " and time < " + endTime;
    }
}
