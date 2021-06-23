package com.mhkim.tms.util;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Preconditions;
import com.mhkim.tms.exception.error.enums.ErrorMessageType;
import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtils {

    private static MessageSourceAccessor messageSourceAccessor;

    private static final String CHECK_MESSAGE_SOURCE_ACCESSOR = "MessageSourceAccessor is not initialized.";

    private MessageUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getMessage(String key) {
        checkState(null != messageSourceAccessor, CHECK_MESSAGE_SOURCE_ACCESSOR);
        return messageSourceAccessor.getMessage(key);
    }

    public static String getMessage(String key, Object... params) {
        checkState(null != messageSourceAccessor, CHECK_MESSAGE_SOURCE_ACCESSOR);
        return messageSourceAccessor.getMessage(key, params);
    }

    public static String getMessage(ErrorMessageType key, Object... params) {
        checkState(null != messageSourceAccessor, CHECK_MESSAGE_SOURCE_ACCESSOR);
        return messageSourceAccessor.getMessage(key.value(), params);
    }

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessageUtils.messageSourceAccessor = messageSourceAccessor;
    }

}
