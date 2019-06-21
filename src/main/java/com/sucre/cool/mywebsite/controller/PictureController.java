package com.sucre.cool.mywebsite.controller;


import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.FileUtil;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.PictureDTO;
import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.PictureInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/backup")
public class PictureController {

    @RequiredRole(RoleConstants.SYSUSER)
    @GetMapping("/")
    public CommonResult<List<PictureInfo>> getPictureFile() {
        CommonResult<List<PictureInfo>> result = new CommonResult<>();
        List<PictureInfo> resultList = new ArrayList<>();
        String path;
        try {
            //path = ResourceUtils.getURL("classpath:static").getPath() + "/weiboimg/";
            path = FileUtil.getCurrentPath() + "/weiboimg/";
            // System.out.println(getCurrentPath());
            if (StringUtils.isNotBlank(path)) {
                ArrayList<String> list = getFiles(path);
                for (String name : list) {
                    PictureInfo pictureInfo = new PictureInfo();
                    pictureInfo.setFileName(name);
                    resultList.add(pictureInfo);
                }
                result.setData(resultList);
            }
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.FILE_ERROR);
        }
        return result;
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/change")
    public void changeFileName(@RequestBody PictureDTO pictureDTO) {
        String fileName = pictureDTO.getFileName();
        String newName = pictureDTO.getNewName();
        try {
            //String path = ResourceUtils.getURL("classpath:static").getPath() + "/weiboimg/" + fileName;
            //String newPath = ResourceUtils.getURL("classpath:static").getPath() + "/weiboimg/" + newName;
            String path = FileUtil.getCurrentPath() + "/weiboimg/" + fileName;
            String newPath = FileUtil.getCurrentPath() + "/weiboimg/" + newName;
            if (StringUtils.isNotBlank(path)) {
                File file = new File(path);
                file.renameTo(new File(newPath));
            }
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.FILE_ERROR);
        }
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/delete")
    public void deleteFile(@RequestBody PictureDTO pictureDTO) {
        String fileName = pictureDTO.getFileName();
        try {
            //String path = ResourceUtils.getURL("classpath:static").getPath() + "/weiboimg/" + fileName;
            String path = FileUtil.getCurrentPath() + "/weiboimg/" + fileName;
            if (StringUtils.isNotBlank(path)) {
                File file = new File(path);
                file.delete();
            }
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.FILE_ERROR);
        }

    }

    private ArrayList<String> getFiles(String path) throws Exception {
        //目标集合fileList
        ArrayList<String> fileList = new ArrayList<>();
        //ArrayList<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath());
                } else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex.getName());
                }
            }
        }
        return fileList;
    }


}
