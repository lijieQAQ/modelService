package com.lijie.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lijie on 2018/5/28.
 */
@Table(name="activity")
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id
    private char type; //类型
    private String name; //活动姓名
    private String title; //活动标题
    private String introduction; //活动简介
    private String content; //活动内容
    private Integer minPricePoint; //活动价格区间下限
    private Integer maxPricePoint; //活动价格区间上限
    private char isCarousel; //是否轮播
    private String coverPhoto; //封面图
    private String address; //活动地点
    private String userNum; //活动要求人数
    private int lookNum; //活动查看数量
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginDate; //活动开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate; //活动结束时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; //活动创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMinPricePoint() {
        return minPricePoint;
    }

    public void setMinPricePoint(Integer minPricePoint) {
        this.minPricePoint = minPricePoint;
    }

    public Integer getMaxPricePoint() {
        return maxPricePoint;
    }

    public void setMaxPricePoint(Integer maxPricePoint) {
        this.maxPricePoint = maxPricePoint;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public char getIsCarousel() {
        return isCarousel;
    }

    public void setIsCarousel(char isCarousel) {
        this.isCarousel = isCarousel;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public int getLookNum() {
        return lookNum;
    }

    public void setLookNum(int lookNum) {
        this.lookNum = lookNum;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", content='" + content + '\'' +
                ", minPricePoint=" + minPricePoint +
                ", maxPricePoint=" + maxPricePoint +
                ", isCarousel=" + isCarousel +
                ", coverPhoto='" + coverPhoto + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                '}';
    }
}
