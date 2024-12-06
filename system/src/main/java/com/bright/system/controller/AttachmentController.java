//package com.bright.system.controller;
//
//import cn.ewsd.common.utils.BaseUtils;
//import cn.ewsd.common.utils.JsonUtils;
//import cn.ewsd.common.utils.StringUtils;
//import cn.hutool.core.util.ArrayUtil;
//import com.aliyun.oss.OSSClient;
//import com.misboot.system.config.UeditorConfig;
//import com.misboot.system.model.Attachment;
//import com.misboot.system.service.AttachmentService;
//import com.misboot.system.utils.ImportExeclUtil;
//import com.misboot.system.utils.PageParam;
//import com.misboot.system.utils.PageSet;
//import com.misboot.system.vo.FileUplaodTab;
//import com.misboot.system.vo.OtherTab;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.http.entity.ContentType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Api(tags = {"文件上传"})
//@RefreshScope
//@Controller
//@RequestMapping("/attachment")
//public class AttachmentController extends SystemBaseController {
//
//    static Logger logger = LoggerFactory.getLogger(AttachmentController.class);
//
//    private static final boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
//
//    private static final boolean isLinux = System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0;
//
//    @Resource
//    private AttachmentService attachmentService;
//
//    //本地文件上传地址
//    @Value("${my.upload-dir}")
//    private String uploadDir;
//
//    public static String accessKeyId = "4WEX9WabROAtS62m";
//    public static String accessKeySecret = "0rjT6OsMLF1c7Sa6LGgQKoQWgkKO55";
//    public static String bucketName = "ewsd";
//
//
//    //分页数据集
//    @ResponseBody
//    @RequestMapping(value = "/getPageSetData")
//    public Object getPageSetData(PageParam pageParam) throws Exception {
//        String filterStr = "";
//        filterStr = BaseUtils.filterSort(request);
//        PageSet<Attachment> pageSet = attachmentService.getPageSet(pageParam, filterStr);
//        return pageSet;
//    }
//
//    //根据puuid获取分页数据集
//    @ResponseBody
//    @RequestMapping(value = "/getListByPuuid")
//    public Object getListByPuuid(PageParam pageParam, String puuid) throws Exception {
//        PageSet<Attachment> pageSet = new PageSet<>();
//        if (!StringUtils.isNullOrEmpty(puuid)) {
//            String filterSort = BaseUtils.filterSort(request, " and puuid = '" + puuid + "'");
//            pageSet = attachmentService.getPageSet(pageParam, filterSort);
//            for (Attachment attachment : pageSet.getRows()) {
//                attachment.setFilSize(com.misboot.system.utils.file.FileUtils.getSize(attachment.getFileSize()));
//            }
//        }
//        return pageSet;
//    }
//
//    //根据uuid获取详情
//    @ResponseBody
//    @RequestMapping(value = "/getDetailByUuid")
//    public Object getDetailByUuid() throws Exception {
//        String uuid = request.getParameter("uuid");
//        Attachment info = attachmentService.selectByPrimaryKey(uuid);
//        return info;
//    }
//
//
//    @Autowired
//    private SysConfigurationController sysConfigurationController;
//
//    //获取系统存储地址
//    public String getUploadDir(String filtType) {
//        Object other = sysConfigurationController.getSysConfigurationDetailByType("other");
//        //判断是否开启自动获取配置
//        String uploadDirs = "";
//        if (((OtherTab) other).getState()) {
//            Object object = sysConfigurationController.getSysConfigurationDetailByType("fileUpload");
//            uploadDirs = isWin ? "D:\\zysd" : "/home/zysd";
//            if (null != object) {
//                if (!StringUtils.isNullOrEmpty(((FileUplaodTab) object).getLocalUploadFilePath())) {
//                    uploadDirs = ((FileUplaodTab) object).getLocalUploadFilePath();
//                }
//            }
//        } else {
//            uploadDirs = uploadDir;
//        }
//        String uploadTypeDir = uploadDirs + "/uploads/" + filtType;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd");
//        Date date = new Date();
//        String ymd = sdf.format(date);
//        uploadTypeDir += File.separator + ymd + File.separator;
//        return uploadTypeDir;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/md5Validate")
//    public Object md5Validate() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("isHave", false);
//        return map;
//    }
//
//    /**
//     * 单个附件上传
//     *
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @ApiOperation(value = "单个附件上传")
//    @ApiImplicitParam(name = "sysFengkai1", value = "保存SysFengkai1模块数据", required = true, dataType = "SysFengkai1")
//    @ResponseBody
//    @RequestMapping(value = "/upload")
//    public Object upload(HttpServletRequest request) throws Exception {
//
//        request.setCharacterEncoding("UTF-8");
//
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//
//        //定义允许上传的文件扩展名
//        HashMap<String, String> extMap = new HashMap<String, String>();
//        extMap.put("image", "gif,jpg,jpeg,png,bmp");
//        extMap.put("flash", "swf,flv");
//        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//        extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,pdf,mp4");
//
//        Map<String, Object> returnMap = new HashMap<String, Object>();
//
//        String fileName;
//        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//            // 上传文件
//            MultipartFile mf = entity.getValue();
//            long fileSize = mf.getSize();
//            fileName = mf.getOriginalFilename();
//            //fileName = URLDecoder.decode(fileName, "UTF-8");
//
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//
//            // 获得文件类型，放入相应的文件夹
//            String fileType = "";
//            Boolean allowUpload = false;
//            for (Map.Entry<String, String> vo : extMap.entrySet()) {
//                if (vo.getValue().indexOf(fileExt) > -1) {
//                    fileType = vo.getKey();
//                    allowUpload = true;
//                    break;
//                }
//            }
//
//            if (!allowUpload) {
//                returnMap.put("statusCode", 300);
//                returnMap.put("title", "操作提示");
//                returnMap.put("message", "不允许上传该类文件！");
//                return returnMap;
//            }
//
//            fileType = "".equals(fileType) ? "file" : fileType;
//            String uploadDir = getUploadDir(fileType);
//            // 创建文件夹
//            File file = new File(uploadDir);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            // 生成保存到服务器的文件名
//            String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + "_" + BaseUtils.getFixLengthNum(10000, 99999) + "." + fileExt;
//            File uploadFile = new File(uploadDir + "/" + newFileName);
//            uploadDir = uploadDir.replace("\\", "/");
//            try {
//                FileCopyUtils.copy(mf.getBytes(), uploadFile);
//                save("uplodify", request.getParameter("module"), request.getParameter("category"), request.getParameter("puuid"), fileExt, fileSize, fileName, newFileName, uploadDir.substring(uploadDir.indexOf("uploads") - 1) + newFileName);
//                String filepath = "/" + uploadDir.substring(uploadDir.indexOf("uploads")) + newFileName;
//                returnMap.put("statusCode", 200);
//                returnMap.put("title", "操作提示");
//                returnMap.put("message", "上传成功");
//                returnMap.put("filePath", filepath);
//            } catch (IOException e) {
//                e.printStackTrace();
//                returnMap.put("statusCode", 300);
//                returnMap.put("title", "操作提示");
//                returnMap.put("message", "上传失败");
//            }
//        }
//        return returnMap;
//    }
//
//    /**
//     * 多个附件上传，同时接收多个附件，如ajaxFileUploader上传工具
//     *
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @ApiOperation(value = "多个附件上传，同时接收多个附件，如ajaxFileUploader上传工具")
//    @ApiImplicitParam(name = "sysFengkai1", value = "保存SysFengkai1模块数据", required = true, dataType = "SysFengkai1")
//    @ResponseBody
//    @RequestMapping(value = "/multiUpload")
//    public Object multiUpload(HttpServletRequest request) throws Exception {
//        request.setCharacterEncoding("UTF-8");
//
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//
//        Map<String, String[]> params = multipartRequest.getParameterMap();
//
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        List<MultipartFile> multiFileMap = multipartRequest.getMultiFileMap().get("files");
//
//        //定义允许上传的文件扩展名
//        HashMap<String, String> extMap = new HashMap<String, String>();
//        extMap.put("image", "gif,jpg,jpeg,png,bmp");
//        extMap.put("flash", "swf,flv");
//        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//        extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,pdf");
//
//        Map<String, Object> returnMap = new HashMap<String, Object>();
//
//        String fileName;
//        //for (Map.Entry<String, List<MultipartFile>> entity : multiFileMap.entrySet()) {
//        for (int i = 0; i < multiFileMap.size(); i++) {
//            // 上传文件
//            MultipartFile mf = multiFileMap.get(i);
//            long fileSize = mf.getSize();
//            fileName = mf.getOriginalFilename();
//            //fileName = URLDecoder.decode(fileName, "UTF-8");
//
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//
//            // 获得文件类型，放入相应的文件夹
//            String fileType = "";
//            for (Map.Entry<String, String> vo : extMap.entrySet()) {
//                if (vo.getValue().indexOf(fileExt) > -1) {
//                    fileType = vo.getKey();
//                    break;
//                }
//            }
//            fileType = "".equals(fileType) ? "file" : fileType;
//            String uploadDir = getUploadDir(fileType);
//            // 创建文件夹
//            File file = new File(uploadDir);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            // 生成保存到服务器的文件名
//            String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + "_" + BaseUtils.getFixLengthNum(10000, 99999) + "." + fileExt;
//            File uploadFile = new File(uploadDir + "/" + newFileName);
//            try {
//                FileCopyUtils.copy(mf.getBytes(), uploadFile);
//                //save("uplodify", request.getParameter("module"), request.getParameter("category"), request.getParameter("puuid"), fileExt, fileSize, fileName, newFileName, uploadDir.substring(uploadDir.indexOf("uploads") - 1) + newFileName);
//                returnMap.put("success", true);
//            } catch (IOException e) {
//                e.printStackTrace();
//                returnMap.put("success", false);
//            }
//        }
//        return returnMap;
//    }
//
//    private Map<String, Object> getError(String message) {
//        Map<String, Object> msg = new HashMap<String, Object>();
//        msg.put("error", 1);
//        msg.put("message", message);
//        return msg;
//    }
//
//    //文件上传
//    @RequestMapping(value = "/cloudUpload", method = RequestMethod.POST)
//    public void cloudUpload() {
//        String filePath = request.getParameter("filePath");
//        String fileExt = filePath.substring(filePath.lastIndexOf("."));
//
//        //文件保存路径
//        String savePath = "uploads/";
//
//        //定义允许上传的文件扩展名
//        HashMap<String, String> extMap = new HashMap<String, String>();
//        extMap.put("image", "gif,jpg,jpeg,png,bmp");
//        extMap.put("flash", "swf,flv");
//        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//        extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,pdf");
//        extMap.put("temp", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,pdf,gif,jpg,jpeg,png,bmp");
//
//        String dirName = request.getParameter("dir");
//        if (dirName == null) {
//            dirName = "file";
//        }
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String ymd = sdf.format(new Date());
//        savePath += dirName + "/" + ymd + "/";
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
//        String newFileName = df.format(new Date()) + "_" + BaseUtils.getFixLengthNum(10000, 99999) + fileExt;
//
//        // endpoint以杭州为例，其它region请按实际情况填写
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 创建OSSClient实例
//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        // 上传文件
//        ossClient.putObject(bucketName, savePath + newFileName, new File(filePath));
//        // 关闭client
//        ossClient.shutdown();
//
//    }
//
//    /**
//     * @MethodName download
//     * @Description TODO 本地文件下载
//     * @Param req
//     * @Param response
//     * @Return void
//     * @Author 明成
//     * @Date 2021-1-4 18:36
//     */
//    @RequestMapping("/download")
//    public void download(HttpServletRequest req, HttpServletResponse response) throws IOException {
//        String uuid = request.getParameter("uuid");
//        Attachment attach = attachmentService.selectByPrimaryKey(uuid);
//        String path = uploadDir() + attach.getFilePath();
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        try {
//            String fileName = attach.getFileName();
//            inputStream = new FileInputStream(path);
//            outputStream = response.getOutputStream();
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Length", String.valueOf(inputStream.available()));
//            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8")); //解决乱码
//            IOUtils.copy(inputStream, outputStream);
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            outputStream.close();
//            inputStream.close();
//            IOUtils.closeQuietly(inputStream);
//            IOUtils.closeQuietly(outputStream);
//        }
//    }
//
//    private String uploadDir() {
////        Object object = sysConfigurationController.getSysConfigurationDetailByType("fileUpload");
////        String uploadDir = isWin ? "D:\\zysd" : "/home/zysd";
////        String uploadDir = uploadDir;
////        if (null != object) {
////            if (!StringUtils.isNullOrEmpty(((FileUplaodTab) object).getLocalUploadFilePath())) {
////                uploadDir = ((FileUplaodTab) object).getLocalUploadFilePath();
////            }
////        }
//        return uploadDir;
//    }
//
//    /**
//     * @MethodName pdfStreamHandeler
//     * @Description 返回pdf流文件
//     * @Param request
//     * @Param response
//     * @Return void
//     * @Author 明成
//     * @Date 2019/1/21 11:31
//     */
//    @RequestMapping(value = "/pdfStreamHandeler", method = {RequestMethod.POST, RequestMethod.GET})
//    public void pdfStreamHandeler(HttpServletRequest request, HttpServletResponse response) {
//        String uuid = request.getParameter("uuid");
//        Attachment attach = attachmentService.selectByPrimaryKey(uuid);
//        String path = uploadDir() + attach.getFilePath();
//        File file = new File(path);
//        byte[] data = null;
//        try {
//            //// 解决请求头跨域问题（IE兼容性  也可使用该方法）
//            //response.setHeader("Access-Control-Allow-Credentials", "true");
//            //response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//            //response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Token,Accept, Connection, User-Agent, Cookie");
//            //response.setHeader("Access-Control-Max-Age", "3628800");
//            //response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setContentType("application/pdf");
//            FileInputStream input = new FileInputStream(file);
//            data = new byte[input.available()];
//            input.read(data);
//            response.getOutputStream().write(data);
//            input.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public void save(String source, String module, String category, String puuid, String fileType, Long fileSize, String fileName, String newFileName, String filePath) {
//
//
//        Attachment attachment = new Attachment();
//        attachment.setSource(source);
//        attachment.setModule(module);
//        attachment.setCategory(category);
//        attachment.setPuuid(puuid);
//        attachment.setFileType(fileType);
//        attachment.setFileSize(fileSize);
//        attachment.setFileName(fileName);
//        attachment.setFilePath(filePath);
//        attachmentService.insertSelective(getSaveData(attachment));
//    }
//
//    /**
//     * 批量保存附件信息
//     *
//     * @return
//     * @throws Exception
//     */
//    @ResponseBody
//    @RequestMapping("/batchSave")
//    public Integer batchSave(String puuid, String file1, String file2, String file3, String file4, String file5) throws Exception {
//        Attachment attachment = new Attachment();
//        attachment.setPuuid(puuid);
//
//        Integer result = 0;
//        if (!StringUtils.isNullOrEmpty(file1)) {
//            attachment.setFileName(file1);
//            attachmentService.insertSelective(getSaveData(attachment));
//            result++;
//        }
//        if (!StringUtils.isNullOrEmpty(file2)) {
//            attachment.setFileName(file2);
//            attachmentService.insertSelective(getSaveData(attachment));
//            result++;
//        }
//        if (!StringUtils.isNullOrEmpty(file3)) {
//            attachment.setFileName(file3);
//            attachmentService.insertSelective(getSaveData(attachment));
//            result++;
//        }
//        if (!StringUtils.isNullOrEmpty(file4)) {
//            attachment.setFileName(file4);
//            attachmentService.insertSelective(getSaveData(attachment));
//            result++;
//        }
//        if (!StringUtils.isNullOrEmpty(file5)) {
//            attachment.setFileName(file5);
//            attachmentService.insertSelective(getSaveData(attachment));
//            result++;
//        }
//        return result;
//    }
//
//
//    //更新
//    @ResponseBody
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Object update(String uuid, String fileName, String fileVersion, String fileStatus) throws Exception {
//        //"UPDATE Attachment SET fileName = '" + fileName + "', fileVersion = '" + fileVersion + "', fileStatus = '" + fileStatus + "' WHERE uuid = '" + uuid + "'"
//        int result = attachmentService.updateFileNameAndFileVersionAndFileStatusAndUuid(fileName, fileVersion, fileStatus, uuid);
//        if (result == 1) {
//            return JsonUtils.messageJson(200, "温馨提示", "数据更新成功");
//        } else {
//            return JsonUtils.messageJson(300, "温馨提示", "数据更新失败");
//        }
//    }
//
//    //本地文件删除
//    @ResponseBody
//    @RequestMapping("/delete")
//    public Object delete(String[] uuid) throws Exception {
//        if (ArrayUtil.isEmpty(uuid)) {
//            return failure("参数uuid不能为空！！！");
//        }
//        Integer resule = 0;
//        for (int i = 0; i < uuid.length; i++) {
//            Attachment attachment = attachmentService.selectByPrimaryKey(uuid[i]);
//            //删除数据库
//            Integer integer = attachmentService.deleteByPrimaryKey(uuid[i]);
//            if(integer >=1 ){
//                //删除本地文件
//                String filePrefix = isWin ? "D:/zysd" : "/home/zysd";
//                String filePath = filePrefix + attachment.getFilePath();
//                if (cn.hutool.core.io.FileUtil.exist(filePath)) {
//                    cn.hutool.core.io.FileUtil.del(filePath);
//                }
//            }
//            resule++;
//        }
//        if (resule > 0) {
//            return success("删除成功！！！");
//        } else {
//            return failure("删除失败！！！");
//        }
//    }
//
//    @RequestMapping("/exportExcel")
//    public void exportExcel(HttpServletResponse response) {
//        List<Attachment> attachments = attachmentService.selectAll();
//
//        //PoiUtils.exportExcel(response, request.getParameter("excelTitle"), request.getParameter("colName"), request.getParameter("fieldName"), attachments);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/config")
//    public Object config(HttpServletRequest request, String action) throws Exception {
//        if ("config".equals(action)) {
//            return UeditorConfig.UEDITOR_CONFIG;
//        } else if ("uploadimage".equals(action) || "uploadscrawl".equals(action)) {
//            return imgUpload(request);
//        } else if ("uploadfile".equals(action)) {
//            return fileUpload(request);
//        }
//        return null;
//    }
//
//    /**
//     * @MethodName imgUpload
//     * @Description 富文本上传
//     * @Param request
//     * @Return java.util.Map<java.lang.String, java.lang.Object>
//     * @Author 明成
//     * @Date 2019/7/3 14:12
//     */
//    @RequestMapping(value = "/imgUpload")
//    @ResponseBody
//    public Map<String, Object> imgUpload(HttpServletRequest request) {
//        Map<String, Object> rs = new HashMap<String, Object>();
//        MultipartHttpServletRequest mReq = null;
//        MultipartFile file = null;
//        String fileName = "";
//        String originalFileName = "";
//
//        try {
//            mReq = (MultipartHttpServletRequest) request;
//            // 从config.json中取得上传文件的ID "imageFieldName": "upfile", /* 提交的图片表单名称 */
//            file = mReq.getFile("upfile");
//
//            if (!file.isEmpty()) {
//                // 取得文件的原始文件名称
//                originalFileName = file.getOriginalFilename();
//                fileName = originalFileName;
//
//                // 设置上传目录
//                String ext = (FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
//                String storePath = "";
//                if ("jpg".equals(ext) || "png".equals(ext) || "jpeg".equals(ext) || "bmp".equals(ext)) {
//                    storePath = "upload/image";
//                } else {
//                    storePath = "upload/video";
//                }
//                String uploadPath = getUploadDir(storePath);
//                //
//                ////获取服务器根目录 D:\wwwroot\java\ewsdSC\system\src\main\resources\static
//                //File path = new File(ResourceUtils.getURL("classpath:").getPath().replace("target/classes","src/main/resources/static"));
//                //// 编辑上传目录，使其生效
//                //File uploadPath = new File(path.getAbsolutePath(),storePath);
//                //System.out.println("upload url:"+uploadPath.getAbsolutePath());
//                // 获取新的文件名
//                String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + "_" + BaseUtils.getFixLengthNum(10000, 99999) + "." + ext;
//
//
//                //获取上传的图片具体路径
//                File targetFile = new File(uploadPath, newFileName);
//
//                //获取父目录
//                File pfile = new File(uploadPath);
//
//                //判断如果目录不存在，先创建
//                if (!pfile.exists()) pfile.mkdirs();
//
//                // 文件上传
//                file.transferTo(targetFile);
//
//                // 文件访问路径
//                String httpImgPath = uploadPath + "/" + fileName;
//                String filepath = "/" + uploadPath.substring(uploadPath.indexOf("uploads")) + newFileName;
//
//                rs.put("state", "SUCCESS"); // UEDITOR的规则:不为SUCCESS则显示state的内容
//                rs.put("url", "/system/attachment/showPic?path=" + URLEncoder.encode(filepath, "UTF-8")); //能访问到你现在图片的路径
//                rs.put("title", originalFileName);
//                rs.put("original", originalFileName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            rs.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息
//            rs.put("url", "");
//            rs.put("title", "");
//            rs.put("original", "");
//        }
//        return rs;
//
//    }
//
//    //文件上传
//    @RequestMapping(value = "/fileUpload")
//    @ResponseBody
//    public Map<String, Object> fileUpload(HttpServletRequest request) {
//        Map<String, Object> rs = new HashMap<String, Object>();
//        MultipartHttpServletRequest mReq = null;
//        MultipartFile file = null;
//        String fileName = "";
//        String originalFileName = "";
//
//
//        try {
//            mReq = (MultipartHttpServletRequest) request;
//            // 从config.json中取得上传文件的ID "imageFieldName": "upfile", /* 提交的图片表单名称 */
//            file = mReq.getFile("upfile");
//
//            if (!file.isEmpty()) {
//                // 取得文件的原始文件名称
//                originalFileName = file.getOriginalFilename();
//                fileName = originalFileName;
//
//                // 设置上传目录
//                String ext = (FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
//                String uploadPath = getUploadDir(ext);
//
//                // 获取新的文件名
//                String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + "_" + BaseUtils.getFixLengthNum(10000, 99999) + "." + ext;
//
//                //获取上传的图片具体路径
//                File targetFile = new File(uploadPath, newFileName);
//
//                //获取父目录
//                File pfile = new File(uploadPath);
//
//                //判断如果目录不存在，先创建
//                if (!pfile.exists()) pfile.mkdirs();
//
//                // 文件上传
//                file.transferTo(targetFile);
//
//                // 文件访问路径
//                String httpImgPath = uploadPath + "/" + fileName;
//                String filepath = "/" + uploadPath.substring(uploadPath.indexOf("uploads")) + newFileName;
//
//
//                save("ueditor", "fileUpload", request.getParameter("category"), request.getParameter("puuid"), ext, file.getSize(), fileName, file.getName(), filepath);
//
//                rs.put("state", "SUCCESS"); // UEDITOR的规则:不为SUCCESS则显示state的内容
//                rs.put("url", "/system/attachment/showPic?path=" + URLEncoder.encode(filepath, "UTF-8")); //能访问到你现在图片的路径
//                rs.put("title", originalFileName);
//                rs.put("original", originalFileName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            rs.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息
//            rs.put("url", "");
//            rs.put("title", "");
//            rs.put("original", "");
//        }
//        return rs;
//
//    }
//
//    @ApiOperation(value = "显示图片")
//    @RequestMapping("/showPic")
//    public ResponseEntity<byte[]> showPic(HttpServletRequest request) throws Exception {
//        String path = request.getParameter("path");
//
//        Attachment attachment = attachmentService.getAttachmentByPath(path);
//        String fileNameOrg = attachment == null ? "image" : attachment.getFileName();
//        if (path.indexOf(uploadDir()) == -1) {
//            path = uploadDir() + path;
//        }
//
//
//        File file = new File(path);
//        HttpHeaders headers = new HttpHeaders();
//        String fileName = new String(fileNameOrg.getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
//        headers.setContentDispositionFormData("attachment", fileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
//    }
//
//    @ApiOperation("根据Execl地址获取文件解析Excel")
//    @ResponseBody
//    @RequestMapping(value = "/analysisExecl")
//    public List<Map<String, String>> analysisExecl(String address) throws Exception {
//        //获取系统判断存储地址
//        File pdfFile = null;
//        if (isWin) {
//            pdfFile = new File("D:/zysd" + address);
//        } else {
//            pdfFile = new File("/home/zysd" + address);
//        }
//        logger.info("上传文件读取地址{}", pdfFile);
//        //解析Excel文件数据
//        FileInputStream fileInputStream = new FileInputStream(pdfFile);
//        MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//        //调ImportExeclUtil工具类
//        List<Map<String, String>> varList = ImportExeclUtil.readExcel(multipartFile, 1, 1, 0);
//        return varList;
//    }
//}
