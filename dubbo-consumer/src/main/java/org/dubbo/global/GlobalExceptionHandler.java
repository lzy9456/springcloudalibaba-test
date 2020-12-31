package org.dubbo.global;

import com.example.entity.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author _lizy
 * @version 1.0
 * @description 全局异常处理器，进controller之后的报错异常处理
 * @date 2020/12/19 20:05
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {



    /**
     * 单个参数校验异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException ex){
        log.error("参数校验异常",ex);
        return Result.fail(getConsValidMsg(ex));
    }


    /**
     * @param ex
     * @return
     * @RequestBody json数据接收绑定异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("参数校验异常",ex);

        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
        return Result.fail(errorMsg);
    }



    /**
     * 参数 bean validation异常
     * @param bindException
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result bindException(BindException bindException) {
        log.error("Params bind exception >>>>>>>>>>>>>>>>>>>>> ", bindException);
        return Result.fail(getBindExMsg(bindException));
    }



    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception ex){
        if(ex instanceof RuntimeException){
            log.error( "抛出自定义异常",ex);
        }else {
            log.error( "系统内部异常",ex);
        }
        return Result.fail();
    }



    /*********************
     * 自定义异常处理
     *********************/
    /**
     * @Description 自定义异常处理
     *
     * @param ex
     * @Return com.example.entity.result.Result
     * @Author _lizy
     * @Date 2020/12/19 21:02
     */
//    @ExceptionHandler(value = {BusinessException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result constraintBusinessException(BusinessException ex) {
//        log.error("Business exception >>>>>>>>>>>>>>>>>>>>> errorCode: {}, errorDesc: {}", ex.getErrorCode(), ex.getMessage(), ex);
//        return ResponseUtils.getErrorReturnMsg(ex.getErrorCode(), ex.getMessage(), null, ExceptionUtils.getStackTrace(ex));
//    }

    @ExceptionHandler(value = {SystemException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result constraintSystemException(SystemException ex) {
        log.error("System exception >>>>>>>>>>>>>>>>>>>>> errorDesc: {}", ex.getCause().getMessage(), ex);
        return Result.fail(ex.getMessage());
    }



//    @ExceptionHandler(value = {NoLoginException.class})
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Result constraintNoLoginException(NoLoginException ex) {
//        log.error("token exception >>>>>>>>>>>>>>>>>>>>> ", ex);
//        return Result.fail(ex.getMessage());
//    }
//
//    @ExceptionHandler(value = {FeignException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result constraintFeignException(Exception ex) {
//        log.error("Feign exception >>>>>>>>>>>>>>>>>>>>> ", ex);
//        return Result.fail(ex.getMessage());
//    }












    private String getConsValidMsg(ConstraintViolationException ex) {
        List<String> msgs = ex.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList());

        return Optional.ofNullable(msgs).map(d -> d.get(0)).orElse("");
    }

    private String getBindExMsg(BindException bindException) {
        List<String> msgs = bindException.getBindingResult()
                                        .getAllErrors()
                                        .stream()
                                        .map(ObjectError::getDefaultMessage)
                                        .collect(Collectors.toList());

        return Optional.ofNullable(msgs).map(d -> d.get(0)).orElse("");
    }

}
