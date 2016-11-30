/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.resource.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import io.gravitee.resource.api.AbstractConfigurableResource;
import io.gravitee.resource.mongodb.configuration.MongoDBResourceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class MongoDBResource extends AbstractConfigurableResource<MongoDBResourceConfiguration> {

    private final Logger LOGGER = LoggerFactory.getLogger(MongoDBResource.class);

    private MongoClient client;
    private MongoDatabase database;

    @Override
    protected void doStart() throws Exception {
        LOGGER.info("Start MongoDB resource with connection URI {}", configuration().getUri());
        client = new MongoClient(new MongoClientURI(configuration().getUri()));
        database = client.getDatabase(configuration().getDatabase());
    }

    @Override
    protected void doStop() throws Exception {
        LOGGER.info("Stop MongoDB resource");
        client.close();
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
