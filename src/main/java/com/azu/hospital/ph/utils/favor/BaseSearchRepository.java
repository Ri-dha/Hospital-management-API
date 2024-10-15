package com.azu.hospital.ph.utils.favor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseSearchRepository<T extends BaseSearchEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    @Modifying
    @Query("update #{#entityName} e set e.searchCount = e.searchCount + 1 where e.id = ?1")
    void incrementSearchCount(ID id);

    @Modifying
    @Query("update #{#entityName} e set e.favorCount = e.favorCount + 1 where e.id = ?1")
    void incrementFavorCount(ID id);

    @Query("SELECT e FROM #{#entityName} e ORDER BY e.searchCount DESC")
    List<T> findMostSearched(Pageable pageable);

    @Query("SELECT e FROM #{#entityName} e ORDER BY e.favorCount DESC")
    List<T> findMostFavored(Pageable pageable);
}
