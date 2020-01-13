package com.fh;

import com.fh.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//设置全局的异常处理
public class BaseController {

    private static final Logger log= LoggerFactory.getLogger(BaseController.class);
    @ExceptionHandler(Exception.class)

    @ResponseBody
    public ResponseData handleException(Exception e){
        e.printStackTrace();
        log.error("异常了"+e.getMessage());
        return ResponseData.error(e);
    }

}
