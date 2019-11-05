package com.sf.hop.rule.model;

public class Rule {
    private String code;
    private String name;
    private String lastModifiedBy;
    
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
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rule [code=");
        builder.append(code);
        builder.append(", name=");
        builder.append(name);
        builder.append(", lastModifiedBy=");
        builder.append(lastModifiedBy);
        builder.append("]");
        return builder.toString();
    }
}
