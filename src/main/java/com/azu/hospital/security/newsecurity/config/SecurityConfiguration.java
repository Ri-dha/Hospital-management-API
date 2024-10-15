package com.azu.hospital.security.newsecurity.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;




    public SecurityConfiguration(
            @Qualifier("Jwt") JwtAuthenticationFilter jwtAuthFilter,
            @Qualifier("Provider") AuthenticationProvider authenticationProvider, LogoutHandler logoutHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .cors(AbstractHttpConfigurer::disable)
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests(
                        (auth) -> {
                            auth.requestMatchers(
                                            "/auth/**",
                                            "/v2/api-docs",
                                            "/v3/api-docs",
                                            "/v3/api-docs/**",
                                            "/swagger-resources",
                                            "/swagger-resources/**",
                                            "/configuration/ui",
                                            "/configuration/security",
                                            "/webjars/**",
                                            "/notification/**"
                                    )
                                    .permitAll();
                            auth.anyRequest().authenticated();
                        })
                .sessionManagement(s -> {
                    s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }




//    @Bean("CorsFilters")
//    public CorsConfigurationSource corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(List.of(
//                "http://192.168.0.190:3000",
//                "http://192.168.0.193:3000",
//                "http://192.168.0.168:3000",
//                "http://192.168.0.130:3000",
//                "http://192.168.0.149:3000",
//                "http://192.168.0.100:3000",
//                "http://192.168.0.195:3000",
//                "http://192.168.0.114:3000",
//                "http://192.168.0.129:3000",
//                "ws://localhost:8086/ws",
//                "http://localhost:3000",
//                "https://demo-web.azu-app.com",
//                "https://hospital.azu-app.com",
//                "https://hospital.azu-app.com/",
//                "/manage/**",
//                "**"
//        ));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowedHeaders(Arrays.asList(
//                ORIGIN,
//                CONTENT_TYPE,
//                ACCEPT,
//                AUTHORIZATION,
//                ACCEPT_LANGUAGE,
//                CONTENT_LANGUAGE,
//                RANGE,
//                HOST
//        ));
//        config.setAllowedMethods(Arrays.asList(
//                GET.name(),
//                POST.name(),
//                DELETE.name(),
//                PUT.name(),
//                PATCH.name(),
//                OPTIONS.name(),
//                HEAD.name()
//        ));
//        source.registerCorsConfiguration("/**", config);
//        return source;
//
//    }


//    request -> {
//
//            request.configurationSource(corsFilter());
//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-CSRF-TOKEN");
//        return repository;
//    }

//    .csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository()))
//            .addFilterAfter(new CsrfFilter(csrfTokenRepository()), UsernamePasswordAuthenticationFilter.class)



}
