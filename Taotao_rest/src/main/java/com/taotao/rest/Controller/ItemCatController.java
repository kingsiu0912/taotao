package com.taotao.rest.Controller;


import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("item/cat")
/**
 *
 */
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatResult(String callback) {

        ItemCatResult result = itemCatService.getitemCatList();
        if(StringUtils.isBlank(callback)){
            //把result转成字符串
            String json = JsonUtils.objectToJson(result);
            return  json;
        }
        //如果字符串不为空 需要支持json调用
        //需要把result转换成字符串
        String json = JsonUtils.objectToJson(result);
        return  callback+"("+json+");";
    }


}
