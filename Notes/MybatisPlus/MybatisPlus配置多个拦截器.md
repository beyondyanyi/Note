```
package com.wst.system.config.mybatisPlus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.wst.system.util.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO mybatisplus配置类
 * @Author yanyi
 * @Date 2019/7/29 13:57
 * @Version 1.0
 **/
@EnableTransactionManagement
@Configuration
@MapperScan({"com.wst.business.dao","com.wst.system.config.shiro.dao","com.wst.system.config.generator.dao"})
public class MybatisPlusConfig {

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }




    //防止分页插件和mybatis其他拦截器冲突
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration){
                //这里添加其他拦截器
                    //table 筛选条件拦截器
                configuration.addInterceptor(new PageInterceptor());
            }
        };
    }
}
```

