package cn.iocoder.yudao.module.sns.framework.web.config;

import cn.iocoder.yudao.framework.swagger.config.YudaoSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sns 模块的 web 组件的 Configuration
 */
@Configuration(proxyBeanMethods = false)
public class SnsWebConfiguration {


    /**
     * sns 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi snsGroupedOpenApi() {
        return YudaoSwaggerAutoConfiguration.buildGroupedOpenApi("sns");
    }

}
