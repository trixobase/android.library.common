package cm.trixobase.library.common.manager;

import android.content.ContentValues;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Powered by Trixobase Enterprise on 11/11/19.
 * updated on 15/01/21.
 */

public class DictionaryStructure {

    private ContentValues data;

    private DictionaryStructure() {}

    public static class Builder {

        private DictionaryStructure instance;

        private Builder() {
            instance = new DictionaryStructure();
            instance.data = new ContentValues();
        }

        private Builder(ContentValues data) {
            instance = new DictionaryStructure();
            instance.data = data;
        }

        public Builder withEntry(String key, String value) {
            instance.data.put(key, value);
            return this;
        }

        public Builder withEntry(String key, int value) {
            instance.data.put(key, value);
            return this;
        }

        public Builder withEntry(String key, long value) {
            instance.data.put(key, value);
            return this;
        }

        public Builder withEntry(String key, boolean value) {
            instance.data.put(key, value);
            return this;
        }

        public ContentValues toData() {
            return instance.data;
        }

        public Map<String, Object> toHashMap() {
            return instance.toHashMap();
        }

        public DictionaryStructure build() {
            return instance;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    private Map<String, Object> toHashMap() {
        Map<String,Object> mapping = new LinkedHashMap<>();
        try {
            for (String property : data.toString().split(" ")) {
                String[] value = property.split("=");
                mapping.put(value[0], value[1]);
            }
        } catch (Exception e) {
            showLog("toHashMap: " + e.getMessage());
        }
        return mapping;
    }

    private void showLog(String message) {
        Manager.showLog(DictionaryStructure.class, message);
    }

}
