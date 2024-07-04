package com.combat.arena.config;

import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Slf4j
@Configuration
@EnableConfigurationProperties(FileSystemYamlProperties.class)
public class ThymeleafConfiguration {

    @Bean
    public SpringTemplateEngine defaultTemplateEngine() {
        log.info("...defaultTemplateEngine()");
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addTemplateResolver(defaultTemplateResolver());

        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ITemplateResolver defaultTemplateResolver() {
        log.info("...defaultTemplateResolver()");
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setPrefix("/pages/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(3);

        log.info("Thymeleaf cache mode: {}", true);
        templateResolver.setCacheable(true);
        templateResolver.setCacheTTLMs(30L);

        return templateResolver;
    }
}
