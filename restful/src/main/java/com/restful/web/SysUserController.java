package com.restful.web;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.core.entity.ErrorResult;
import com.core.entity.HttpResult;
import com.core.entity.Result;
import com.restful.entity.SysUser;
import com.restful.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  * 
 *   ┏ ┓   ┏ ┓
 *  ┏┛ ┻━━━┛ ┻┓
 *  ┃         ┃
 *  ┃    ━    ┃
 *  ┃  ┳┛  ┗┳ ┃
 *  ┃         ┃
 *  ┃    ┻    ┃
 *  ┃         ┃
 *  ┗━━┓    ┏━┛
 *     ┃    ┃神兽保佑
 *     ┃    ┃代码无BUG！
 *     ┃    ┗━━━━━━━┓
 *     ┃            ┣┓
 *     ┃            ┏┛
 *     ┗┓┓┏━━━━━━┳┓┏┛
 *      ┃┫┫      ┃┫┫
 *      ┗┻┛      ┗┻┛
 * @since 2018-08-12
 */
@RestController
@RequestMapping("/api/users")
public class SysUserController extends BaseController{

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("")
    public HttpResult index(HttpServletRequest request, SysUser sysUser) {
        EntityWrapper<SysUser> userEntityWrapper = new EntityWrapper<SysUser>();
        userEntityWrapper.like("username", sysUser.getUsername(), SqlLike.DEFAULT)
                .like("email", sysUser.getEmail(), SqlLike.DEFAULT);
        Page<SysUser> userPage = new Page<SysUser>(sysUser.getCurrentPage(), 10);
        return Result.OK(sysUserService.selectPage(userPage, userEntityWrapper));
    }

    @GetMapping("/current")
    public HttpResult current(HttpServletRequest request) {
        return Result.OK(sysUserService.current(request));
    }

    @GetMapping("/{id}")
    public HttpResult details(@PathVariable Integer id) {
        SysUser user = sysUserService.selectById(id);
        user.setPassword(null);
        return Result.OK(user);
    }

    @PutMapping("/{id}")
    public HttpResult update(HttpServletRequest request, HttpServletResponse response, @RequestBody SysUser user, @PathVariable Integer id) {
        if (sysUserService.updateById(user)) {
            return Result.OK(request, "修改用户成功!");
        } else {
            return ErrorResult.EXPECTATION_FAILED("操作未完成，请检查参数");
        }

    }

    @PostMapping()
    public HttpResult save(HttpServletRequest request, @RequestBody SysUser user){
        if(sysUserService.insert(user)){
            return Result.OK(request, "新建用户成功");
        }else{
            return ErrorResult.EXPECTATION_FAILED();
        }
    }

    @DeleteMapping("/{id}")
    public HttpResult delete(HttpServletRequest request, @PathVariable Integer id){
        if (sysUserService.deleteById(id)){
            return Result.OK(request, "删除用户成功!");
        }else{
            return ErrorResult.EXPECTATION_FAILED();
        }

    }
}

