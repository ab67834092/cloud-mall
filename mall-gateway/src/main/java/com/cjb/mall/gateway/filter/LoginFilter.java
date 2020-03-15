package com.cjb.mall.gateway.filter;

import com.cjb.mall.common.user.UserInfo;
import com.cjb.mall.common.utils.CookieUtils;
import com.cjb.mall.common.utils.JwtUtils;
import com.cjb.mall.gateway.properties.FilterProperties;
import com.cjb.mall.gateway.properties.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chenjiabao
 * @date 2018/10/2
 */
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        //判断白名单
        return !isAllowPath(requestURI);
    }

    @Override
    public Object run() {
        //获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //获取请求
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("token");
        //登录验证逻辑 JWT
        if(StringUtils.isEmpty(token)){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        try {
            //从Token获取解析用户信息
            UserInfo userInfo = JwtUtils.getUserInfo(jwtProperties.getPublicKey(), token);
            //解析成功，什么都不做，放行
            System.out.println(userInfo.getId());
            System.out.println(userInfo.getTokenExpire());
            //todo 如果做权限管理的话，在这做权限检验
        }catch (ExpiredJwtException e){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(403);
            logger.error("token失效了");
        } catch (Exception e) {
            //检验出现异常，返回403
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(403);
            logger.error("非法访问，未登录，地址：{}", request.getRemoteHost());
            logger.error("非法访问，未登录，地址：{}", request.getRemoteHost(), e);
        }
        return null;
    }

    /**
     * 判断请求URI是不是白名单中的URI
     *
     * @param requestURI
     * @return
     */
    private Boolean isAllowPath(String requestURI) {
        boolean flag = false;
        List<String> allowPaths =filterProperties.getAllowPaths();
        if (allowPaths.contains(requestURI)) {
            //允许
            flag = true;
        }
        return flag;


    }
}
