package com.sf.bizx.rule.model;

import java.util.Date;

public class Rule {
    private Long rowId;
    private Long internalCode;
    private String code;
    private String name;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private String lastModifiedBy;
    
    public Long getRowId() {
        return rowId;
    }
    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }
    public Long getInternalCode() {
        return internalCode;
    }
    public void setInternalCode(Long internalCode) {
        this.internalCode = internalCode;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }
    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }
    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }
    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    @Override
    public String toString() {
        return "Rule [rowId=" + rowId + ", internalCode=" + internalCode + ", code=" + code
                + ", name=" + name + ", effectiveStartDate=" + effectiveStartDate
                + ", effectiveEndDate=" + effectiveEndDate + ", lastModifiedBy=" + lastModifiedBy
                + "]";
    }
}
