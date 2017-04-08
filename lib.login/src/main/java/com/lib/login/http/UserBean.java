package com.lib.login.http;

import java.io.Serializable;

public class UserBean implements Serializable {
    /**
     * SessionId : 3bb13310-e404-45ae-aeac-17250ce51449
     * UserId : e98ebf49-f501-44a0-80c8-2d81f39b7dbb
     * Username : admin
     * Mobile : 18046300497
     * Email : 295552891@qq.com
     * Status : 1
     * UserAccountStatusDesc : 激活的
     * LastLoginAt : /Date(1491536692947)/
     * LastLoginIP : 0.0.0.0
     * Created : /Date(1491536692947)/
     * CreatedFormated : 2017-04-07 11:44:52
     * Updated : /Date(1491536692947)/
     * UpdatedFormated : 2017-04-07 11:44:52
     * Nickname : null
     * Avatar : null
     * Sex : null
     * UserSexDesc :
     * Age : null
     * UserBalance : null
     * UserLockBalance : null
     * RoleId : 1
     * Role : 系统管理员
     */

    private String SessionId;
    private String UserId;
    private String Username;
    private String Mobile;
    private String Email;
    private int Status;
    private String UserAccountStatusDesc;
    private String LastLoginAt;
    private String LastLoginIP;
    private String Created;
    private String CreatedFormated;
    private String Updated;
    private String UpdatedFormated;
    private Object Nickname;
    private Object Avatar;
    private Object Sex;
    private String UserSexDesc;
    private Object Age;
    private Object UserBalance;
    private Object UserLockBalance;
    private int RoleId;
    private String Role;

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getUserAccountStatusDesc() {
        return UserAccountStatusDesc;
    }

    public void setUserAccountStatusDesc(String UserAccountStatusDesc) {
        this.UserAccountStatusDesc = UserAccountStatusDesc;
    }

    public String getLastLoginAt() {
        return LastLoginAt;
    }

    public void setLastLoginAt(String LastLoginAt) {
        this.LastLoginAt = LastLoginAt;
    }

    public String getLastLoginIP() {
        return LastLoginIP;
    }

    public void setLastLoginIP(String LastLoginIP) {
        this.LastLoginIP = LastLoginIP;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }

    public String getCreatedFormated() {
        return CreatedFormated;
    }

    public void setCreatedFormated(String CreatedFormated) {
        this.CreatedFormated = CreatedFormated;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String Updated) {
        this.Updated = Updated;
    }

    public String getUpdatedFormated() {
        return UpdatedFormated;
    }

    public void setUpdatedFormated(String UpdatedFormated) {
        this.UpdatedFormated = UpdatedFormated;
    }

    public Object getNickname() {
        return Nickname;
    }

    public void setNickname(Object Nickname) {
        this.Nickname = Nickname;
    }

    public Object getAvatar() {
        return Avatar;
    }

    public void setAvatar(Object Avatar) {
        this.Avatar = Avatar;
    }

    public Object getSex() {
        return Sex;
    }

    public void setSex(Object Sex) {
        this.Sex = Sex;
    }

    public String getUserSexDesc() {
        return UserSexDesc;
    }

    public void setUserSexDesc(String UserSexDesc) {
        this.UserSexDesc = UserSexDesc;
    }

    public Object getAge() {
        return Age;
    }

    public void setAge(Object Age) {
        this.Age = Age;
    }

    public Object getUserBalance() {
        return UserBalance;
    }

    public void setUserBalance(Object UserBalance) {
        this.UserBalance = UserBalance;
    }

    public Object getUserLockBalance() {
        return UserLockBalance;
    }

    public void setUserLockBalance(Object UserLockBalance) {
        this.UserLockBalance = UserLockBalance;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
}
