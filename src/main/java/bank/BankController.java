package bank;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BankController {
    private final BankService service = new BankService();

    @GetMapping("/accounts")
    public List<BankService.UserResponse> getAll() {
        return service.getAllUsers();
    }
}