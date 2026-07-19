package com.translateapi.port.in;

import com.translateapi.TranslationIn;
import com.translateapi.TranslationOut;

public interface TranslateUseCase {

  TranslationOut translate(TranslationIn intext);
}
