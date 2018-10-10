package com.console.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.common.constant.ConfigConstant;
import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.modules.sys.model.SysConfig;
import com.console.modules.sys.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:24 2018/9/25
 * @Modified By:
 */
@Slf4j
@Controller
@RequestMapping("/sys/file")
public class UploadController {
    @Autowired
    private SysConfigService sysConfigService;

    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultGenerator.genFailedResult("文件为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        SysConfig uploadType = sysConfigService.getOne(new QueryWrapper<SysConfig>().eq("param_key", ConfigConstant.FILE_UPLOAD_TYPE));
        if (Integer.valueOf(uploadType.getParamValue()) == 0) {
            // 服务器路径
            SysConfig path = sysConfigService.getOne(new QueryWrapper<SysConfig>().eq("param_key", ConfigConstant.SERVER_UPLOAD_PATH));
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;

            File dest = new File(path + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            try {
                file.transferTo(dest);
                return ResultGenerator.genOkResult("上传成功");
            } catch (IllegalStateException e) {
                log.error("上传文件异常", e);
            } catch (IOException e) {
                log.error("上传文件IO异常", e);
            }
            return ResultGenerator.genFailedResult("上传失败");
        } else {
            // 云上传
            return ResultGenerator.genOkResult();
        }
    }

}
