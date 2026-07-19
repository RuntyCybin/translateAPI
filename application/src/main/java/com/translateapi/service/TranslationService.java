package com.translateapi.service;

import com.translateapi.TranslationIn;
import com.translateapi.TranslationOut;
import com.translateapi.port.in.TranslateUseCase;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class TranslationService implements TranslateUseCase {

  private static final String TRANSLATE_URL =
      "https://script.google.com/macros/s/AKfycbx9ArTsKIoJdtRy0ezYmMjlpISVFI9RcP7-8ls9AxcSYXcN9pDLScdFZbPgsZU6YN5jZw/exec";

  private final HttpClient httpClient;

  public TranslationService() {
    this.httpClient = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();
  }

  @Override
  public TranslationOut translate(TranslationIn intext) {
    final String url = TRANSLATE_URL
        + "?q=" + this.encode(intext.text())
        + "&source=" + this.encode(intext.fromLg())
        + "&target=" + this.encode(intext.toLg());

    final HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    final HttpResponse<String> response = this.httpClient
        .sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .join();

    return new TranslationOut(response.body());
  }

  private String encode(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8);
  }
}
