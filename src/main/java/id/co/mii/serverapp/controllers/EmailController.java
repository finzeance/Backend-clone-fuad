package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import id.co.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

  private EmailService emailService;

  @PostMapping("/simple")
  public EmailRequest sendSimpleMessage(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendSimpleMessage(emailRequest);
  }

  @PostMapping("/attach")
  public EmailRequest sendMessageWithAttachment(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendMessageWithAttachment(emailRequest);
  }

  // Cara 1 - Multiple by Email
  @PostMapping("/attach-multiple")
  public EmailRequest sendMessageWithAttachmentMultiple(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendMessageWithAttachmentMultiple(emailRequest);
  }

  // Cara 2 - Multiple by Object
  @PostMapping("/attach-multiple/object")
  public Iterable<EmailRequest> sendMessageWithAttachmentMultipleObject(
    @RequestBody Iterable<EmailRequest> emailRequests
  ) {
    return emailService.sendMessageWithAttachmentMultipleObject(emailRequests);
  }
}
