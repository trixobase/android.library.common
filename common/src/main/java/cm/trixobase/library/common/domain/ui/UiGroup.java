package cm.trixobase.library.common.domain.ui;

import android.content.ContentValues;

import java.util.ArrayList;

import cm.trixobase.library.common.R;
import cm.trixobase.library.common.constants.BaseName;

/*
 * Powered by Trixobase Enterprise on 15/04/18.
 * updated on 01/03/21
 */

public class UiGroup extends UiDomainObject {

    public static class Builder extends UiDomainObject.Builder<UiGroup> {

        protected Builder(ContentValues data) {
            super(data);
        }

        @Override
        protected UiGroup newInstance() {
            return new UiGroup();
        }

        public Builder identifiedById(long id) {
            instance.getData().put(BaseName.ID, id);
            return this;
        }
    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public UiGroup setName(String name) {
        getData().put(BaseName.NAME, name);
        return this;
    }

    public UiGroup setCategory(String category) {
        getData().put(BaseName.CATEGORY, category);
        return this;
    }

    public UiGroup setMembersCount(long member) {
        getData().put(BaseName.MATRICULE, member);
        return this;
    }

    public UiGroup setMembersNumber(ArrayList<String> membersNumber) {
        for (int t=0 ; t<membersNumber.size() ; t++)
            getData().put(BaseName.MEMBER + String.valueOf(t), membersNumber.get(t));
        getData().put(BaseName.MEMBER, membersNumber.size());
        return this;
    }

    public String getName() {
        return getData().getAsString(BaseName.NAME);
    }

    public String getCategory() {
        return getData().getAsString(BaseName.CATEGORY);
    }

    public int getPictureCategory() {
        switch (getCategory()) {
            case BaseName.CATEGORY_BUSINESS:
                return R.drawable.iv_group_business;
            case BaseName.CATEGORY_WORK:
                return R.drawable.iv_group_work;
            case BaseName.CATEGORY_SPORT:
                return R.drawable.iv_group_sport;
            case BaseName.CATEGORY_STUDY:
                return R.drawable.iv_group_study;
            case BaseName.CATEGORY_ENJOY:
                return R.drawable.iv_group_enjoy;
            default:
                return R.drawable.iv_group_familiy;
        }
    }

    public String getMembersCount() {
        String count = String.valueOf(getData().getAsLong(BaseName.MEMBER));
        if (count.length() == 1)
            count = 0 + count;
        return count;
    }

}
