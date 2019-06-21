package com.pie.se.pl.Controller;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceResponse<T> {

	private String status;
	private T data;

}
