package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    /**
     * 返回图片url
     * 避免报错，随机返回字符串
     * @return {@link Result}
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file)
    {
        log.info("生成随机字符串");
        String url=UUID.randomUUID().toString()+"-"+file.getName()+".jpg";
        return Result.success(url);
    }
}
