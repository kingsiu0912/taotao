package com.taotao.Controller;

import com.taotao.common.pojo.EasyUIDataGridResult;

import com.taotao.service.ItemParamService;
import com.taotao.utils.T_Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 商品参数规格的controller
 */
@Controller
public class ItemParamcontroller {
    @Autowired
    private ItemParamService itemParamService;


    @RequestMapping("/item/param/query/itemcatid/{cid}")
    @ResponseBody
//    public T_Result getItemCatByCid(@PathVariable Long cid) {
//
//        T_Result result = this.itemParamService.getItemParamService(cid);
    public T_Result getItemParamService(@PathVariable Long cid) {
        T_Result result = itemParamService.getItemParamService(cid);

        return result;
    }

    /**
     * 5.3.2.3提交规格参数模板
     */

    @RequestMapping("/item/param/save/{cid}")
    @ResponseBody
    public T_Result insertItemParam(@PathVariable Long cid, String paramData) {

        T_Result result = itemParamService.insertItemParam(cid, paramData);

        return result;
    }

    /**
     * 分页显示页面的实现
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemPparamList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        EasyUIDataGridResult result = itemParamService.getItemPparamList(page, rows);
        return result;
    }

    /**------------------------------------------------------------------------------------》》》》
     * 参数规格的controller实现
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/content/category/update")
    @ResponseBody
    public T_Result updateCatgory(Long id, String name) {
        T_Result result = itemParamService.updateCatgory(id, name);
        return result;

    }

    @RequestMapping("/content/category/delete/")
    @ResponseBody
    public  T_Result deleteCatgory(Long id){
        T_Result result = itemParamService.deleteCatgory(id);
        return  result;
    }
}
