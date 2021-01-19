package com.kollus.demo.repository;

import com.kollus.demo.model.LmsData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LmsDataRepository extends JpaRepository<LmsData, Long> {
    @Query(value = "SELECT l FROM LmsData l where l.cuid =:cuid and l.media_content_key = :media_content_key and l.start_at = :start_at")
    LmsData findByCuidAndMedia_content_keyAndStart_at(String cuid, String media_content_key, long start_at);
    @Query(value = "SELECT l FROM LmsData l where l.cuid =:cuid and l.media_content_key = :media_content_key")
    List<LmsData> findByCuidAndMedia_content_key(String cuid, String media_content_key);
}
