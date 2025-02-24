package com.viai.ai_docs_reader.mapper;

import com.viai.ai_docs_reader.dto.response.UserResponse;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.util.TimeZoneUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static UserResponse toUserResponse(UserModel user, String userTimeZone) {

        ZonedDateTime createdTime = TimeZoneUtils.convertUtcToZone(user.getCreatedAt(), userTimeZone);
        ZonedDateTime updatedTime = TimeZoneUtils.convertUtcToZone(user.getUpdatedAt(), userTimeZone);

        String formattedCreatedAt = createdTime.format(FORMATTER);
        String formattedUpdatedAt = updatedTime.format(FORMATTER);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(formattedCreatedAt)
                .updatedAt(formattedUpdatedAt)
                .build();
    }

    public static UserResponse toUserResponse(UserModel user) {
        return toUserResponse(user, ZoneId.systemDefault().getId());
    }
}
