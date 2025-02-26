package com.viai.ai_docs_reader.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_roles")
public class UserRoleModel {
    @EmbeddedId
    private UserRoleId id;
}
