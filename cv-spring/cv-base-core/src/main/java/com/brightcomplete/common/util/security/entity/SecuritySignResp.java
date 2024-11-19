package com.brightcomplete.common.util.security.entity;

import lombok.Data;

/**
 * @Description: SecuritySignResp
 * @author lmy
 */
@Data
public class SecuritySignResp {
    private String data;
    private String signData;
    private String aesKey;
}
