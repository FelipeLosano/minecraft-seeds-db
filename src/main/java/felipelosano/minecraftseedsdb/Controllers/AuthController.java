package felipelosano.minecraftseedsdb.Controllers;

import felipelosano.minecraftseedsdb.DTO.AuthDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

  @PostMapping(path = "/login")
  public ResponseEntity login(@RequestBody @Valid AuthDTO data) {

  }


}