package emsi.ssii.devoir.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import emsi.ssii.devoir.models.Software;

public interface SoftwareRepo extends JpaRepository<Software, Integer> {

}
