package com.brightcomplete.modules.oss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brightcomplete.common.util.CommonUtils;
import com.brightcomplete.common.util.oss.OssBootUtil;
import com.brightcomplete.modules.oss.entity.OssFile;
import com.brightcomplete.modules.oss.mapper.OssFileMapper;
import com.brightcomplete.modules.oss.service.IOssFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description: OSS云存储实现类
 * @author lmy
 */
@Service("ossFileService")
public class OssFileServiceImpl extends ServiceImpl<OssFileMapper, OssFile> implements IOssFileService {

	@Override
	public void upload(MultipartFile multipartFile) throws IOException {
		String fileName = multipartFile.getOriginalFilename();
		fileName = CommonUtils.getFileName(fileName);
		OssFile ossFile = new OssFile();
		ossFile.setFileName(fileName);
		String url = OssBootUtil.upload(multipartFile,"upload/test");
		// 【文件预览】阿里云原生域名可以文件预览，自己映射域名kkfileview提示文件下载失败
		// 返回阿里云原生域名前缀URL
		ossFile.setUrl(OssBootUtil.getOriginalUrl(url));
		// 【文件预览】阿里云原生域名可以文件预览，自己映射域名kkfileview提示文件下载失败
		this.save(ossFile);
	}

	@Override
	public boolean delete(OssFile ossFile) {
		try {
			this.removeById(ossFile.getId());
			OssBootUtil.deleteUrl(ossFile.getUrl());
		}
		catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			return false;
		}
		return true;
	}

}
