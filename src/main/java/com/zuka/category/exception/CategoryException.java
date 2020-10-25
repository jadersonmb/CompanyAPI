package com.zuka.category.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String type;
	private String title;
	private String details;

	private String userMessage;
	private String uri;

	public CategoryException() {
		super();
	}
	
	public CategoryException(String message) {
		super(message);
	}

	public CategoryException(Integer status, String type, String title, String details) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.details = details;
	}
}
