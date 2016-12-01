package io.gravitee.policy.configuration;

import io.gravitee.policy.api.PolicyConfiguration;

/**
 * Created by alexandre on 01/12/16.
 */
public class MongoDBPolicyConfiguration implements PolicyConfiguration {

    private String mongoDBResource;

    public String getMongoDBResource() {
        return mongoDBResource;
    }

    public void setMongoDBResource(String mongoDBResource) {
        this.mongoDBResource = mongoDBResource;
    }
}
