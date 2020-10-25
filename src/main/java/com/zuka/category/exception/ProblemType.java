package com.zuka.category.exception;

import lombok.Getter;

@Getter
public enum ProblemType {


	INVALID_BODY("/invalid-body", "Invalid Body", "invalid_message_body"),
	INVALID_BODY_PARAM("/invalid-body", "Invaled Body", "invalid_message_body_param"),
	INVALID_VALUE_LONG_DATABASE("/invaled-long-database", "Value Long", "invalid_value_long_database"),
    USER_NOT_FOUND("/user-not-found", "User not found", "category_not_exists"),
    USER_NOT_EXISTS("/user-not-exists", "User not exists", "user_not_found"),
	DATE_INVALED("/date-invalid", "Date Invalid", "date_invalid"),
	NAME_ALREADY_EXISTS("/name-duplicate", "Name duplicate", "name_duplicate"),;

    private String uri;
    private String title;
    private String messageSource;

    ProblemType(String path, String title, String messageSource){
        this.uri = "https://zuka.com.br" + path;
        this.title = title;
        this.messageSource = messageSource;
    }
}
