package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.utils.T_Result;

import java.util.List;

public interface ItemParamService {

    //商品规格参数的查询
    T_Result getItemParamService(Long cid);


    //规格模板设置

    T_Result insertItemParam(Long cid,String paremData);



   // 从tb_item_param中查询数据展示到jsp，单表查询，实现分页。


   EasyUIDataGridResult getItemPparamList(int page, int rows);


   //规格参数的更改
    T_Result updateCatgory(Long id,String name);


    //删除规格参数



    T_Result deleteCatgory(Long id);
    List<TbContentCategory>getChildNodeList(Long id);
    void deleteCatgoryAndChildNode(Long id);



}
