package com.taotao.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 页面的展示和页面的跳转
 */
@Controller
public class PageController {
  @RequestMapping("/")
   public String showindex(){
      return "index";
  }
  //页面的跳转批量处理
@RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
       return page;
}
}
