package com.springboot.ecommerceApplication.domain;

import org.apache.tomcat.util.buf.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter(autoApply = true)
public class StringSetConverter implements AttributeConverter<List<String>, String> {
           //private static final String SPLIT_CHAR = ";";

        @Override
        public String convertToDatabaseColumn(List<String> stringList) {
            if (stringList == null || stringList.isEmpty()) {
                return "";
            }
            return StringUtils.join(stringList, ',');
            //return String.join(",", stringList);
           // return Joiner.on(',').join(list);

        }

        @Override
        public List<String> convertToEntityAttribute(String string) {
            if (string == null || string.trim().length() == 0) {
                return new ArrayList<String>();
            }

            String[] data = string.split(",");
            return Arrays.asList(data);

            //return new ArrayList<>(Arrays.asList(string.split(",")));
        }
    }

