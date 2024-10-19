package studycaseaplikasiapi.springbeaidil.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reimbursements")
public class ReimbursementDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Column
    private LocalDateTime approvalDate;

    @Column
    private String status;

    private ReimbursementDTO(Builder builder) {
        this.user = builder.user;
        this.amount = builder.amount;
        this.description = builder.description;
        this.submissionDate = builder.submissionDate;
        this.approvalDate = builder.approvalDate;
        this.status = builder.status;
    }

    public static class Builder {
        private UserDTO user;
        private BigDecimal amount;
        private String description;
        private LocalDateTime submissionDate;
        private LocalDateTime approvalDate;
        private String status;

        public Builder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder submissionDate(LocalDateTime submissionDate) {
            this.submissionDate = submissionDate;
            return this;
        }

        public Builder approvalDate(LocalDateTime approvalDate) {
            this.approvalDate = approvalDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public ReimbursementDTO build() {
            return new ReimbursementDTO(this);
        }
    }
}