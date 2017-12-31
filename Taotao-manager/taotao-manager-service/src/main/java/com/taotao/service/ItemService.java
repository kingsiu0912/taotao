package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.utils.T_Result;


public interface ItemService {


    //测试项目是否成功的接口
    TbItem getItemById(long itemId);

    //商品分页查询  加展示的接口

    EasyUIDataGridResult getItemList(int page,int rows);


    //商品的id和商品的描述  提交商品的页面

    T_Result createItem(TbItem item,String desc);


}
