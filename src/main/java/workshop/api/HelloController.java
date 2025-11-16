@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello from Docker!";
    }
}
