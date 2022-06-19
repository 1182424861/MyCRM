package com.crm.ssm.entity;

import java.io.Serializable;

public class SysMenu implements Serializable {
    private Integer rightId;

    private String rightParentId;

    private Byte rightType;

    private String rightText;

    private String rightUrl;

    private static final long serialVersionUID = 1L;

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public String getRightParentId() {
        return rightParentId;
    }

    public void setRightParentId(String rightParentId) {
        this.rightParentId = rightParentId == null ? null : rightParentId.trim();
    }

    public Byte getRightType() {
        return rightType;
    }

    public void setRightType(Byte rightType) {
        this.rightType = rightType;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText == null ? null : rightText.trim();
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl == null ? null : rightUrl.trim();
    }
}