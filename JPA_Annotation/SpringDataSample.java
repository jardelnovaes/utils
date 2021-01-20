package br.com.jardelnovaes.gists;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SpringDataSample extends JpaRepository<YourEntity, Long> {
    
    @PessimisticWriteLockWithSkipLockedRows
    @Query("from YourEntity where isAvailable = true")
    List<YourEntity> findNextNAvailableData(Pageable pageable);
}
