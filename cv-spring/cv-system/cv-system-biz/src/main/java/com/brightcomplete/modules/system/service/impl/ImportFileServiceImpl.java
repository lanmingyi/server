package com.brightcomplete.modules.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.util.CommonUtils;
import com.brightcomplete.poi.excel.imports.base.ImportFileServiceI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * excel导入 实现类
 * @author lmy
 */
@Slf4j
@Service
public class ImportFileServiceImpl implements ImportFileServiceI {

    @Value("${brightcomplete.path.upload}")
    private String upLoadPath;

    @Value(value="${brightcomplete.uploadType}")
    private String uploadType;

    @Override
    public String doUpload(byte[] data) {
        return CommonUtils.uploadOnlineImage(data, upLoadPath, "import", uploadType);
    }
}
