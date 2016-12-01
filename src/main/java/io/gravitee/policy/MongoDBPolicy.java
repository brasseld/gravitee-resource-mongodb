package io.gravitee.policy;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import io.gravitee.gateway.api.ExecutionContext;
import io.gravitee.gateway.api.Request;
import io.gravitee.gateway.api.Response;
import io.gravitee.policy.api.PolicyChain;
import io.gravitee.policy.api.PolicyResult;
import io.gravitee.policy.api.annotations.OnRequest;
import io.gravitee.policy.configuration.MongoDBPolicyConfiguration;
import io.gravitee.resource.api.ResourceManager;
import io.gravitee.resource.mongodb.MongoDBResource;

/**
 * Created by alexandre on 01/12/16.
 */
public class MongoDBPolicy {

    private MongoDBPolicyConfiguration mongoDBPolicyConfiguration;

    public MongoDBPolicy(MongoDBPolicyConfiguration mongoDBPolicyConfiguration) {
        this.mongoDBPolicyConfiguration = mongoDBPolicyConfiguration;
    }

    @OnRequest
    public void onRequest(Request request, Response response, ExecutionContext executionContext, PolicyChain policyChain) {
        ResourceManager resourceManager = executionContext.getComponent(ResourceManager.class);
        MongoDBResource mongoDBResource = resourceManager.getResource(
                mongoDBPolicyConfiguration.getMongoDBResource(),
                MongoDBResource.class
        );

        if(mongoDBResource == null) {
            policyChain.failWith(PolicyResult.failure("Resource unavailable"));
        }

        DBCollection cityCollection = (DBCollection)mongoDBResource.getDatabase().getCollection("cities");

        BasicDBObject test = new BasicDBObject("zip", 59000);

        cityCollection.findOne(test);
    }
}
