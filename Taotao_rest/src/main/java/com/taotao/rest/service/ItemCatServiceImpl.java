package com.taotao.rest.service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.pojo.catNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public ItemCatResult getitemCatList() {
        List catlist = getItemCatList(01);
        ItemCatResult result = new ItemCatResult();

        result.setData(catlist);


        return result;
    }

    /**
     *
     * @param psrntId
     * @return
     */
    private List getItemCatList(long psrntId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(psrntId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        List resulist = new ArrayList();

        //如果是父节点
        for (TbItemCat tbItemCat : list) {

            catNode node = new catNode();
            node.setUrl("/products/" + tbItemCat.getId() + ".html");
            //如果当前节点是第一个节点
            if (tbItemCat.getIsParent()) {


                if (tbItemCat.getParentId() == 0) {
                    node.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");


                } else {
                    node.setName(tbItemCat.getName());
                }
                node.setItems(getItemCatList(tbItemCat.getId()));
                resulist.add(node);


            } else {
                //如果不是父节点
                String item = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                resulist.add(item);

            }
        }
        return resulist;
    }
}