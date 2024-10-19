package studycaseaplikasiapi.springbeaidil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studycaseaplikasiapi.springbeaidil.entity.Reimbursement;
import studycaseaplikasiapi.springbeaidil.entity.User;
import studycaseaplikasiapi.springbeaidil.model.ReimbursementDTO;
import studycaseaplikasiapi.springbeaidil.model.UserDTO;

import java.util.List;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {
    List<Reimbursement> findByUser(User user);
    List<Reimbursement> findByStatus(String status);
    List<Reimbursement> findByUserAndStatus(User user, String status);
}