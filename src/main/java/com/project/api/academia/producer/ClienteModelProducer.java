package com.project.api.academia.producer;


import com.project.api.academia.dtos.email.EmailRecordDto;
import com.project.api.academia.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.project.api.academia.config.RabbitMQConfig.RK_EMAIL_LOG;

@Component
@RequiredArgsConstructor
public class ClienteModelProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishMessagenEmail(Cliente cliente) {
        var emailDTO = new EmailRecordDto(
                cliente.getId(),
                cliente.getEmail(),
                "Cadastro Realizado Com Sucesso, " + cliente.getNomeCompleto() + "!",
                "Agradecemos o seu cadastro, " + cliente.getNomeCompleto() + "!" );
        rabbitTemplate.convertAndSend(RK_EMAIL_LOG, RK_EMAIL_LOG, emailDTO);
    }

}
