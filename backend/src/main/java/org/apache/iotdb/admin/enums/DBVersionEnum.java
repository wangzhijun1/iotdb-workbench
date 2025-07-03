package org.apache.iotdb.admin.enums;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.StringUtils;

public enum DBVersionEnum {
    V0_13_4("0.13.4", "0.13", "0"),
    V1_0_1("1.0.1", "1.0", "1"),
    V1_1_0("1.1.0", "1.1"),
    V1_1_1("1.1.1", "1.1");

    private String FULL_VERSION;

    private String MAJOR_VERSION;

    private String MINOR_VERSION;

    public String getFULL_VERSION() {
        return this.FULL_VERSION;
    }

    public void setFULL_VERSION(String FULL_VERSION) {
        this.FULL_VERSION = FULL_VERSION;
    }

    public String getMAJOR_VERSION() {
        return this.MAJOR_VERSION;
    }

    public void setMAJOR_VERSION(String MAJOR_VERSION) {
        this.MAJOR_VERSION = MAJOR_VERSION;
    }

    public String getMINOR_VERSION() {
        return this.MINOR_VERSION;
    }

    public void setMINOR_VERSION(String MINOR_VERSION) {
        this.MINOR_VERSION = MINOR_VERSION;
    }

    DBVersionEnum(String FULL_VERSION, String MAJOR_VERSION) {
        this.FULL_VERSION = FULL_VERSION;
        this.MAJOR_VERSION = MAJOR_VERSION;
    }

    DBVersionEnum(String FULL_VERSION, String MAJOR_VERSION, String MINOR_VERSION) {
        this.FULL_VERSION = FULL_VERSION;
        this.MAJOR_VERSION = MAJOR_VERSION;
        this.MINOR_VERSION = MINOR_VERSION;
    }

    public static org.apache.iotdb.admin.enums.DBVersionEnum valueOfVersion(String version) {
        org.apache.iotdb.admin.enums.DBVersionEnum enumVersion = null;
        org.apache.iotdb.admin.enums.DBVersionEnum[] values = values();
        for (org.apache.iotdb.admin.enums.DBVersionEnum value : values) {
            if (version.startsWith(value.MAJOR_VERSION))
                return value;
            if (StringUtils.hasLength(value.MINOR_VERSION) && version.startsWith(value.MINOR_VERSION))
                enumVersion = value;
        }
        if (!ObjectUtils.isEmpty(enumVersion))
            return enumVersion;
        return V0_13_4;
    }

    public static boolean checkVersion(String version) {
        if (StringUtils.hasLength(version)) {
            int compare = V0_13_4.getFULL_VERSION().compareTo(version);
            if (compare <= 0)
                return true;
        }
        return false;
    }

    public static boolean checkV100Version(String version) {
        if (StringUtils.hasLength(version)) {
            int compare = "1.0.0".compareTo(version);
            if (compare <= 0)
                return true;
        }
        return false;
    }
}

