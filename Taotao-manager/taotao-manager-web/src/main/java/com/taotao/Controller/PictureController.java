package com.taotao.Controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 接收上传的图片信息，
 * 调用Service把图片上传到图片服务器。
 * 返回json数据。需要使用@ResponseBody
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;



    @RequestMapping("/pic/upload")
    @ResponseBody

    public String uploadFile(MultipartFile uploadFile) {
        PictureResult result = pictureService.uploadPic(uploadFile);
        System.out.println(result);

        String json = JsonUtils.objectToJson(result);
        return json;

    }
}
