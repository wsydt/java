package com.example.wsy.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String[] dependsOn = beanDefinition.getDependsOn();
            if (dependsOn != null) {
                System.out.println(Arrays.asList(dependsOn));
            } else {
                System.out.println("bean 无依赖, name : " + beanDefinitionName);
            }
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            System.out.println(propertyValues);
            String[] attributeNames = beanDefinition.attributeNames();
            System.out.println(Arrays.asList(attributeNames));
        }
        System.out.println(beanFactory.getBeanDefinitionCount());
        System.out.println("MyBeanFactoryPostProcessor::postProcessBeanFactory method invoke... " + beanFactory);
    }
}
