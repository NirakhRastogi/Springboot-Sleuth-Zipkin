package com.distributed.tracing.CheckoutService.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class YamlPropertyFactoryLoader implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(encodedResource.getResource());

        Properties properties = factoryBean.getObject();
        String filename = encodedResource.getResource().getFilename();

        Objects.requireNonNull(filename, "Filename should not be null");
        Objects.requireNonNull(properties, "Properties should not be null");
        return new PropertiesPropertySource(filename, properties);
    }
}