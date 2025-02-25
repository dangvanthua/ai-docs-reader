package com.viai.ai_docs_reader.mapper;

import com.viai.ai_docs_reader.dto.response.UserResponse;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.util.TimeZoneUtils;

import java.time.ZonedDateTime;

public class UserMapper {

    public static UserResponse toUserResponse(UserModel user, String userTimeZone) {
        ZonedDateTime createdTime = TimeZoneUtils.convertUtcToZone(user.getCreatedAt(), userTimeZone);
        ZonedDateTime updatedTime = TimeZoneUtils.convertUtcToZone(user.getUpdatedAt(), userTimeZone);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(TimeZoneUtils.formatDateTimeIso(createdTime))
                .updatedAt(TimeZoneUtils.formatDateTimeIso(updatedTime))
                .build();
    }

    public static UserResponse toUserResponse(UserModel user) {
        return toUserResponse(user, "UTC");
    }
}