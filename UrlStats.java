import java.util.concurrent.ConcurrentHashMap;

class UrlStats {
    private final ConcurrentHashMap<String, String> longToShortMap;
    private final ConcurrentHashMap<String, String> shortToLongMap;

    public UrlStats(ConcurrentHashMap<String, String> longToShortMap, ConcurrentHashMap<String, String> shortToLongMap) {
        this.longToShortMap = longToShortMap;
        this.shortToLongMap = shortToLongMap;
    }

    public ConcurrentHashMap<String, String> getLongToShortMap() {
        return longToShortMap;
    }

    public ConcurrentHashMap<String, String> getShortToLongMap() {
        return shortToLongMap;
    }
}