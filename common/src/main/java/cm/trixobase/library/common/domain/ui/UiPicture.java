package cm.trixobase.library.common.domain.ui;

/*
 * Powered by Trixobase Enterprise on 17/11/20.
 */

import android.content.ContentValues;

import cm.trixobase.library.common.constants.BaseName;

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
