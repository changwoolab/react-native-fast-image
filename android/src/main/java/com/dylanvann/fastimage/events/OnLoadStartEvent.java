package com.dylanvann.fastimage.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class OnLoadStartEvent extends Event {

    public static final String EVENT_NAME = "topFastImageLoadStart";

    private final long mBytesRead;
    private final long mExpectedLength;

    private final String mCachePath;

    public OnLoadStartEvent(int viewId, long bytesRead, long expectedLength, String cachePath) {
        super(viewId);
        mBytesRead = bytesRead;
        mExpectedLength = expectedLength;
        mCachePath = cachePath;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public short getCoalescingKey() {
        // All events for a given view can be coalesced.
        return 0;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap args = Arguments.createMap();
        args.putInt("loaded", (int) mBytesRead);
        args.putInt("total", (int) mExpectedLength);
        args.putString("cachePath", mCachePath);
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), args);
    }
}