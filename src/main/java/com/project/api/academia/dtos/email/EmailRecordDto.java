package com.project.api.academia.dtos.email;

import java.util.UUID;

public record EmailRecordDto(

        UUID clienteId,
        String emailTo,
        String subject,
        String text
) {
}
