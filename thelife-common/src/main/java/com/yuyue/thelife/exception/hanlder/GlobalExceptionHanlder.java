package com.yuyue.thelife.exception.hanlder;

import com.yuyue.thelife.result.JsonRestResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 11:29
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHanlder {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHanlder.class);

    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(TheLifeException.class)
    public JsonRestResponseVo handleSecurityException(TheLifeException e) {
        return buildJsonRestResponseVo(e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonRestResponseVo hanlderMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        logger.info("MethodArgumentNotValidException:" + allErrors.get(0).getDefaultMessage());
        return buildJsonRestResponseVo(allErrors.get(0).getDefaultMessage());
    }

    /**
     * springsecurity异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public JsonRestResponseVo badCredentialsException(BadCredentialsException e) {
        return buildJsonRestResponseVo(e.getMessage());
    }

    /**
     * 统一返回json
     *
     * @param msg
     * @return
     */
    public JsonRestResponseVo buildJsonRestResponseVo(String msg) {
        return JsonRestResponseVo.build(400, msg);
    }

}
