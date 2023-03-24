package io.kenxue.devops.application.project.nginx;

import java.util.UUID;
import org.apache.commons.lang.StringUtils;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
public class NginxHelper {
    private static final String NGINX_CONF_FILE_FORMAT="%s_%s.conf";
    private static final String NGINX_SERVERNAME_FORMAT ="%s.%s.haolongz.online";
    private static final String NGINX_SERVER_PREFIX_FORMAT="%s-%s";
//    upstream springboottest{
//        server 192.168.143.134:8080 weight=1;
//    }
//
//    server{
//        listen 80;
//        server_name test.dev.haolongz.online;
//        resolver 8.8.8.8 114.114.114.114 valid=5 ipv6=off;
//        resolver_timeout 3s;
//        location / {
//            proxy_pass http://springboottest;
//        proxy_set_header Host $host;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header X-Forwarded-For $remote_addr;
//        proxy_set_header X-Forwarded-Proto $scheme;
//        proxy_set_header X-Forwarded-Host $host;
//        add_header Cache-Control no-store;
//        add_header Pragma no-cache;
//    }
//    }
    private static final String NGINX_CONF_SCRIPT_FORMAT = "upstream %s {\n" +
            "    %s \n" +
            "}\n" +
            "\n" +
            "server {\n" +
            "    listen 80;\n" +
            "    server_name %s;\n" +
            "    resolver 8.8.8.8 114.114.114.114 valid=5 ipv6=off;\n" +
            "    resolver_timeout 3s;\n" +
            "    location / {\n" +
            "        proxy_pass http://%s;\n" +
            "        proxy_set_header Host $host;\n" +
            "        proxy_set_header X-Real-IP $remote_addr;\n" +
            "        proxy_set_header X-Forwarded-For $remote_addr;\n" +
            "        proxy_set_header X-Forwarded-Proto $scheme;\n" +
            "        proxy_set_header X-Forwarded-Host $host;\n" +
            "        add_header Cache-Control no-store;\n" +
            "        add_header Pragma no-cache;\n" +
            "    }\n" +
            "}";


    public static String getNginxServerName(String prefix,String env){
        return String.format(NGINX_SERVERNAME_FORMAT,prefix,StringUtils.lowerCase(env));
    }

    public static String getNginxFileName(String prefix,String env){
        return String.format(NGINX_CONF_FILE_FORMAT,prefix, StringUtils.lowerCase(env));
    }

    public static String getNginxConfScript(String[] servers,String port,String serverName){
        return String.format(NGINX_CONF_SCRIPT_FORMAT,serverName,getNginxUpstreamServers(servers,port),serverName,serverName);
    }

    public static String getUniqueNginxPrefix(String projectName){
        UUID uuid = UUID.randomUUID();
        return String.format(NGINX_SERVER_PREFIX_FORMAT,projectName,uuid);
    }

    public static String getNginxUpstreamServers(String[] servers,String port){
        StringBuilder sb = new StringBuilder();
        for (String server : servers) {
            sb.append("server ").append(server).append(":").append(port).append(" weight=1;\n");
        }
        return sb.toString();
    }
}
