package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExgVal {
	private Long id;
	private Currencies from;
	private Currencies to;
	private Integer exgVal;
}
