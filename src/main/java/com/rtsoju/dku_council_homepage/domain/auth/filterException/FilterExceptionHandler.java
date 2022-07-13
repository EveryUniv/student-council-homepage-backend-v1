package com.rtsoju.dku_council_homepage.domain.auth.filterException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtsoju.dku_council_homepage.common.RequestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterExceptionHandler extends OncePerRequestFilter {

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenNotExsistException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        }
    }



    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Exception e){
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        RequestResult errResponse = new RequestResult(e);

        try {
            mapper.writeValue(response.getWriter(), errResponse);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
