package com.qa.sdet.test;

import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import com.qa.sdet.Country;
import com.qa.sdet.CountryDetailsMain;

public class CountryTest {	

	@Test
	public void testGetContrytDetailsByCode_InvalidSearch()
			throws InterruptedException, ExecutionException, IOException, Exception {
		Assert.assertNull(CountryDetailsMain.getDetailsByCode("90989").getCapital());
	}

	@Test
	public void testGetContrytDetailsByCode() throws InterruptedException, ExecutionException, IOException, Exception {
		Assert.assertNotNull(CountryDetailsMain.getDetailsByCode("Denmark"));
	}

	@Test
	public void testGetContrytDetailsByCodeValidData()
			throws InterruptedException, ExecutionException, IOException, Exception {
		Assert.assertEquals("Bogotá", CountryDetailsMain.getDetailsByCode("COL").getCapital());
	}
	
	@Test
	public void testGetContrytDetailsByNameValidInput()
			throws InterruptedException, ExecutionException, IOException, Exception {
		String searchKey = "Spain";
		List<Country> lstCountry = CountryDetailsMain.getDetailsByName(searchKey);
		Assert.assertNotNull("Madrid", lstCountry.get(0).getCapital());
	}

	@Test
	public void testGetContrytDetailsByNameAsNull() throws InterruptedException, ExecutionException, IOException {
		assertThrows(Exception.class, () -> {
			CountryDetailsMain.getDetailsByName(null);
		});
	}
	
	
	@Test
	public void testGetCountrytDetailsByName_InvalidSearch() throws InterruptedException, ExecutionException, IOException {
		assertThrows(Exception.class, () -> {
			CountryDetailsMain.getDetailsByName("tetstetste");
		});
	}

	@Test
	public void testGetContrytDetailsByName() throws InterruptedException, ExecutionException, IOException, Exception {
		Assert.assertNotNull(CountryDetailsMain.getDetailsByName("Turkey"));
	}
}
