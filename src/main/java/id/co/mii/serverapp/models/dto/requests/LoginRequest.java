package id.co.mii.serverapp.models.dto.requests;

import lombok.Data;

@Data
public class LoginRequest {

  private String username;
  private String password;
}
