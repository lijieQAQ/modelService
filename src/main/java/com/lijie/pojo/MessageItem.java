package com.lijie.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name="messageItem")
@Entity
public class MessageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    private String msgType;

    private Integer user1;
    private String loginId1;
    private String portrait1;

    private Integer user2;
    
    private String loginId2;
    private String portrait2;
    private Integer speaker;

    private String message;

    private Date sendTime;

    private Date openTime;

    private String status;
    
    private Integer unopenCnt;

    private String delFlg;

    private Integer createId;

    private Date createDate;

    private Integer updateId;

    private Date updateDate;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public Integer getUser1() {
        return user1;
    }

    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    public String getLoginId1() {
        return loginId1;
    }

    public void setLoginId1(String loginId1) {
        this.loginId1 = loginId1;
    }
    
    public String getPortrait1() {
        return portrait1;
    }

    public void setPortrait1(String portrait1) {
        this.portrait1 = portrait1;
    }
    
    public Integer getUser2() {
        return user2;
    }

    public void setUser2(Integer user2) {
        this.user2 = user2;
    }

    public String getLoginId2() {
        return loginId2;
    }

    public void setLoginId2(String loginId2) {
        this.loginId2 = loginId2;
    }
    
    public String getPortrait2() {
        return portrait2;
    }

    public void setPortrait2(String portrait2) {
        this.portrait2 = portrait2;
    }
    
    public Integer getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Integer speaker) {
        this.speaker = speaker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public Integer getUnopenCnt() {
        return unopenCnt;
    }

    public void setUnopenCnt(Integer unopenCnt) {
        this.unopenCnt = unopenCnt;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


}
