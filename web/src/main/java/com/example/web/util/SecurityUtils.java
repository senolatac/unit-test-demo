package com.example.web.util;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:01
 */
public class SecurityUtils
{
    private static final String ROLE_PREFIX = "ROLE_";

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String ROLE_ADMIN = ROLE_PREFIX + ADMIN;
    public static final String ROLE_USER = ROLE_PREFIX + USER;
}
