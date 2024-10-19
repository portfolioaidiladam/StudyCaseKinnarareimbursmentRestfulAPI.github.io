package studycaseaplikasiapi.springbeaidil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studycaseaplikasiapi.springbeaidil.entity.Reimbursement;
import studycaseaplikasiapi.springbeaidil.service.ReimbursementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reimbursements")
public class ReimbursementController {

    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @PostMapping
    public ResponseEntity<Reimbursement> submitReimbursement(@Valid @RequestBody Reimbursement reimbursement) {
        Reimbursement submitted = reimbursementService.submitReimbursement(reimbursement);
        return ResponseEntity.status(HttpStatus.CREATED).body(submitted);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Reimbursement> approveReimbursement(@PathVariable Long id) {
        Reimbursement approved = reimbursementService.approveReimbursement(id);
        return ResponseEntity.ok(approved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reimbursement> checkReimbursement(@PathVariable Long id) {
        Reimbursement reimbursement = reimbursementService.getReimbursement(id);
        return ResponseEntity.ok(reimbursement);
    }


    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}