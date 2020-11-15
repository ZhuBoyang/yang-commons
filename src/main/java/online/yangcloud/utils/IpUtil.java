package online.yangcloud.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author zhuby
 * @since 2020/11/13 11:12 上午
 */

public class IpUtil {

    private static final String ip = "127.0.0.1";
    private static final String unknown = "unknown";

    /**
     * 根据请求获取客户端 ip 地址
     *
     * @param req 客户端请求
     * @return ip 地址
     * @author zhuby
     * @since 2020/11/13 11:17 上午
     */
    public static String getIpAddr(HttpServletRequest req) {
        String ipAddress = req.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ipAddress) || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("x-client-ip");
        }
        if (StrUtil.isBlank(ipAddress) || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("client-ip");
        }
        if (StrUtil.isBlank(ipAddress) || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("x-real-ip");
        }
        if (StrUtil.isBlank(ipAddress) || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ipAddress) || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ipAddress) || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getRemoteAddr();
            if (ip.equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    System.out.println("getIpAddr : " + e.getMessage());
                }
                if (Objects.nonNull(inet)) {
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (StrUtil.isNotBlank(ipAddress)) { // "***.***.***.***".length() = 15
            if (ipAddress.indexOf(StrUtil.COMMA) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(StrUtil.COMMA));
            }
        }
        return ipAddress;
    }

}
