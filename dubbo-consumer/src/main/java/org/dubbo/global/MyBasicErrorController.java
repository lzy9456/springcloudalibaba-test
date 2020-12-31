package org.dubbo.global;

import com.example.entity.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author _lizy
 * @version 1.0
 * @description 没进controller之前自定义报错处理，404、500，默认对应前端静态html页面
 * @date 2020/12/24 23:30
 */
@Slf4j
@RestController
public class MyBasicErrorController implements ErrorController {

//    public MyBasicErrorController() {
//        super(new DefaultErrorAttributes(), new ErrorProperties());
//    }

    private final static String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(path  = ERROR_PATH )
    public Result error(HttpServletRequest request, HttpServletResponse response){
        log.info("/error  错误代码：{} - {}", response.getStatus(), request.getRemoteAddr());
        return Result.fail(String.valueOf(response.getStatus()));
    }






    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

//
//    @RequestMapping(produces = "text/html",value = "/404")
//    @ResponseBody
//    public ResponseEntity errorHtml404(HttpServletRequest request, HttpServletResponse response) {
//        log.error("404 >>>>>>>>>>>>>>>>>>>>> NOT FOUND: {}", request.getRequestURI());
//        return ResponseEntity.notFound().build();
//    }
//    /**
//     * 定义500的错误JSON信息
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/404")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error404(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }
//
//    /**
//     * 定义500的ModelAndView
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(produces = "text/html",value = "/500")
//    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> model = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        model.put("msg","自定义错误信息");
//        return new ModelAndView("error/500", model);
//    }
//
//
//
//    /**
//     * 定义500的错误JSON信息
//     * @param request
//     * @return
//     */
//
//    @RequestMapping(value = "/500")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }
}
