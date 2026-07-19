package com.translateapi;

import com.translateapi.port.in.TranslateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translate")
public class TranslationController {

  private final TranslateUseCase translateUseCase;

  public TranslationController(TranslateUseCase useCase) {
    this.translateUseCase = useCase;
  }

  @GetMapping("/test/{textToTranslate}/{fromLang}/{toLang}")
  public ResponseEntity<TranslationOut> test(@PathVariable String textToTranslate,
                                     @PathVariable String fromLang,
                                     @PathVariable String toLang) {

    final var translationOut = this.translateUseCase
            .translate(
                    new TranslationIn(textToTranslate, fromLang, toLang));
    return new ResponseEntity<TranslationOut>(translationOut, HttpStatus.OK);
  }

}
