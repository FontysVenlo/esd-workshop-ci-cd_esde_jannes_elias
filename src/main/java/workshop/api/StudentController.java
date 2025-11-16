@RestController
public class StudentController {

    @GetMapping("/")
    public String home() {
        return "Hello from Docker!";
    }
}
