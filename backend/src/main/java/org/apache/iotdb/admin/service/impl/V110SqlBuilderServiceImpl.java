package org.apache.iotdb.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.iotdb.admin.enums.IotDBEnum;
import org.apache.iotdb.admin.service.ISqlBuilderService;
import org.springframework.stereotype.Service;


/**
 * @Author: wangzhijun
 * @Date: 2025/06/06/13:39
 * @Description:
 */
@Service
public class V110SqlBuilderServiceImpl extends BaseSqlBuilder implements ISqlBuilderService {
        public String buildListRole(String userName) {
            if (StringUtils.isNotBlank(userName))
                return "list role of user " + userName;
            return "list role";
        }

        public String buildListUserOfRole(String roleName) {
            if (StringUtils.isNotBlank(roleName))
                return "list user of role " + roleName;
            return "list user";
        }

        public String upsertAuthorityPrivilege(String operationType, String userOrRole, String name, String privilegesStr, String path) {
            return operationType + " " + userOrRole + " " + name + " privileges " + privilegesStr + " on " + path;
        }

        public String buildListPrivilegesOfRole(String roleName) {
            return "list privileges role " + roleName;
        }

        public String buildListPrivilegesOfUser(String userName) {
            return "list privileges user " + userName;
        }

        public String getMetric(String monitorHostAndPort, IotDBEnum em) {
            String value = em.name();
            if (StringUtils.isBlank(value))
                value = "";
            return "root.__system.metric.`" + monitorHostAndPort + "`." + value;
        }

        public String buildSelectMetricV11Sql(String clusterName, String nodeType, Integer nodeId, IotDBEnum em) {
            String value = em.name();
            if (StringUtils.isBlank(value))
                value = "";
            return "root.__system.metric." + clusterName + "." + nodeType

                    .toUpperCase() + ".`" + nodeId + "`." + value;
        }
    }

