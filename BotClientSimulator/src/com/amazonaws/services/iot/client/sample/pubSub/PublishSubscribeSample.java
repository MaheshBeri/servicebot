/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.services.iot.client.sample.pubSub;

import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTimeoutException;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.amazonaws.services.iot.client.sample.sampleUtil.CommandArguments;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil.KeyStorePasswordPair;

/**
 * This is an example that uses {@link AWSIotMqttClient} to subscribe to a topic and
 * publish messages to it. Both blocking and non-blocking publishing are
 * demonstrated in this example.
 */
public class PublishSubscribeSample {

    private static final String TestTopic = "acme/temp";
    private static final AWSIotQos TestTopicQos = AWSIotQos.QOS0;

    private  AWSIotMqttClient awsIotClient;
    int temperature;
    String team_id;
    public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public int getTemperature() {
		return temperature;
	}

	public  void setTemperature(int temperature1) {
		temperature = temperature1;
	}

	public  void setClient(AWSIotMqttClient client) {
        awsIotClient = client;
    }

    private  void initClient(CommandArguments arguments) {
        String clientEndpoint = arguments.getNotNull("clientEndpoint", SampleUtil.getConfig("clientEndpoint"));

        int Min=1000;
        int Max=9999;
        int clientId=Min + (int)(Math.random() * ((Max - Min) + 1));
        String clientIdS=""+clientId;
        String certificateFile = arguments.get("certificateFile", SampleUtil.getConfig("certificateFile"));
        String privateKeyFile = arguments.get("privateKeyFile", SampleUtil.getConfig("privateKeyFile"));
        if (awsIotClient == null && certificateFile != null && privateKeyFile != null) {
            String algorithm = arguments.get("keyAlgorithm", SampleUtil.getConfig("keyAlgorithm"));
            KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile, algorithm);

            awsIotClient = new AWSIotMqttClient(clientEndpoint, clientIdS, pair.keyStore, pair.keyPassword);
        }

        if (awsIotClient == null) {
            String awsAccessKeyId = arguments.get("awsAccessKeyId", SampleUtil.getConfig("awsAccessKeyId"));
            String awsSecretAccessKey = arguments.get("awsSecretAccessKey", SampleUtil.getConfig("awsSecretAccessKey"));
            String sessionToken = arguments.get("sessionToken", SampleUtil.getConfig("sessionToken"));

            if (awsAccessKeyId != null && awsSecretAccessKey != null) {
                awsIotClient = new AWSIotMqttClient(clientEndpoint, clientIdS, awsAccessKeyId, awsSecretAccessKey,
                        sessionToken);
            }
        }

        if (awsIotClient == null) {
            throw new IllegalArgumentException("Failed to construct client due to missing certificate or credentials.");
        }
    }

    public  void main(String args[]) throws InterruptedException, AWSIotException, AWSIotTimeoutException {
        CommandArguments arguments = CommandArguments.parse(args);
        initClient(arguments);
        System.out.println("Trying to connect  ...");
        awsIotClient.connect();

        AWSIotTopic topic = new TestTopicListener(TestTopic, TestTopicQos);
        String payload = "{\"temperature\":"+getTemperature()+","+
        					"\"team_id\":\""+getTeam_id()+"\"}";        
        System.out.println("Trying to publish ...");
        awsIotClient.publish(TestTopic, payload);
            
       // awsIotClient.disconnect();
    }

}
