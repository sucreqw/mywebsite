package com.sucre.cool.mywebsite.controller;

import com.sucre.cool.mywebsite.common.FileUtil;
import com.sucre.cool.mywebsite.entity.ThirdPlatformDO;
import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.ThirdPlatformInfo;
import com.sucre.cool.mywebsite.info.fileUploadInfo;
import com.sucre.cool.mywebsite.service.IThirdPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/common")
public class CommonController {

    /*@Value("${apiKey.sinaKey}")
    private String APPID;
    @Value("${apiKey.redirectURI}")
    private String REDIRECT_URI;
*/
    @Autowired
    IThirdPlatformService iThirdPlatformService;

    @PostMapping("/upload")
    public CommonResult<fileUploadInfo> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BizException(ResultCodeEnum.FILE_ERROR);
        }
        CommonResult<fileUploadInfo> result = new CommonResult<>();
        fileUploadInfo fileUploadInfo = new fileUploadInfo();

        String fileName = System.currentTimeMillis() + ".jpg";

        try {
           // File target = new File(ResourceUtils.getURL("classpath:static").getPath() + "/weiboimg/" + fileName);
            File target = new File(FileUtil.getCurrentPath() + "/weiboimg/" + fileName);
            System.out.println(target);
            file.transferTo(target);
        } catch (Exception e) {
            throw new BizException(505, e.getMessage());
        }
        fileUploadInfo.setName(fileName);
        fileUploadInfo.setUrl("/weiboimg/" + fileName);
        result.setData(fileUploadInfo);
        return result;
    }

    @GetMapping("/loginurl")
    public CommonResult<String> loginUrl() {
        ThirdPlatformInfo thirdPlatformInfo=iThirdPlatformService.getThirdPlatformByName("sina");
        CommonResult<String> result = new CommonResult<>();
        String url = "https://api.weibo.com/oauth2/authorize?client_id=" + thirdPlatformInfo.getAppId() + "&response_type=code&redirect_uri=" + thirdPlatformInfo.getRedirect();
        result.setData(url);
        return result;
    }
}
