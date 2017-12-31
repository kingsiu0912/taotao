package com.taotao.Controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

import com.taotao.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class Testpagehelper {

    /**
     * spring/applicationContext-dao.xml
     */
    @org.junit.Test
    public void cc() throws Exception {
        //1、获得mapper代理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext
                ("classpath:spring/applicationContext-*.xml");
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        //2、设置分页
        PageHelper.startPage(1, 30);
        //3、执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //4、取分页后结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("total:" + total);
        int pages = pageInfo.getPages();
        System.out.println("pages:" + pages);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize:" + pageSize);
    }

    @org.junit.Test
    public void fasdsa() throws  Exception{
        ClientGlobal.init("F:\\taotao\\Taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        // 3、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7、直接调用StorageClient对象方法上传文件即可。
        String[] strings = storageClient.upload_file("F:\\我的网盘文件\\OneDrive\\图片\\屏幕快照\\2017-08-22.png", "png", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    @org.junit.Test
    public void dfs()throws  Exception{

        FastDFSClient client=new FastDFSClient("F:\\taotao\\Taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        String up=client.uploadFile("C:\\Users\\dengs\\Pictures\\t01b364bf7873ae24d6.jpg");
        System.out.println(up);
    }
}


