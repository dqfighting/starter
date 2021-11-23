package com.irving.starter;

import com.irving.starter.config.CommonConfig;
import com.irving.starter.exception.IRestControllerException;
import org.springframework.context.annotation.Import;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/18 17:14
 * @describe
 */
@Import(
        {CommonConfig.class,
         IRestControllerException.class}
)
public class WebStarter {
}
