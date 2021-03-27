package cm.trixobase.library.common.manager;

import android.content.ContentValues;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cm.trixobase.library.common.constants.BaseName;

/*
 * Powered by Trixobase Enterprise on 05/01/20.
 * updated on 16/03/21.
 */

public class Response {

    private onFrontResult front;
    private onBackResult back;
    private ContentValues data;
    private List<ContentValues> result;

    private static final int CODE_PROCESS_SUCCESS = 202;
    private static final int CODE_PROCESS_FAILED = 404;

    private Response() {
    }

    public interface onFrontResult {
        void onTrue(Response response);
        void onFalse(Response response);
        void onMessage(String message);
    }

    public interface onBackResult {
        void onTrue(JSONObject response);
    }

    public static class Builder {

        final Response instance;

        private Builder() {
            instance = new Response();
            instance.data = new ContentValues();
            instance.result = new ArrayList<>();
            instance.front = new onFrontResult() {
                @Override
                public void onTrue(Response response) {
                    instance.showLog(".onFrontTrue");
                }

                @Override
                public void onFalse(Response response) {
                    instance.showLog(".onFrontFalse");
                }

                @Override
                public void onMessage(String message) {
                    instance.showLog(".onFrontMessage");
                }
            };
            instance.back = response -> {
                instance.showLog(".onBackTrue: " + response.toString());
            };
        }

        public Builder asSuccess(String message) {
            instance.data.put(BaseName.STATUS, true);
            instance.data.put(BaseName.CODE, CODE_PROCESS_SUCCESS);
            instance.data.put(BaseName.MESSAGE, message);
            return this;
        }

        public Builder asSuccess(List<ContentValues> result) {
            instance.data.put(BaseName.STATUS, true);
            instance.data.put(BaseName.CODE, CODE_PROCESS_SUCCESS);
            instance.result.addAll(result);
            return this;
        }

        public Builder asSuccess(ContentValues result) {
            instance.data.put(BaseName.STATUS, true);
            instance.data.put(BaseName.CODE, CODE_PROCESS_SUCCESS);
            instance.result.add(result);
            return this;
        }

        public Builder asSuccess() {
            instance.data.put(BaseName.STATUS, true);
            instance.data.put(BaseName.CODE, CODE_PROCESS_SUCCESS);
            return this;
        }

        public Builder asFailed(String message) {
            instance.data.put(BaseName.STATUS, false);
            instance.data.put(BaseName.CODE, CODE_PROCESS_FAILED);
            instance.data.put(BaseName.MESSAGE, message);
            return this;
        }

        public Response withCode(int code) {
            instance.data.put(BaseName.CODE, code);
            return instance;
        }

        public Builder setFrontAction(onFrontResult action) {
            if (action != null)
                instance.front = action;
            return this;
        }

        public Builder setBackAction(onBackResult action) {
            if (action != null)
                instance.back = action;
            return this;
        }

        public Response build() {
            return instance;
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public static Response builder(onFrontResult front, onBackResult back) {
        return builder().setFrontAction(front).setBackAction(back).build();
    }

    public static Response asFailed(String error) {
        return builder().asFailed(error).build();
    }

    public static Response asSuccess() {
        return builder().asSuccess().build();
    }

    public static Response asSuccess(String message) {
        return builder().asSuccess(message).build();
    }

    public static Response asSuccess(ContentValues result) {
        return builder().asSuccess(result).build();
    }

    public static Response asSuccess(List<ContentValues> result) {
        return builder().asSuccess(result).build();
    }

    public boolean isSuccess() {
        return data.getAsBoolean(BaseName.STATUS);
    }

    public String getMessage() {
        return data.containsKey(BaseName.MESSAGE) ? data.getAsString(BaseName.MESSAGE) : "";
    }

    public List<ContentValues> getResult() {
        return result != null ? result : new ArrayList<>();
    }

    public int getCode() {
        return data.containsKey(BaseName.CODE) ? data.getAsInteger(BaseName.CODE) : -1;
    }

    public onFrontResult getFrontAction() {
        return front;
    }

    public onBackResult getBackAction() {
        return back;
    }

    public void setBackAction(onBackResult back) {
        if (back != null)
            this.back = back;
    }

    public void showFrontMessage(String message) {
        front.onMessage(message);
    }

    public void showLog(String message) {
        Manager.showLog(Response.class, message);
    }

}
