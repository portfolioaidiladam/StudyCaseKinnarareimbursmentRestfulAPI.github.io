package studycaseaplikasiapi.springbeaidil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycaseaplikasiapi.springbeaidil.entity.Reimbursement;
import studycaseaplikasiapi.springbeaidil.exeption.ResourceNotFoundException;
import studycaseaplikasiapi.springbeaidil.repository.ReimbursementRepository;


import java.time.LocalDateTime;

@Service
public class ReimbursementService {

    private final ReimbursementRepository reimbursementRepository;

    @Autowired
    public ReimbursementService(ReimbursementRepository reimbursementRepository) {
        this.reimbursementRepository = reimbursementRepository;
    }

    @Transactional
    public Reimbursement submitReimbursement(Reimbursement reimbursement) {
        reimbursement.setSubmissionDate(LocalDateTime.now());
        reimbursement.setStatus(Reimbursement.ReimbursementStatus.PENDING);
        return reimbursementRepository.save(reimbursement);
    }

    @Transactional
    public Reimbursement approveReimbursement(Long id) {
        Reimbursement reimbursement = reimbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reimbursement not found with id: " + id));
        reimbursement.setStatus(Reimbursement.ReimbursementStatus.APPROVED);
        reimbursement.setApprovalDate(LocalDateTime.now());
        return reimbursementRepository.save(reimbursement);
    }

    public Reimbursement getReimbursement(Long id) {
        return reimbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reimbursement not found with id: " + id));
    }
}