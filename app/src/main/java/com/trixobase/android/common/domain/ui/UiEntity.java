package com.trixobase.android.common.domain.ui;

import android.content.ContentValues;

import com.trixobase.android.common.constants.BaseName;

/*
 * Powered by Trixobase Enterprise on 22/01/20.
 * updated on 14/02/21.
 */

public class UiEntity extends UiDomainObject {

    public static class Builder extends UiDomainObject.Builder<UiEntity> {

        protected Builder(ContentValues data) {
            super(data);
        }

        @Override
        protected UiEntity newInstance() {
            return new UiEntity();
        }

    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public int getId() {
        return getData().containsKey(BaseName.ID)
                ? getData().getAsInteger(BaseName.ID) : -1;
    }

    public String getName() {
        return getData().containsKey(BaseName.NAME)
                ? getData().getAsString(BaseName.NAME) : "Missing name";
    }

    public boolean isChecked() {
        return getData().containsKey(BaseName.STATUS)
                ? getData().getAsBoolean(BaseName.STATUS) : false;
    }

    public void setChecked(boolean status) {
        getData().put(BaseName.STATUS, status);
    }

}
