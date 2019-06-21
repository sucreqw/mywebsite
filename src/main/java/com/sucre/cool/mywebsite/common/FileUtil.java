package com.sucre.cool.mywebsite.common;

import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;

import java.io.File;

public class FileUtil {
    public static String getCurrentPath() {
        File directory = new File("");//设定为当前文件夹
        try {
            //System.out.println(directory.getCanonicalPath());//获取标准的路径
            return directory.getAbsolutePath();//获取绝对路径
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.FILE_ERROR);
        }
    }
}
