package com.bright.system.vo;

/**
 * @MethodName
 * @Description TODO  常用其他配置
 * @Param null
 * @Return
 * @Author lmy
 * @Date 2021-6-7 14:59
 */
public class OtherTab {
    //状态
    private Boolean state;
    // uuid
    private String uuid;
    //类型
    private String type;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
