package com.example.javaapplication;// Used for the call to addCallback() within this snippet.

import android.content.Context;
import android.util.Log;

import androidx.ads.identifier.AdvertisingIdClient;
import androidx.ads.identifier.AdvertisingIdInfo;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MyAdIdClient {
    String AdvertisingId;


    public String determineAdvertisingInfo(Context context) {
        if (AdvertisingIdClient.isAdvertisingIdProviderAvailable(context)) {
            ListenableFuture<AdvertisingIdInfo> advertisingIdInfoListenableFuture =
                    AdvertisingIdClient.getAdvertisingIdInfo(context);

            Executor executor = Executors.newSingleThreadExecutor();

            Futures.addCallback(advertisingIdInfoListenableFuture,
                    new FutureCallback<AdvertisingIdInfo>() {
                        @Override
                        public void onSuccess(AdvertisingIdInfo adInfo) {
                            AdvertisingId = adInfo.getId();
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e("MY_APP_TAG",
                                    "Failed to connect to Advertising ID provider.");
                        }

                    }, executor);

            return AdvertisingId;
        } else {
            Log.e("MY_APP_TAG",
                    "Клиентская библиотека Advertising ID недоступна. Используйте другую библиотеку.");
        }
        return null;
    }
}