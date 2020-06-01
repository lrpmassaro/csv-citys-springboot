package br.com.csv.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	private String state;
	private Long number;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

}
