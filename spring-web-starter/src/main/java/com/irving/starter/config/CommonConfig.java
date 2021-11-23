package com.irving.starter.config;

import cn.hutool.core.net.NetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/18 16:25
 * @describe  项目启动打印 访问地址
 */

@Configuration(proxyBeanMethods=false)
@RequiredArgsConstructor
@Slf4j
public class CommonConfig {

    private final ServerProperties serverProperties;

    @Bean(value = "commandLineRunner")
    //@ConditionalOnProperty()
    public CommandLineRunner commandLineRunner(){
        return args -> {
            if (!log.isInfoEnabled()){
                return;
            }

            Integer port = serverProperties.getPort();
            String contextPath = serverProperties.getServlet().getContextPath();
            String localhostStr = NetUtil.getLocalhostStr();

            String finalPort=port!=null?port.toString():"8080";
            String finalContextPath=contextPath!=null && !StringUtils.isEmpty(contextPath) ?contextPath:"/";
            String finalLocalhostStr=localhostStr!=null && !StringUtils.isEmpty(localhostStr) ?localhostStr:"127.0.0.1";

            log.info("项目启动地址---> http://{}:{}{}",finalLocalhostStr,finalPort,finalContextPath);
        };
    }
}
