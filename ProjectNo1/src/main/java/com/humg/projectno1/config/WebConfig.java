package com.humg.projectno1.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.humg.projectno1.common.AccountMail;

@Configurable
public class WebConfig {


  @Bean
  public DriverManagerDataSource dataSource() throws IOException {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    Properties properties = new Properties();
    InputStream user_props = this.getClass().getResourceAsStream("/application.properties");
    properties.load(user_props);
    dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(properties.getProperty("spring.datasource.url"));
    dataSource.setUsername(properties.getProperty("spring.datasource.username"));
    dataSource.setPassword(properties.getProperty("spring.datasource.password"));
    return dataSource;
  }

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(582);

    mailSender.setUsername(AccountMail.MY_EMAIL);
    mailSender.setPassword(AccountMail.MY_PASSWORD);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    return mailSender;
  }

}
