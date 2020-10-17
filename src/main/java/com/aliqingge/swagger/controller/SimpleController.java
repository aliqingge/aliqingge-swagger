package com.aliqingge.swagger.controller;

import com.aliqingge.swagger.dto.UserDTO;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author Administrator
 * @date 2020/10/13 11:43
 */
@Api(tags = {"API文档"})
@RestController
public class SimpleController {

    @ApiIgnore
    @GetMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }

    @ApiOperation(value = "welcome", produces = MediaType.TEXT_PLAIN_VALUE, notes = "返回：Hello swagger")
    @GetMapping("/welcome")
    public String home() {
        return "Hello, nice to meet you!";
    }

    @ApiOperation(value = "查询所有用户", produces = MediaType.TEXT_PLAIN_VALUE, notes = "返回：所有用户数据")
    @GetMapping("/user/all")
    public List<UserDTO> getUserAll() {
        return null;
    }

    @ApiOperation(value = "根据id查询用户", produces = MediaType.TEXT_PLAIN_VALUE, notes = "返回：用户数据")
    @GetMapping("/user/{id}")
    public UserDTO getUserById(@ApiParam(value = "用户id", required = true, example = "1") @PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "保存用户", produces = MediaType.APPLICATION_JSON_VALUE, notes = "返回：新增用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "授权码", required = true, dataType = "string", paramType = "header", example = "abc"),
            @ApiImplicitParam(name = "id", value = "用户id", dataType = "string", paramType = "query", example = "123456"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "参数填写错误"),
            @ApiResponse(code = 500, message = "接口异常")
    })
    @PostMapping("/user/save")
    public Long saveUser(@RequestBody UserDTO user) {
        return 1L;
    }

    @ApiOperation(value = "根据id删除用户", produces = MediaType.TEXT_PLAIN_VALUE, notes = "无返回")
    @DeleteMapping("/user/delete")
    public void deleteUser(
            @ApiParam(value = "用户id", required = true, example = "1")
            @RequestParam("id") Long id) {
    }

    @GetMapping("/test")
    public String test(HttpServletResponse response) {
        //创建一个 cookie
        Cookie cookie = new Cookie("username", "Jovan");
        //设置 cookie过期时间 expires in 7 days
//        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setMaxAge(60);
        //添加到 response 中
        response.addCookie(cookie);

        return "Username is changed!";
    }
}
