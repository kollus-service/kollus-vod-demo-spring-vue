package com.kollus.demo.eduwillvod.repository;


import com.kollus.demo.eduwillvod.model.JwtPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtPolicyRepository extends JpaRepository<JwtPolicy, String> {
    @Query(value = "SELECT j FROM JwtPolicy j WHERE (j.channel_key = :channel_key and j.cuid is null and j.media_content_key is null) or j.channel_key = 'default'")
    List<JwtPolicy> findJwtPolicyByChannel_keyWithDefault(String channel_key);

    @Query(value = "SELECT j FROM JwtPolicy j WHERE (j.media_content_key =:mck and j.channel_key = :channel_key and j.cuid is null) or j.channel_key = 'default'")
    List<JwtPolicy> findJwtPolicyByChannel_keyAndMckWithDefault(String channel_key, String mck);

    @Query(value = "SELECT j FROM JwtPolicy j WHERE (j.cuid =:cuid and j.channel_key = :channel_key and j.media_content_key is null) or j.channel_key = 'default'")
    List<JwtPolicy> findJwtPolicyByChannel_keyAndCuidWithDefault(String channel_key, String cuid);

    @Query(value = "SELECT j FROM JwtPolicy j WHERE (j.cuid =:cuid and j.channel_key = :channel_key and j.media_content_key = :media_content_key)"
            + " or j.channel_key = 'default'")
    List<JwtPolicy> findJwtPolicyByChannel_keyAndCuidAndMckWithDefault(String channel_key, String cuid, String media_content_key);

    @Query(value = "SELECT j FROM JwtPolicy j WHERE (j.channel_key is null and j.cuid = :cuid and j.media_content_key is null) or j.channel_key = 'default'")
    List<JwtPolicy> findJwtPoliciesByCuidWidthDefault(String cuid);

    @Query(value = "SELECT j FROM JwtPolicy j Where j.channel_key = :channel_key or j.channel_key = 'default'")
    List<JwtPolicy> findJwtPolicyForPlayerUrl(String channel_key);

}
