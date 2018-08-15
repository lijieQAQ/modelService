package com.lijie.pojo;



import javax.persistence.*;

/**
 * Created by lijie on 2018/5/28.
 */
@Table(name="staff_pic")
@Entity
public class StaffPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer staffId; //用户id
    private String videoPath; //视频地址
    private String makeUp; //化妆
    private String noMakeUp; //素颜
    private String body; //全身照片
    private String modelCard; //模卡


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getMakeUp() {
        return makeUp;
    }

    public void setMakeUp(String makeUp) {
        this.makeUp = makeUp;
    }

    public String getNoMakeUp() {
        return noMakeUp;
    }

    public void setNoMakeUp(String noMakeUp) {
        this.noMakeUp = noMakeUp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getModelCard() {
        return modelCard;
    }

    public void setModelCard(String modelCard) {
        this.modelCard = modelCard;
    }
}
