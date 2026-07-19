package com.translateapi.port.out;

import com.translateapi.TranslationId;
import com.translateapi.TranslationIn;
import com.translateapi.TranslationOut;

import java.util.List;

public interface TranslateRepository {
  TranslationOut translateText(TranslationIn translationIn);
  TranslationOut findById(TranslationId translationId);
  List<TranslationOut> findAll();
}
