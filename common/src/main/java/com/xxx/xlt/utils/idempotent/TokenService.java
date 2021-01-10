package com.xxx.xlt.utils.idempotent;

import com.xxx.xlt.utils.constant.ApiResult;
import com.xxx.xlt.utils.constant.RedisConstant;
import com.xxx.xlt.utils.exception.CommonException;
import com.xxx.xlt.utils.redis.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Token service
 */
@Service
public class TokenService implements ITokenService {
    /**
     * 创建token
     *
     * @return token
     */
    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString().replace("-", "");
        try {
            String key = RedisConstant.PREFIX + RedisConstant.API_TOKEN;
            RedisUtil.set(key, token, 30L);
            if (!StringUtils.isEmpty(token)) {
                return token;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 检验token
     *
     * @param request http request
     * @return true/false
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(RedisConstant.API_TOKEN);
        if (StringUtils.isEmpty(token)) {// header中不存在token
            token = request.getParameter(RedisConstant.API_TOKEN);
            if (StringUtils.isEmpty(token)) {// parameter中也不存在token
                throw new CommonException(ApiResult.BAD_ARGUMENT);
            }
        }

        String key = RedisConstant.PREFIX + RedisConstant.API_TOKEN;
        if (!RedisUtil.hasKey(key)) {
            throw new CommonException(ApiResult.REPETITIVE_REQUEST);
        }
       if(RedisUtil.get(key).equals(token)) {
           RedisUtil.del(token);
       } else {
           throw new CommonException(ApiResult.API_TOKEN_ERROR);
       }
        return true;
    }
}

