package ru.otus.erinary.hw02.quiz.service.localization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Сервис для локализации сообщений
 */
@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final Locale currentLocale;

    public LocalizationServiceImpl(MessageSource messageSource, @Value("${application.locale}") String localeKey) {
        this.messageSource = messageSource;
        this.currentLocale = selectLocale(localeKey);
    }

    public String localizeMessage(String code) {
        return messageSource.getMessage(code, null, currentLocale);
    }

    public String getLocaleCode() {
        return currentLocale.getLanguage();
    }

    private Locale selectLocale(String localeKey) {
        return new Locale.Builder().setLanguageTag(localeKey).build();
    }
}
