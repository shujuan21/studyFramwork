package com.shujuan.studyframework.common.exception;

import com.shujuan.studyframework.api.IndexApi;
import com.shujuan.studyframework.common.result.ExceptionEnum;
import com.shujuan.studyframework.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 自定义异常处理
 *
 * @Auther: guany
 * @Date: 2023/02/11
 */
@RestControllerAdvice
public class GlobleExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(IndexApi.class);

    /**
     *自定义异常处理
     */
    @ExceptionHandler(value = CustomException.class)
    public Result customExceptionHandler(HttpServletRequest req, CustomException e){
        return Result.error(e.getCode(),e.getMessage());
    }

    /**
     * 参数校验异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result validationExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new Result(ExceptionEnum.ParamError.getCode(), fieldError.getDefaultMessage());
            }
        }
        return new Result(ExceptionEnum.ParamError.getCode(),ExceptionEnum.ParamError.getMsg());
    }

    /**
     *其他异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return Result.error(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), ExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
    }

}
