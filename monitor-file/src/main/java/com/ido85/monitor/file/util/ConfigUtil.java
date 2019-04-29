package com.ido85.monitor.file.util;

import com.alibaba.fastjson.JSON;
import com.ido85.monitor.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
public class ConfigUtil {

    /**
     * 获取配置文件源内容
     * @param configFilePath 配置文件在服务器上的路径
     * @return
     * @throws Exception
     */
    public static String getConfigSource(String configFilePath) throws Exception {
        if (StringUtils.isNotBlank(configFilePath)) {
            File file = new File(configFilePath);
            return getConfigSource(file);
        }
        throw new BusinessException("请检查配置文件路径是否正确");
    }

    public static String getConfigSource(File file) throws Exception{
        BufferedReader br = null;
        try {
            if (file != null && file.exists()){
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (String configLine = br.readLine();configLine != null;){
                    sb.append(configLine).append("\n");
                    configLine = br.readLine();
                }
                return sb.toString();
            }
            throw new BusinessException("未能读取配置文件，请检查配置的文件是否可正确读取");
        }catch (Exception e){
            throw new BusinessException("未能读取配置文件，请检查配置的文件是否可正确读取");
        }finally {
            if (br != null){
                br.close();
            }
        }
    }

    public static void putConfigSource(String filePath,String configYaml) throws Exception{
        putConfigSource(filePath,configYaml,true,false);
    }

    public static void putConfigSource(File file,String configYaml) throws Exception{
        putConfigSource(file,configYaml,true,false);
    }
    public static void putConfigSource(String filePath,String configYaml,boolean createFlag,boolean appendFlag) throws Exception{
        if (StringUtils.isNotBlank(filePath)){
            File file = new File(filePath);
            putConfigSource(file,configYaml,createFlag,appendFlag);
        }else {
            throw new BusinessException("请检查配置文件路径是否正确");
        }
    }

    public static void putConfigSource(File file,String configYaml,boolean createFlag,boolean appendFlag) throws Exception{
        File bkFile = null;
        if (!file.exists()){
            if (createFlag){
                file.createNewFile();
            }else{
                throw new BusinessException("文件未找到");
            }
        }else{
            // 如果之前文件已经存在，则先备份
            bkFile = new File(file.getPath()+".bk");
            file.renameTo(bkFile);
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file,appendFlag));
            bw.write(configYaml);
            bw.flush();
        }catch (Exception e){
            if (null != bw) {
                bw.close();
            }
            // 如果修改文件异常，则删除该文件
            if(file != null && file.exists()){
                file.delete();
            }
            // 修改文件异常，则将备份文件还原
            if(bkFile != null){
                bkFile.renameTo(file);
            }
            throw new BusinessException("未能写入配置文件，请检查配置的文件是否可正确写入");
        }finally {
            if (null != bw){
                bw.close();
            }
            // 删除备份文件
            if (bkFile != null){
                bkFile.delete();
            }
        }
    }



    public static String handlePattern(String fileName) throws Exception{
        String pattern = fileName.replaceAll("\\.","\\\\.");
        pattern = pattern.replaceAll("\\*","(.*)");
        return pattern;
    }


    public static Object handleNoNull(Object object){
        String str = JSON.toJSONString(object);
        return JSON.parse(str);
    }

}
