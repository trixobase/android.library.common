package com.trixobase.android.common.domain.ui;

import android.content.ContentValues;

import com.trixobase.android.common.constants.BaseName;

/*
 * Powered by Trixobase Enterprise on 17/11/20.
 */

public class UiPicture extends UiDomainObject {

    public static class Builder extends UiDomainObject.Builder<UiPicture> {

        protected Builder(ContentValues data) {
            super(data);
        }

        @Override
        protected UiPicture newInstance() {
            return new UiPicture();
        }
    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public String getFileUrl() {
        return getData().getAsString(BaseName.FILE_URL);
    }

}
