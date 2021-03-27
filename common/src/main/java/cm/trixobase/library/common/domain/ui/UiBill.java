package cm.trixobase.library.common.domain.ui;

import android.content.ContentValues;
import android.content.res.Resources;

import cm.trixobase.library.common.constants.BaseName;
import cm.trixobase.library.common.manager.Manager;

/*
 * Powered by Trixobase Enterprise on 11/12/19.
 * update on 26/11/20.
 */

public class UiBill extends UiDomainObject {

    public static class Builder extends UiDomainObject.Builder<UiBill> {

        protected Builder(ContentValues data) {
            super(data);
        }

        @Override
        protected UiBill newInstance() {
            return new UiBill();
        }

    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public String getReference() {
        return getData().getAsString(BaseName.REFERENCE);
    }

    public int getMatricule() {
        return getData().getAsInteger(BaseName.MATRICULE);
    }

    public int getAmount() {
        return getData().getAsInteger(BaseName.AMOUNT);
    }

    public String getAmountComputed() {
        return new StringBuilder().append(Manager.compute.price(getData().getAsInteger(BaseName.AMOUNT))).append(BaseName.DEVISE_FCFA).toString();
    }

    public String getAddedDate(Resources resources) {
        return Manager.compute.dateWithMonth(resources, Manager.date.getDateFromSql(getData().getAsString(BaseName.ADDED)));
    }

    public String getAddedHour() {
        return Manager.time.getTimeFromSqlDate(getData().getAsString(BaseName.ADDED));
    }

    public String getStatus() {
        return getData().getAsString(BaseName.STATUS);
    }

}
