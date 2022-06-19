package com.crm.ssm.entity;

import java.io.Serializable;

public class SysRoleMenuKey implements Serializable {
    private Integer rfRoleId;

    private Integer rfRightId;

    private static final long serialVersionUID = 1L;

    public Integer getRfRoleId() {
        return rfRoleId;
    }

    public void setRfRoleId(Integer rfRoleId) {
        this.rfRoleId = rfRoleId;
    }

    public Integer getRfRightId() {
        return rfRightId;
    }

    public void setRfRightId(Integer rfRightId) {
        this.rfRightId = rfRightId;
    }
}