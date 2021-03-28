package com.trixobase.android.common.domain.ui;

import android.content.ContentValues;

import com.trixobase.android.common.R;
import com.trixobase.android.common.constants.BaseName;

import com.trixobase.android.common.manager.Manager;

/*
 * Powered by Trixobase Enterprise on 11/11/19.
 * updated on 07/02/21.
 */

public class UiAccount extends UiDomainObject {

    public static class Builder extends UiDomainObject.Builder<UiAccount> {

        protected Builder(ContentValues data) {
            super(data);
        }

        @Override
        protected UiAccount newInstance() {
            return new UiAccount();
        }
    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public String getUid() {
        return getData().getAsString(BaseName.UID);
    }

    public String getUsername() {
        return getData().getAsString(BaseName.USERNAME);
    }

    public String getFistName() {
        return getData().getAsString(BaseName.FIRST_NAME);
    }

    public String getLastName() {
        return getData().getAsString(BaseName.LAST_NAME);
    }

    public String getFullName() {
        String fullName = getData().getAsString(BaseName.FULL_NAME);
        return !fullName.isEmpty() ? fullName :
                new StringBuilder(getCivility()).append(" ").append(formatName(getLastName())).append(" ").append(formatName(getFistName())).toString();
    }

    public String getBirthDate() {
        String sqlDate = getData().getAsString(BaseName.DATE_BIRTH);
        return !sqlDate.isEmpty() ? Manager.date.getDateFromSql(sqlDate) : "";
    }

    public String getNumber() {
        return getData().getAsString(BaseName.NUMBER);
    }

    public String getSexe() {
        return getData().getAsString(BaseName.SEXE);
    }

    public String getPictureAvatar() {
        return getData().getAsString(BaseName.AVATAR);
    }

    public int getPictureOperator() {
        switch (getOperator()) {
            case BaseName.OPERATOR_ORANGE:
                return R.drawable.iv_operator_orange;
            case BaseName.OPERATOR_MTN:
                return R.drawable.iv_operator_mtn;
            default:
                return R.drawable.ic_phone_mobile1;
        }
    }

    public String getEmail() {
        return getData().getAsString(BaseName.EMAIL);
    }

    public String getWebSite() {
        return getData().getAsString(BaseName.WEB_SITE);
    }

    public String getAbout() {
        return getData().getAsString(BaseName.ABOUT);
    }

    public void setName(String value) {
        getData().put(BaseName.NAME, value);
    }

    public void setSurname(String value) {
        getData().put(BaseName.SURNAME, value);
    }

    public void setSexe(String value) {
        getData().put(BaseName.SEXE, value);
    }

    public void setNumber(String value) {
        getData().put(BaseName.NUMBER, value);
    }

    public boolean isRegistered() {
        return getData().containsKey(BaseName.ADDED);
    }

    public String getOperator() {
        return Manager.phoneNumber.getOperatorOf(getData().getAsString(BaseName.NUMBER));
    }

    private String getCivility() {
        return getSexe().equalsIgnoreCase("f") ? BaseName.CIVILITY_WOMAN : BaseName.CIVILITY_MAN;
    }

    private String formatName(String name) {
        return Manager.compute.contactName(name);
    }

}
