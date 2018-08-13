package com.lijie.Util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.GroupPushClient;
import cn.jpush.api.push.model.notification.*;
import com.google.gson.*;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

public class PushExample {
    protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);

    // demo App defined in resources/jpush-api.conf 
    protected static final String APP_KEY ="6c98d45aac330b9fda9cce4a";
    protected static final String MASTER_SECRET = "7411af2f79ff7d6e54eb26c0";

	public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";

	public static void main(String[] args) {
		testSendPush();
	}


	public static void testSendPush() {
		ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_android_and_ios();
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            System.out.println(result);
            // jpushClient.close();
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
	
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }
    
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios() {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("test", "https://community.jiguang.cn/push");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                		.setAlert("alert content")
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle("Android Title")
                                .addExtras(extras).build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("extra_key", "extra_value").build())
                		.build())
                .build();
    }

    public static void buildPushObject_with_extra() {

        JsonObject jsonExtra = new JsonObject();
        jsonExtra.addProperty("extra1", 1);
        jsonExtra.addProperty("extra2", false);

        Map<String, String> extras = new HashMap<String, String>();
        extras.put("extra_1", "val1");
        extras.put("extra_2", "val2");

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("Android Title")
                                .addExtras(extras)
                                .addExtra("booleanExtra", false)
                                .addExtra("numberExtra", 1)
                                .addExtra("jsonExtra", jsonExtra)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();

        System.out.println(payload.toJSON());
    }
    
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }

    public static PushPayload buildPushObject_android_newly_support() {
        JsonObject inbox = new JsonObject();
        inbox.add("line1", new JsonPrimitive("line1 string"));
        inbox.add("line2", new JsonPrimitive("line2 string"));
        inbox.add("contentTitle", new JsonPrimitive("title string"));
        inbox.add("summaryText", new JsonPrimitive("+3 more"));
        Notification notification = Notification.newBuilder()
                .addPlatformNotification(AndroidNotification.newBuilder()
                        .setAlert(ALERT)
                        .setBigPicPath("path to big picture")
                        .setBigText("long text")
                        .setBuilderId(1)
                        .setCategory("CATEGORY_SOCIAL")
                        .setInbox(inbox)
                        .setStyle(1)
                        .setTitle("Alert test")
                        .setPriority(1)
                        .build())
                .build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(notification)
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .setSendno(ServiceHelper.generateSendno())
                        .build())
                .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_all_tag_not() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag_not("abc", "123"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_cid() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId("18071adc030dcba91c0"))
                .setNotification(Notification.alert(ALERT))
                .setCid("cid")
                .build();
    }

    public static void testSendPushWithCustomConfig() {
        ClientConfig config = ClientConfig.getInstance();
        // Setup the custom hostname
        config.setPushHostName("https://api.jpush.cn");

        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert();

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    public static void testSendIosAlert() {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);

        IosAlert alert = IosAlert.newBuilder()
                .setTitleAndBody("test alert", "subtitle", "test ios alert json")
                .setActionLocKey("PLAY")
                .build();
        try {
            PushResult result = jpushClient.sendIosNotificationWithAlias(alert, new HashMap<String, String>(), "alias1");
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static void testSendWithSMS() {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            SMS sms = SMS.content(1, 10);
            PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "alias1");
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static void testGetCidList() {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            CIDResult result = jPushClient.getCidList(3, "push");
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static void testSendPushWithCid() {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload pushPayload = buildPushObject_android_cid();
        try {
            PushResult result = jPushClient.sendPush(pushPayload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

}

