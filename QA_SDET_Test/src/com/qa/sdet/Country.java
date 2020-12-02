package com.qa.sdet;

public class Country {
	private String capital;

	public String getCapital() {
		return capital;
	}

	@Override
	public String toString() {
		return "capital=" + capital + "";
	}

	public Country() {
	}

	public Country(String capital) {
		super();
		this.capital = capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

}
