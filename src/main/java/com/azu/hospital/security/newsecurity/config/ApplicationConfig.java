package com.azu.hospital.security.newsecurity.config;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.security.audting.ApplicationAuditingAware;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig {

  private final BaseUserDao baseUserDao;


  public ApplicationConfig(
         @Qualifier("BaseUserJpa") BaseUserDao baseUserDao) {
    this.baseUserDao = baseUserDao;
  }



  @Bean("UserDetailsServiceApp")
  public UserDetailsService userDetailsService() {
    return username -> baseUserDao.findByEmail(username)
           .orElseThrow(
                   () -> new UsernameNotFoundException("User not found!")
           );

  }




  @Bean("Provider")
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean("AuthManger")
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
//
//  @Bean("CorsFilters")
//  public CorsFilter corsFilter() {
//    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    final CorsConfiguration config = new CorsConfiguration();
//    config.setAllowCredentials(true);
//    config.setAllowedOrigins(List.of(
//            "http://192.168.0.190:3000",
//            "http://192.168.0.193:3000",
//            "http://192.168.0.130:3000",
//            "http://192.168.0.149:3000",
//            "http://192.168.0.100:3000",
//            "http://192.168.0.195:3000",
//            "http://192.168.0.114:3000",
//            "http://192.168.0.129:3000",
//            "http://localhost:3000",
//            "https://hospital.azu-app.com"
//    ));
//    config.setAllowedHeaders(Arrays.asList(
//            ORIGIN,
//            CONTENT_TYPE,
//            ACCEPT,
//            AUTHORIZATION
//    ));
//    config.setAllowedMethods(Arrays.asList(
//            GET.name(),
//            POST.name(),
//            DELETE.name(),
//            PUT.name(),
//            PATCH.name()
//    ));
//    source.registerCorsConfiguration("/**", config);
//    return new CorsFilter(source);
//
//  }

  @Bean("PassEncoder")
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  @Bean("TokenKeyConvertor")
//  public ConversionService tokenKeyConversionService() {
//    ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
//    bean.setConverters(Set.of(new StringToPrivateKeyConverter(), new StringToPublicKeyConverter()));
//    return bean.getObject();
//  }



  @Bean("ResourceImagesAndFile")
  public WebMvcConfigurer webMvcConfigurer(){
    return new WebMvcConfigurer() {
      @Override
      public void addResourceHandlers(@NotNull @org.jetbrains.annotations.NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:assets/user/files/").addResourceLocations("file" +
                ":assets/images/").addResourceLocations("file:assets/user/images/").addResourceLocations("file:assets/patient/");
      }
    };
  }



  @Bean("auditorAware")
  public AuditorAware<Long> auditorAware (){
    return new ApplicationAuditingAware();
  }



}
