/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.ktown4u.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrettyJsonWriter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ObjectMapper mapper;

    public PrettyJsonWriter() {
        this.mapper = createMapper();
    }

    private ObjectMapper createMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        final JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        mapper.registerModule(javaTimeModule);
        return mapper;
    }

    private String filterOut(final String statements, final String... fields) {
        final List<String> fieldList = Arrays.asList(fields);
        return Arrays.stream(statements.split("\n"))
                .filter(line -> fieldList.stream().noneMatch(line::contains))
                .collect(Collectors.joining("\n"));
    }

    public String prettyPrint(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String prettyPrintWithFilters(Object obj, String... filters) {
        String prettyStatements = prettyPrint(obj);
        return filterOut(prettyStatements, filters);
    }
}