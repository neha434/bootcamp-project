package com.springboot.ecommerceApplication.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Converter
public class StringSetConverter implements AttributeConverter<List<String>, String> {
           private static final String SPLIT_CHAR = ",";

//    @Override
//    public String convertToDatabaseColumn(Set<String> attribute) {
//           return String.join(SPLIT_CHAR, attribute);
//    }
//
//    @Override
//    public Set<String> convertToEntityAttribute(String dbData) {
//        return null;
//    }

//    @Converter
//    public class StringListConverter implements AttributeConverter<List<String>, String> {
//        private static final String SPLIT_CHAR = ";";

        @Override
        public String convertToDatabaseColumn(List<String> stringList) {
            return String.join(SPLIT_CHAR, stringList);
        }

        @Override
        public List<String> convertToEntityAttribute(String string) {
            return Arrays.asList(string.split(SPLIT_CHAR));
        }
    }

