package cn.xp1997.xp.blog.controller.admin;


import cn.xp1997.xp.sys.base.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author xp
 * @since 2020-01-08
 */
@RestController
@RequestMapping("/article")
public class XpArticleController {

    @RequiresPermissions("/article/list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(){
        try {
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

}
