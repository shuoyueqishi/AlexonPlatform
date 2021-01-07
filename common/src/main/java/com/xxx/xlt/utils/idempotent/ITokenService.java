package com.xxx.xlt.utils.idempotent;

import javax.servlet.http.HttpServletRequest;

public interface ITokenService {
    /**
     * 创建token
     * @return token
     */
    public  String createToken();

    /**
     * 检验token
     * @param request
     * @return true/false
     */
    public boolean checkToken(HttpServletRequest request) throws Exception;
}
