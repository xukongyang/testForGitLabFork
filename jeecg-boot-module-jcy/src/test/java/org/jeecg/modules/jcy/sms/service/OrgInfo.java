package org.jeecg.modules.jcy.sms.service;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class OrgInfo {

    String orgId = "";
    String orgName = "";
    String orgType = "";
    String superOrgId = "";
    String status = "";
    String orgIdPath = "";
    String orgPath = "";

    String orgCode = "";
    String orgNewId = "";
    String orgParentId = "";
    String orgCategory = "";
    String orgNewStatus = "";

    String createUser = "xukong";

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getSuperOrgId() {
        return superOrgId;
    }

    public void setSuperOrgId(String superOrgId) {
        this.superOrgId = superOrgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgIdPath() {
        return orgIdPath;
    }

    public void setOrgIdPath(String orgIdPath) {
        this.orgIdPath = orgIdPath;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgNewId() {
        return orgNewId;
    }

    public void setOrgNewId(String orgNewId) {
        this.orgNewId = orgNewId;
    }

    public String getOrgParentId() {
        return orgParentId;
    }

    public void setOrgParentId(String orgParentId) {
        this.orgParentId = orgParentId;
    }

    public String getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getOrgNewStatus() {
        return orgNewStatus;
    }

    public void setOrgNewStatus(String orgNewStatus) {
        this.orgNewStatus = orgNewStatus;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
