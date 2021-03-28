package com.trixobase.android.common.domain.ui;

import android.content.ContentValues;

/*
 * Powered by Trixobase Enterprise on 17/11/20.
 * updated on 24/02/21.
 */

class UiDomainObject {

    private ContentValues data;

    public abstract static class Builder<T extends UiDomainObject> {
        final T instance;

        protected Builder(ContentValues data) {
            instance = newInstance();
            instance.setData(data);
        }

        protected abstract T newInstance();

        public T build() {
            return instance;
        }

    }

    public ContentValues getData() {
        return data;
    }

    public void setData(ContentValues data) {
        this.data = new ContentValues();
        this.data.putAll(data);
    }

}
