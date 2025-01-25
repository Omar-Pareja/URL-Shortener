import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

@RestController
@RequestMapping("/service")
class UrlController {

    private final ConcurrentHashMap<String, String> longToShortMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> shortToLongMap = new ConcurrentHashMap<>();
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int TOKEN_SIZE = 7;
    private final Random randGenerator = new Random();

    @PostMapping("/generate")
    public String createShortUrl(@RequestBody UrlInput input) {
        String originalUrl = input.getOriginalUrl();
        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input: originalUrl is required");
        }

        if (longToShortMap.containsKey(originalUrl)) {
            return buildShortUrl(longToShortMap.get(originalUrl));
        }

        String token;
        do {
            token = generateUniqueToken();
        } while (shortToLongMap.containsKey(token));

        longToShortMap.put(originalUrl, token);
        shortToLongMap.put(token, originalUrl);

        return buildShortUrl(token);
    }

    @GetMapping("/{token}")
    public String getOriginalUrl(@PathVariable String token) {
        String originalUrl = shortToLongMap.get(token);
        if (originalUrl == null) {
            throw new IllegalArgumentException("No matching URL found for token: " + token);
        }
        return "Redirecting to: " + originalUrl;
    }

    @GetMapping("/overview")
    public UrlStats getStatistics() {
        return new UrlStats(longToShortMap, shortToLongMap);
    }

    private String generateUniqueToken() {
        StringBuilder token = new StringBuilder(TOKEN_SIZE);
        for (int i = 0; i < TOKEN_SIZE; i++) {
            token.append(ALPHANUMERIC.charAt(randGenerator.nextInt(ALPHANUMERIC.length())));
        }
        return token.toString();
    }

    private String buildShortUrl(String token) {
        return "http://localhost:8080/service/" + token;
    }
}