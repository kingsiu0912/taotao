package com.taotao.Controller;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.T_Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品查询 ItemController
 *
 *
 * 商品分页查询的页面
 *
 *
 * 商品查询页面
 *
 *
 *
 * 商品添加的页面
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    /**
     *
     */
    private TbItem getItemById(@PathVariable long itemId){
        TbItem item=itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
        EasyUIDataGridResult result =itemService.getItemList(page, rows);
        return result;
    }


    /**
     * 商品添加页面
     *
     */
@RequestMapping(value = "/item/save",method = RequestMethod.POST)
@ResponseBody

    public T_Result createItem(TbItem item,String desc){
    T_Result result = itemService.createItem(item, desc);
    return result;
    }

}
