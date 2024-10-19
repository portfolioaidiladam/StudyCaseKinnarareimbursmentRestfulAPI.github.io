package studycaseaplikasiapi.springbeaidil.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import studycaseaplikasiapi.springbeaidil.entity.Reimbursement;
import studycaseaplikasiapi.springbeaidil.service.ReimbursementService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReimbursementControllerTest {

    @Mock
    private ReimbursementService reimbursementService;

    @InjectMocks
    private ReimbursementController reimbursementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubmitReimbursement() {
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setAmount(new BigDecimal("100.00"));
        reimbursement.setDescription("Test reimbursement");

        when(reimbursementService.submitReimbursement(any(Reimbursement.class))).thenReturn(reimbursement);

        ResponseEntity<Reimbursement> response = reimbursementController.submitReimbursement(reimbursement);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(reimbursement, response.getBody());
    }

    @Test
    void testApproveReimbursement() {
        Long id = 1L;
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setId(id);
        reimbursement.setStatus(Reimbursement.ReimbursementStatus.APPROVED);

        when(reimbursementService.approveReimbursement(id)).thenReturn(reimbursement);

        ResponseEntity<Reimbursement> response = reimbursementController.approveReimbursement(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Reimbursement.ReimbursementStatus.APPROVED, response.getBody().getStatus());
    }

    @Test
    void testCheckReimbursement() {
        Long id = 1L;
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setId(id);
        reimbursement.setAmount(new BigDecimal("100.00"));
        reimbursement.setDescription("Test reimbursement");
        reimbursement.setStatus(Reimbursement.ReimbursementStatus.PENDING);
        reimbursement.setSubmissionDate(LocalDateTime.now());

        when(reimbursementService.getReimbursement(id)).thenReturn(reimbursement);

        ResponseEntity<Reimbursement> response = reimbursementController.checkReimbursement(id);

        assertNotNull(response, "Response should not be null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP status should be OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(id, response.getBody().getId(), "Reimbursement ID should match");
        assertEquals(reimbursement, response.getBody(), "Returned reimbursement should match the mock");
    }
}