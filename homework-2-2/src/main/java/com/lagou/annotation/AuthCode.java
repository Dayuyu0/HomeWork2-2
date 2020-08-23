package com.lagou.annotation;

public enum AuthCode {
    Allow("00000", "00000", "允许访问"),

    /******************用户权限******************/

    AU0001("100001", "AU0001", "新增用户", "新增用户"),

    AU0002("100002", "AU0002", "删除用户", "批量删除用户");

    /**权限标识 */
    private String authId;

    /**权限编码 */
    private String authCode;

    /**权限名称 */
    private String authName;

    /**权限描述 */
    private String authDesc;


    AuthCode(String authId, String authCode, String authName) {
        this.authId = authId;
        this.authCode = authCode;
        this.authName = authName;
    }


    AuthCode(String authId, String authCode, String authName, String authDesc) {
        this.authId = authId;
        this.authCode = authCode;
        this.authName = authName;
        this.authDesc = authDesc;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    @Override
    public String toString() {
        return String.format("authId:%s, authCode:%s, authName:%s, authDesc:%s", this.authId, this.authCode, this.authName, this.authDesc);
    }
}
