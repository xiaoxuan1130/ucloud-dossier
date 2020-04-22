package com.epipe.ucloud.dossier.utils;

import com.epipe.ucloud.dossier.utils.id.IdUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 七牛简单工具类
 * @author gongtao
 * @version 2018-03-14 15:45
 **/
public class QiniuUtil {

    private static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);
    private static Properties properties = new Properties();
    private static final String FULL_STOP = ".";

    static {
        try {
            properties.load(QiniuUtil.class.getClassLoader().getResourceAsStream("properties/qiniu.properties"));
        } catch (IOException e) {
            logger.error("load qiniu.properties fail",e);
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

    /**
     * 上传文件并返回文件全路径
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static String uploadFileByStream(InputStream inputStream) throws Exception {
        return uploadFileByStream(inputStream,true);
    }

    /**
     * 上传文件
     * @param inputStream
     * @param appendDomain
     * @return
     * @throws Exception
     */
    public static String uploadFileByStream(InputStream inputStream, boolean appendDomain) throws Exception {
        Response resp = null;
        String bucket = getValue("bucket");
        try {
            String key = String.valueOf(IdUtil.getId());
            String token = Auth.create(getValue("accessKey"), getValue("secretKey")).uploadToken(bucket, key);
            UploadManager manager = new UploadManager(new Configuration());
            resp = manager.put(inputStream, key, token, null, null);
            logger.info("uploadLocalFile inputStream success: bucket={}|response={}", bucket, resp.bodyString());
            return appendDomain ? getValue("domain") + key : key;
        } catch (Exception e) {
            logger.error("error when upload inputStream bucket={}|resp={}", bucket, resp, e);
            throw new Exception(e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 文件上传，以文件名作为识别码，并返回文件全路径
     * @param inputStream
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String uploadFileWithExt(InputStream inputStream, String fileName) throws Exception{
        Response resp = null;
        String bucket = getValue("bucket");
        try {
            String key;
            if (fileName.contains(FULL_STOP)){
//                String prefix = fileName.substring(0,fileName.indexOf(FULL_STOP));
//                String suffix = fileName.substring(fileName.indexOf(FULL_STOP));
//                key = prefix.concat(IdUtil.getId() + "").concat(suffix);
                key=fileName;
            }else {
                key = fileName.concat(IdUtil.getId() + "");
            }
            String token = Auth.create(getValue("accessKey"), getValue("secretKey")).uploadToken(bucket, key);
            UploadManager manager = new UploadManager(new Configuration());
            resp = manager.put(inputStream, key, token, null, null);
            logger.info("uploadLocalFile inputStream success: bucket={}|response={}", bucket, resp.bodyString());
            return getValue("domain") + key ;
        } catch (Exception e) {
            logger.error("error when upload inputStream bucket={}|resp={}", bucket, resp, e);
            IOUtils.closeQuietly(inputStream);
            throw new Exception(e);
        }
    }

}
