package dev.nars.wannab.user.dto;

import dev.nars.wannab.check.Status;
import dev.nars.wannab.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteUserResDto {
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    public static DeleteUserResDto from(User user) {
        DeleteUserResDto dto = new DeleteUserResDto();
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setStatus(user.getStatusCk());

        return dto;
    }
}
