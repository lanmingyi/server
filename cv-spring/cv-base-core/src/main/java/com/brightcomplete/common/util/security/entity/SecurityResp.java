package com.brightcomplete.common.util.security.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Description: SecurityResp
 * @author lmy
 */
@Data
public class SecurityResp {
    private Boolean success;
    private JSONObject data;
}
