package com.qa.sdet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.type.TypeReference;

public class CountryDetailsMain {

	private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

	private static final String URL = "http://restcountries.eu/rest/v2/";

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		userInputs();
	}

	public static void userInputs() throws IOException, InterruptedException, ExecutionException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\n Search Country By \n A - By Name \n B - By Code  \n X - exit \n ");
		String input = reader.readLine();
		if (input.toUpperCase().equals("X"))
			System.exit(1);
		System.out.print(" \n Enter your country name or Code \n");
		String searchKey = reader.readLine();
		switch (input.toUpperCase()) {
		case "A":
			getDetailsByName(searchKey);
			break;
		case "B":
			getDetailsByCode(searchKey);
			break;
		case "X":
			System.exit(1);
			break;
		default:
			System.out.print("Entered inputs are not valid, Please try with correct inputs \n");
			break;
		}
		userInputs();
	}

	public static List<Country> getDetailsByName(String searchString)
			throws InterruptedException, ExecutionException, IOException {
		HttpRequest req = HttpRequest.newBuilder(URI.create(URL + "name/" + searchString)).GET().build();
		CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
		List<Country> covertFromJsonToObject = null;
		if (response.get().statusCode() == 500)
			System.out.println("Country Details Not Avaialble");
		else {
			covertFromJsonToObject = JSONUtils.convertFromJsonToList(response.get().body(),
					new TypeReference<List<Country>>() {
					});
			System.out.println("\n" + covertFromJsonToObject);
		}
		return covertFromJsonToObject;
	}

	public static Country getDetailsByCode(String searchCode)
			throws InterruptedException, ExecutionException, IOException {
		HttpRequest req = HttpRequest.newBuilder(URI.create(URL + "alpha/" + searchCode)).GET().build();
		CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
		Country covertFromJsonToObject = null;
		if (response.get().statusCode() == 500)
			System.out.println("Country Details Not Avaialble");
		else {
			covertFromJsonToObject = JSONUtils.covertFromJsonToObject(response.get().body(), Country.class);
			System.out.println("\n" + covertFromJsonToObject);
		}
		return covertFromJsonToObject;
	}

}
