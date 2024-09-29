package com.social.social_media.dtos;

import java.util.List;
import java.util.UUID;

public class UUIDsRequest {
    private List<UserFollowerUUID> uuids;

    public List<UserFollowerUUID> getUuids() {
        return uuids;
    }

    public void setUuids(List<UserFollowerUUID> uuids) {
        this.uuids = uuids;
    }
}

