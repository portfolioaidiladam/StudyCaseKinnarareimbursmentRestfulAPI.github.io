package studycaseaplikasiapi.springbeaidil.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import studycaseaplikasiapi.springbeaidil.entity.Reimbursement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReimbursementResponse {
    private Long id;
    private Long userId;
    private String username;
    private BigDecimal amount;
    private String description;
    private LocalDateTime submissionDate;
    private LocalDateTime approvalDate;
    private String status;

    public static ReimbursementResponse fromReimbursement(Reimbursement reimbursement) {
        return new ReimbursementResponse(
                reimbursement.getId(),
                reimbursement.getUser().getId(),
                reimbursement.getUser().getUsername(),
                reimbursement.getAmount(),
                reimbursement.getDescription(),
                reimbursement.getSubmissionDate(),
                reimbursement.getApprovalDate(),
                reimbursement.getStatus().name()
        );
    }
}