package studycaseaplikasiapi.springbeaidil.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // Singleton instance
    private static UserDTO instance;

    // Private constructor for Singleton
    private UserDTO() {}

    // Singleton getInstance method
    public static UserDTO getInstance() {
        if (instance == null) {
            instance = new UserDTO();
        }
        return instance;
    }

    // Builder class
    public static class UserBuilder {
        private UserDTO userDTO;

        public UserBuilder() {
            userDTO = UserDTO.getInstance();
        }

        public UserBuilder id(Long id) {
            userDTO.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            userDTO.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            userDTO.password = password;
            return this;
        }

        public UserBuilder role(String role) {
            userDTO.role = role;
            return this;
        }

        public UserDTO build() {
            return userDTO;
        }
    }
}