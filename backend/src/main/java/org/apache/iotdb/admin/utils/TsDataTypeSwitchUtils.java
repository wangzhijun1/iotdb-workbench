package org.apache.iotdb.admin.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.iotdb.admin.common.exception.BaseException;
import org.apache.tsfile.enums.TSDataType;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: wangzhijun
 * @Date: 2025/06/06/13:48
 * @Description:
 */
public class TsDataTypeSwitchUtils {
    public static TSDataType handleTypeStr(String type) throws BaseException {
        TSDataType tsDataType;
        switch (type) {
            case "BOOLEAN":
                tsDataType = TSDataType.BOOLEAN;
                return tsDataType;
            case "INT32":
                tsDataType = TSDataType.INT32;
                return tsDataType;
            case "INT64":
                tsDataType = TSDataType.INT64;
                return tsDataType;
            case "FLOAT":
                tsDataType = TSDataType.FLOAT;
                return tsDataType;
            case "DOUBLE":
                tsDataType = TSDataType.DOUBLE;
                return tsDataType;
            case "TEXT":
                tsDataType = TSDataType.TEXT;
                return tsDataType;
        }
        throw new BaseException("IOTDB-0004", "DB_DATATYPE_WRONG_MSG");
    }

    public static Boolean checkGroupType(String type) {
        Boolean tsDataType = Boolean.valueOf(false);
        switch (type) {
            case "BOOLEAN":
                tsDataType = Boolean.valueOf(false);
                return tsDataType;
            case "INT32":
                tsDataType = Boolean.valueOf(true);
                return tsDataType;
            case "INT64":
                tsDataType = Boolean.valueOf(true);
                return tsDataType;
            case "FLOAT":
                tsDataType = Boolean.valueOf(true);
                return tsDataType;
            case "DOUBLE":
                tsDataType = Boolean.valueOf(true);
                return tsDataType;
            case "TEXT":
                tsDataType = Boolean.valueOf(false);
                return tsDataType;
        }
        tsDataType = Boolean.valueOf(false);
        return tsDataType;
    }

    public static Boolean checkMeasurementType(String type) {
        List<String> list = Arrays.asList(new String[] { "BOOLEAN", "INT32", "INT64", "FLOAT", "DOUBLE", "TEXT" });
        try {
            String typeStr = list.stream().filter(t -> t.equalsIgnoreCase(type)).findFirst().get();
            if (StringUtils.isNotBlank(typeStr))
                return Boolean.valueOf(true);
            return Boolean.valueOf(false);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }
}
