package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/members/add", "/login","/logout","/css/*"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse)response;
        try{
            log.info("인증체크 필터시작{}", requestURI);
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행{}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {//세션없을시(로그인안됨)or attribute가 null일시
                    log.info("미인증 사용자 요청{}", requestURI);
                    //로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);//로그인다시하면 쫓겨난곳으로부터시작.
                    return;
                }
            }
            chain.doFilter(request, response);
        }catch(Exception e){
            throw e;
        } finally{
            log.info("인증체크 필터종료.");
        }

    }
    //화이트 리스트경우 인증체크x
    private boolean isLoginCheckPath(String requestUri){
        return !PatternMatchUtils.simpleMatch(whiteList, requestUri);//화이트리스트 안된건 false
    }
}

