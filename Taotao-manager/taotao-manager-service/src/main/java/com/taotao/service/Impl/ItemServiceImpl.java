package com.taotao.service.Impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import com.taotao.utils.T_Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    //注入mapper

   @Autowired
   private TbItemMapper itemMapper;
    @Autowired
   private TbItemDescMapper tbItemDescMapper;
    @Override
    /**
     *测试方法
     */

    public TbItem getItemById(long itemId) {
    TbItemExample example=new TbItemExample();
    Criteria criteria=example.createCriteria();
    criteria.andIdEqualTo(itemId);
    List<TbItem> list = itemMapper.selectByExample(example);
    //判断list是不是空
        TbItem item=null;
        if(list!=null&&list.size()>0) {
            item = list.get(0);
        }
    return item;
}

    /**
     * 商品查询分页插件
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        TbItemExample example=new TbItemExample();
         List<TbItem> list= itemMapper.selectByExample(example);
         //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 商品提交页面  商品id的生成和商品描述页面
     * @param item
     * @param desc
     * @return
     */
    @Override
    public T_Result createItem(TbItem item, String desc) {
        //生成一个商品id
        long itemId = IDUtils.genItemId();
        //补全tbitem属性
        item.setId(itemId);
        //商品状态 1*正常 2*下架 3*删除
        item.setStatus((byte) 1);
        //创建时间和跟新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入商品表
        itemMapper.insert(item);
        //商品描述
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //插入商品描述信息
        return T_Result.ok();
    }
}
