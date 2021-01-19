package com.kollus.demo.repository;

import com.kollus.demo.model.PlayCallbackPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayCallbackPolicyRepository extends JpaRepository<PlayCallbackPolicy, String> {

    @Query(value = "SELECT p FROM PlayCallbackPolicy p WHERE (p.channel_key = :channel_key and p.cuid is null and p.media_content_key is null) or p.channel_key = 'default'")
    List<PlayCallbackPolicy> findPlayCallbackPolicyByChannel_keyWithDefault(String channel_key);



    @Query(value = "SELECT p FROM PlayCallbackPolicy p WHERE (p.media_content_key =:mck and p.channel_key = :channel_key and p.cuid is null) or p.channel_key = 'default'")
    List<PlayCallbackPolicy> findPlayCallbackPolicyByChannel_keyAndMckWithDefault(String channel_key, String mck);

    @Query(value = "SELECT p FROM PlayCallbackPolicy p WHERE (p.cuid =:cuid and p.channel_key = :channel_key and p.media_content_key is null) or p.channel_key = 'default'")
    List<PlayCallbackPolicy> findPlayCallbackPolicyByChannel_keyAndCuidWithDefault(String channel_key, String cuid);

    @Query(value = "SELECT p FROM PlayCallbackPolicy p WHERE (p.cuid =:cuid and p.channel_key = :channel_key and p.media_content_key = :media_content_key)"
            + " or p.channel_key = 'default'")
    List<PlayCallbackPolicy> findPlayCallbackPolicyByChannel_keyAndCuidAndMckWithDefault(String channel_key, String cuid, String media_content_key);

    @Query(value = "SELECT p FROM PlayCallbackPolicy p WHERE (p.channel_key is null and p.cuid = :cuid and p.media_content_key is null) or p.channel_key = 'default'")
    List<PlayCallbackPolicy> findPlayCallbackPoliciesByCuidWithDefault(String cuid);

    @Query(value = "SELECT p FROM PlayCallbackPolicy p Where p.channel_key = :channel_key or p.channel_key = 'default'")
    List<PlayCallbackPolicy> findPlayCallbackPoliciesForCallback(String channel_key);
}
