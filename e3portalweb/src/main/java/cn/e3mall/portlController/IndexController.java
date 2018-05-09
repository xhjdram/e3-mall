package cn.e3mall.portlController;

import cn.e3mall.content.service.Content;
import cn.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Value("${CategoryId}")
    Long category;
    @Autowired
    Content content;

    @RequestMapping("/index")
    public String showIndexPage(Model model) {
        List<TbContent> tbcontentByCategoryId = content.findTbcontentByCategoryId(category);
        model.addAttribute("ad1List", tbcontentByCategoryId);
        return "index";
    }
}
