package org.apache.iotdb.admin.model.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: wangzhijun
 * @Date: 2025/06/06/13:55
 * @Description:
 */
public class ClusterNodeInfoVO {
    private Integer nodeId;

    private String nodeType;

    private String status;

    private String internalAddress;

    private Integer internalPort;

    private Integer configConsensusPort;

    private String rpcAddress;

    private Integer rpcPort;

    private Integer dataConsensusPort;

    private Integer schemaConsensusPort;

    private Integer mppPort;

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInternalAddress(String internalAddress) {
        this.internalAddress = internalAddress;
    }

    public void setRpcAddress(String rpcAddress) {
        this.rpcAddress = rpcAddress;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof org.apache.iotdb.admin.model.vo.ClusterNodeInfoVO))
            return false;
        org.apache.iotdb.admin.model.vo.ClusterNodeInfoVO other = (org.apache.iotdb.admin.model.vo.ClusterNodeInfoVO)o;
        if (!other.canEqual(this))
            return false;
        Object this$nodeId = getNodeId(), other$nodeId = other.getNodeId();
        if ((this$nodeId == null) ? (other$nodeId != null) : !this$nodeId.equals(other$nodeId))
            return false;
        Object this$nodeType = getNodeType(), other$nodeType = other.getNodeType();
        if ((this$nodeType == null) ? (other$nodeType != null) : !this$nodeType.equals(other$nodeType))
            return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status))
            return false;
        Object this$internalAddress = getInternalAddress(), other$internalAddress = other.getInternalAddress();
        if ((this$internalAddress == null) ? (other$internalAddress != null) : !this$internalAddress.equals(other$internalAddress))
            return false;
        Object this$internalPort = getInternalPort(), other$internalPort = other.getInternalPort();
        if ((this$internalPort == null) ? (other$internalPort != null) : !this$internalPort.equals(other$internalPort))
            return false;
        Object this$configConsensusPort = getConfigConsensusPort(), other$configConsensusPort = other.getConfigConsensusPort();
        if ((this$configConsensusPort == null) ? (other$configConsensusPort != null) : !this$configConsensusPort.equals(other$configConsensusPort))
            return false;
        Object this$rpcAddress = getRpcAddress(), other$rpcAddress = other.getRpcAddress();
        if ((this$rpcAddress == null) ? (other$rpcAddress != null) : !this$rpcAddress.equals(other$rpcAddress))
            return false;
        Object this$rpcPort = getRpcPort(), other$rpcPort = other.getRpcPort();
        if ((this$rpcPort == null) ? (other$rpcPort != null) : !this$rpcPort.equals(other$rpcPort))
            return false;
        Object this$dataConsensusPort = getDataConsensusPort(), other$dataConsensusPort = other.getDataConsensusPort();
        if ((this$dataConsensusPort == null) ? (other$dataConsensusPort != null) : !this$dataConsensusPort.equals(other$dataConsensusPort))
            return false;
        Object this$schemaConsensusPort = getSchemaConsensusPort(), other$schemaConsensusPort = other.getSchemaConsensusPort();
        if ((this$schemaConsensusPort == null) ? (other$schemaConsensusPort != null) : !this$schemaConsensusPort.equals(other$schemaConsensusPort))
            return false;
        Object this$mppPort = getMppPort(), other$mppPort = other.getMppPort();
        return !((this$mppPort == null) ? (other$mppPort != null) : !this$mppPort.equals(other$mppPort));
    }

    protected boolean canEqual(Object other) {
        return other instanceof org.apache.iotdb.admin.model.vo.ClusterNodeInfoVO;
    }



    public String toString() {
        return "ClusterNodeInfoVO(nodeId=" + getNodeId() + ", nodeType=" + getNodeType() + ", status=" + getStatus() + ", internalAddress=" + getInternalAddress() + ", internalPort=" + getInternalPort() + ", configConsensusPort=" + getConfigConsensusPort() + ", rpcAddress=" + getRpcAddress() + ", rpcPort=" + getRpcPort() + ", dataConsensusPort=" + getDataConsensusPort() + ", schemaConsensusPort=" + getSchemaConsensusPort() + ", mppPort=" + getMppPort() + ")";
    }

    public Integer getNodeId() {
        return this.nodeId;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public String getStatus() {
        return this.status;
    }

    public String getInternalAddress() {
        return this.internalAddress;
    }

    public Integer getInternalPort() {
        return this.internalPort;
    }

    public Integer getConfigConsensusPort() {
        return this.configConsensusPort;
    }

    public String getRpcAddress() {
        return this.rpcAddress;
    }

    public Integer getRpcPort() {
        return this.rpcPort;
    }

    public Integer getDataConsensusPort() {
        return this.dataConsensusPort;
    }

    public Integer getSchemaConsensusPort() {
        return this.schemaConsensusPort;
    }

    public Integer getMppPort() {
        return this.mppPort;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public void setInternalPort(Integer internalPort) {
        this.internalPort = internalPort;
    }

    public void setConfigConsensusPort(Integer configConsensusPort) {
        this.configConsensusPort = configConsensusPort;
    }

    public void setRpcPort(Integer rpcPort) {
        this.rpcPort = rpcPort;
    }

    public void setDataConsensusPort(Integer dataConsensusPort) {
        this.dataConsensusPort = dataConsensusPort;
    }

    public void setSchemaConsensusPort(Integer schemaConsensusPort) {
        this.schemaConsensusPort = schemaConsensusPort;
    }

    public void setMppPort(Integer mppPort) {
        this.mppPort = mppPort;
    }

    public void setNodeID(String nodeId) {
        if (StringUtils.isNotBlank(nodeId))
            this.nodeId = Integer.valueOf(nodeId);
    }

    public void setInternalPort(String internalPort) {
        if (StringUtils.isNotBlank(internalPort))
            this.internalPort = Integer.valueOf(internalPort);
    }

    public void setConfigConsensusPort(String configConsensusPort) {
        if (StringUtils.isNotBlank(configConsensusPort))
            this.configConsensusPort = Integer.valueOf(configConsensusPort);
    }

    public void setRpcPort(String rpcPort) {
        if (StringUtils.isNotBlank(rpcPort))
            this.rpcPort = Integer.valueOf(rpcPort);
    }

    public void setDataConsensusPort(String dataConsensusPort) {
        if (StringUtils.isNotBlank(dataConsensusPort))
            this.dataConsensusPort = Integer.valueOf(dataConsensusPort);
    }

    public void setSchemaConsensusPort(String schemaConsensusPort) {
        if (StringUtils.isNotBlank(schemaConsensusPort))
            this.schemaConsensusPort = Integer.valueOf(schemaConsensusPort);
    }

    public void setMppPort(String mppPort) {
        if (StringUtils.isNotBlank(mppPort))
            this.mppPort = Integer.valueOf(mppPort);
    }
}
