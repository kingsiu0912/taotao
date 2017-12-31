package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbContentCategory;

import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import com.taotao.utils.T_Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品规格参数
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    /**
     * //根据cid查询规格参数模板
     * @param cid
     * @return
     */
    @Override
    public T_Result getItemParamService(Long cid) {
//根据cid查询规格参数模板
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        //执行查询
        List<TbItemParam>list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list != null&&list.size() > 0) {
            TbItemParam itemParam = list.get(0);
            return T_Result.ok(itemParam);
        }
        return T_Result.ok();
    }

    /**
     *
     规格模板设置
     * @param cid
     * @param paremData
     * @return
     */
    @Override
    public T_Result insertItemParam(Long cid, String paremData) {
        //创建pojo对象
TbItemParam itemParam=new TbItemParam();
itemParam.setItemCatId(cid);
itemParam.setParamData(paremData);
itemParam.setCreated(new Date());
itemParam.setUpdated(new Date());

//插入记录


        itemParamMapper.insert(itemParam);

        return T_Result.ok();
    }

    /**
     *---------------------------------------------------------------------------------
     // 从tb_item_param中查询数据展示到jsp，单表查询，实现分页。
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemPparamList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //查询
        TbItemParamExample example=new TbItemParamExample();
        List<TbItemParam> list= itemParamMapper.selectByExampleWithBLOBs(example);
        //取分页信息
        PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;

    }

    /**
     * ----------------------------------------------------------------》》》》》》》》》》》》》
     * 规格参数的修改service层
     * @param id
     * @param name
     * @return
     */
    @Override
    public T_Result updateCatgory(Long id, String name) {

        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        category.setName(name);
        contentCategoryMapper.updateByPrimaryKey(category);

        return T_Result.ok();
    }

    /**
     * ---------------------------------------------------------------------------------
     * @param id
     * @return
     */
    @Override
    public T_Result deleteCatgory(Long id) {
        deleteCatgoryAndChildNode(id);
        return T_Result.ok();
    }

    /**
     *删除参数规格的节点
     * @param id
     * @return
     */
    @Override
    public List<TbContentCategory> getChildNodeList(Long id) {
        TbContentCategoryExample example=new TbContentCategoryExample()
;

        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        return list;
    }

    @Override
    public void deleteCatgoryAndChildNode(Long id) {
        TbContentCategory tbContentCategory=contentCategoryMapper.selectByPrimaryKey(id);
        if(tbContentCategory.getIsParent()){
            List<TbContentCategory>list=getChildNodeList(id);
            for (TbContentCategory category:list){
                deleteCatgoryAndChildNode(category.getId());

            }
        }
        //判断父节点下面是不是有其他的节点
        if(getChildNodeList(tbContentCategory.getParentId()).size()==1){
            TbContentCategory tbContentCategory1 = contentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
            tbContentCategory1.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(tbContentCategory1);



        }
        //删除本节点
        contentCategoryMapper.deleteByPrimaryKey(id);
        return;

    }
}
