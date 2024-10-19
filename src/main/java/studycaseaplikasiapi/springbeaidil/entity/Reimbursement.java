package studycaseaplikasiapi.springbeaidil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reimbursements")
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Column
    private LocalDateTime approvalDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReimbursementStatus status;

    public enum ReimbursementStatus {
        PENDING, APPROVED, REJECTED
    }

    // Remove the Builder pattern as it's not typically used with JPA entities
}
