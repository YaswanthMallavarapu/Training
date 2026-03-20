package org.hibernate.controller;

import org.hibernate.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;


public class FlightController {
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                =context.getBean(LocalContainerEntityManagerFactoryBean.class);
        System.out.println(entityManagerFactoryBean);
        context.close();
    }
}
