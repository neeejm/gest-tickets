package emsi.ssii.devoir;

import java.util.Set;

public final class Constants {
    private static final Set<String> roles;

    static {
        roles = Set.of("ADMIN", "DEV", "CLIENT");
    }

    public Constants() {}

    public static String getRole(String role) {
        return roles.stream().filter(r -> r.equals(role)).findFirst().orElse(null);
    }
}
