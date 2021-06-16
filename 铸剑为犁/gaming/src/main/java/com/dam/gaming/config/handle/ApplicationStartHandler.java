package com.dam.gaming.config.handle;

import com.dam.gaming.view.DemoView;
import com.dam.gaming.view.HelloWorld;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author : dam
 * @description :spring任务启动
 * @create :2021-06-16 14:36:00
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class ApplicationStartHandler implements ApplicationListener<ContextRefreshedEvent> {
    private final DemoView demoView;
    private final HelloWorld helloWorld;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
          helloWorld.run();
    }
}
