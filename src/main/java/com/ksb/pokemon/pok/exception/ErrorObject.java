package com.ksb.pokemon.pok.exception;

//import java.sql.Date;

import lombok.Data;

@Data
public class ErrorObject {

	private Integer statusCode;
	private String message;
//	private Date timestamp;
}
