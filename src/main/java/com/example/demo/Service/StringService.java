package com.example.demo.Service;

import com.example.demo.Common.CommonStringUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StringService {
    private static final Logger LOG = LoggerFactory.getLogger(StringService.class);

    /**
     * make use of CommonStringUtils (static) and apache StringUtils(maven package)
     */
    public String replaceString(String input, String target, String replace) {
        String result = input;
        if ((CommonStringUtils.containStrNoCase(input, target)) == true) {
            result = StringUtils.replace(input, target, replace);
        }
        return result;
    }

}
