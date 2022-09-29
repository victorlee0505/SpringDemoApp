package com.example.demo.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.Common.CommonStringUtils;

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

    public static String getFulfillmentName(String fulfillmentName) {
        if(fulfillmentName == null){
            return "";
        }
        List<String> names = new ArrayList<String>();
        
		if (fulfillmentName.length() > 0) {
			fulfillmentName = fulfillmentName.trim();
			
			if(fulfillmentName.contains("/")){
                names = Arrays.asList(fulfillmentName.split("/"));
			} else {
                names.add(fulfillmentName);
            }

            System.out.println(names);

            int i = 0;
            for (String name : names) {
                name = name.trim();
                int idx = name.indexOf(",");
                if (idx > 0 &&  idx+1 < name.length()) {
                    name = name.substring(idx+1).trim()+" "+name.substring(0,idx).trim();
                }
                names.set(i, name);
                i++;
            }
		}

        System.out.println(names);

        return String.join(" / ", names);

	}

        private static String dateToIntString(Date date){

        Calendar cal = Calendar.getInstance((TimeZone.getTimeZone("America/New_York")));
        cal.setTime(date);

        StringBuilder sb = new StringBuilder();
        sb.append(cal.get(Calendar.YEAR));
        if(cal.get(Calendar.MONTH)+1 < 10){
            sb.append(0);
        }
        sb.append(cal.get(Calendar.MONTH));
        if(cal.get(Calendar.DAY_OF_MONTH) < 10){
            sb.append(0);
        }
        sb.append(cal.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    public static void main(String[] args) {

        // List<String> inputs = new ArrayList<String>();
        // inputs.add(null);
        // inputs.add("");
        // inputs.add("KEANIE,KATARINA");
        // inputs.add("KEANIE   ,   KATARINA    ");
        // inputs.add("KEANIE,KATARINA/CVETKOVIC,DIANA");
        // inputs.add("KEANIE,KATARINA /CVETKOVIC,DIANA/LOGINOV,VADYM ");
        // inputs.add("KEANIE  ,   KATARINA  /     CVETKOVIC    ,     DIANA       /   LOGINOV   ,   VADYM     ");

        // for(int i = 0; i < inputs.size(); i ++){

        //     System.out.println("Input:  " + inputs.get(i));

        //     String fulfillmentName = StringService.getFulfillmentName(inputs.get(i));

        //     System.out.println("output:  " + fulfillmentName);
            
        //     String label = "%NAME%<br/>%ADDRESS1%<br/>%ADDRESS2%<br/>%ADDRESS3%";
            
        //     label = label.replaceAll("%NAME%", fulfillmentName.trim());
            
        //     System.out.println("label:  " + label);
        //     System.out.println("");
        // }

        Date date = new Date();

        System.out.println("Input:  " + date.toString());
            
        System.out.println(StringService.dateToIntString(date));

    }

}
