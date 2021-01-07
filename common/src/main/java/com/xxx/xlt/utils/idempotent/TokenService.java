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
        String str = UUID.randomUUID().toString().replace("-", "");
        StringBuilder token = new StringBuilder();
        try {
            token.append(RedisConstant.PREFIX).append(str);
            RedisUtil.set(token.toString(), token.toString(), 1000L);
            if (!StringUtils.isEmpty(token.toString())) {
                return token.toString();
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

        String token = request.getHeader(RedisConstant.TOKEN);
        if (StringUtils.isEmpty(token)) {// header中不存在token
            token = request.getParameter(RedisConstant.TOKEN);
            if (StringUtils.isEmpty(token)) {// parameter中也不存在token
                throw new CommonException(ApiResult.BAD_ARGUMENT);
            }
        }

        String key = RedisConstant.PREFIX + RedisConstant.TOKEN;
        if (!RedisUtil.hasKey(key)) {
            throw new CommonException(ApiResult.REPETITIVE_REQUEST);
        }

        RedisUtil.del(token);
        return true;
    }
}

