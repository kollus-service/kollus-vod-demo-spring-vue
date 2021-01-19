package com.kollus.demo.repository;

import com.kollus.demo.model.DrmCallbackPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DrmCallbackPolicyRepository extends JpaRepository<DrmCallbackPolicy, String> {

    @Query(value = "SELECT d FROM DrmCallbackPolicy d WHERE (d.channel_key = :channel_key and d.cuid is null and d.media_content_key is null) or d.channel_key = 'default'")
    List<DrmCallbackPolicy> findDrmCallbackPolicyByChannel_keyWithDefault(String channel_key);

    @Query(value = "SELECT d FROM DrmCallbackPolicy d WHERE (d.channel_key = :channel_key and d.media_content_key = :mck and d.cuid is null) or d.channel_key = 'default'")
    List<DrmCallbackPolicy> findDrmCallbackPolicyByChannel_keyAndMckWithDefault(String channel_key, String mck);


    @Query(value = "SELECT d FROM DrmCallbackPolicy d WHERE (d.cuid =:cuid and d.channel_key = :channel_key and d.media_content_key is null) or d.channel_key = 'default'")
    List<DrmCallbackPolicy> findDrmCallbackPolicyByChannel_keyAndCuidWithDefault(String channel_key, String cuid);

    @Query(value = "SELECT d FROM DrmCallbackPolicy d WHERE (d.cuid =:cuid and d.channel_key = :channel_key and d.media_content_key = :media_content_key)"
            + " or d.channel_key = 'default'")
    List<DrmCallbackPolicy> findDrmCallbackPolicyByChannel_keyAndCuidAndMckWithDefault(String channel_key, String cuid, String media_content_key);

    @Query(value = "SELECT d FROM DrmCallbackPolicy d WHERE (d.channel_key is null and d.cuid = :cuid and d.media_content_key is null) or d.channel_key = 'default'")
    List<DrmCallbackPolicy> findDrmCallbackPoliciesByCuidWithDefault(String cuid);

    @Query(value = "SELECT d FROM DrmCallbackPolicy d Where d.channel_key = :channel_key or d.channel_key = 'default'")
    List<DrmCallbackPolicy> findDrmCallbackPolicyForCallback(String channel_key);
}
