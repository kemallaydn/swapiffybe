package com.swapiffy.swapiffybe.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestService {
    String getClientIp(HttpServletRequest request);
}
