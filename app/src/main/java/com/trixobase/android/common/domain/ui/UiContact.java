package com.trixobase.android.common.domain.ui;

import android.content.ContentValues;

import com.trixobase.android.common.constants.BaseName;

import cm.trixobase.library.common.R;
import com.trixobase.android.common.manager.Manager;

/*
 * Powered by Trixobase Enterprise on 13/05/18.
 */

public class UiContact extends UiDomainObject implements Comparable<UiContact> {

    @Override
    public int compareTo(UiContact contact) {
        return this.getName().compareTo(contact.getName());
    }

    private boolean isChecked = false;

    public static class Builder extends UiDomainObject.Builder<UiContact> {
        protected Builder(ContentValues data) {
            super(data);
        }

        @Override
        protected UiContact newInstance() {
            return new UiContact();
        }
    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public String getName() {
        return getData().getAsString(BaseName.NAME);
    }

    public String getDisplayName() {
        return Manager.compute.contactName(getName());
    }

    public String getNumber() {
        return getData().getAsString(BaseName.NUMBER);
    }

    public String getOperator() {
        return Manager.phoneNumber.getOperatorOf(getNumber());
    }

    public int getPictureOperator() {
        switch (getOperator()) {
            case BaseName.OPERATOR_ORANGE:
                return R.drawable.iv_operator_orange;
            case BaseName.OPERATOR_MTN:
                return R.drawable.iv_operator_mtn;
            case BaseName.OPERATOR_NEXTTEL:
                return R.drawable.iv_operator_nexttel;
            case BaseName.OPERATOR_CAMTEL:
                return R.drawable.iv_operator_camtel;
            default:
                return R.drawable.ic_phone_mobile1;
        }
    }

    public long getNote() {
        return getData().getAsLong(BaseName.NOTE);
    }

    public UiContact setName(String name) {
        getData().put(BaseName.NAME, name);
        return this;
    }

    public UiContact setNumber(String number) {
        getData().put(BaseName.NUMBER, number);
        return this;
    }

    public UiContact setOperator(String operator) {
        getData().put(BaseName.OPERATOR, operator);
        return this;
    }


    public UiContact setNote(long note) {
        getData().put(BaseName.NOTE, note);
        return this;
    }

    public void setChecked(boolean state) {
        isChecked = state;
    }

    public boolean isChecked() {
        return isChecked;
    }

}
