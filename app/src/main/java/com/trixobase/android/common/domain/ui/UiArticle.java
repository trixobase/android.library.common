package com.trixobase.android.common.domain.ui;

import android.content.ContentValues;
import android.content.Context;

import com.trixobase.android.common.R;
import com.trixobase.android.common.constants.BaseName;
import com.trixobase.android.common.manager.Manager;

/*
 * Powered by Trixobase Enterprise on 28/11/20.
 * updated on 24/02/21.
 */

public class UiArticle extends UiDomainObject {

    private String ENTITY;

    public static class Builder extends UiDomainObject.Builder<UiArticle> {

        protected Builder(ContentValues data) {
            super(data);
        }

        public UiArticle build() {
            instance.ENTITY = instance.getData().getAsString(BaseName.ENTITY);
            return instance;
        }

        @Override
        protected UiArticle newInstance() {
            return new UiArticle();
        }

    }

    public static Builder builder(ContentValues data) {
        return new Builder(data);
    }

    public String getEntity() {
        return getData().getAsString(BaseName.ENTITY);
    }

    public long getEntityId() {
        return getData().getAsLong(BaseName.ENTITY_ID);
    }

    public String getDisplayCount() {
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
            case BaseName.ENTITY_PLATE:
            case BaseName.ENTITY_PRODUCT:
            case BaseName.ENTITY_SHOES:
            case BaseName.ENTITY_WEAR:
                return getCountNumber();
            case BaseName.ENTITY_HAIRCUT:
            case BaseName.ENTITY_ROOM:
            case BaseName.ENTITY_SPECTACLE:
            case BaseName.ENTITY_TABLE:
                return Manager.date.getDateFromSql(getData().getAsString(BaseName.START));
            default:
                return "Missing implementation";
        }
    }

    public String getCountNumber() {
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
            case BaseName.ENTITY_PLATE:
            case BaseName.ENTITY_PRODUCT:
            case BaseName.ENTITY_SHOES:
            case BaseName.ENTITY_WEAR:
                return computeCount(getCount());
            case BaseName.ENTITY_HAIRCUT:
            case BaseName.ENTITY_TABLE:
            case BaseName.ENTITY_SPECTACLE:
            case BaseName.ENTITY_ROOM:
                return computeCount(1);
            default:
                return computeCount(0);
        }
    }

    public String getDisplayAmount() {
        return computePrice(getAmount());
    }

    public String getDisplayPrice() {
        return computePrice(getPrice());
    }

    public String getDisplayOldPrice() {
        return getPromotion() > 0 ? computePrice(getData().getAsInteger(BaseName.PRICE)) : "";
    }

    public String getDisplayDateStart() {
        return getData().containsKey(BaseName.START)
                ? getStart() : Manager.date.getSqlDate();
    }

    public int getPrice() {
        return Manager.maths.getPricePromote(getData().getAsInteger(BaseName.PRICE), getPromotion());
    }

    public String getStart() {
        return getData().getAsString(BaseName.START);
    }

    public int getAmount() {
        return getData().getAsInteger(BaseName.AMOUNT);
    }

    public String getTitle(Context context) {
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
            case BaseName.ENTITY_PLATE:
            case BaseName.ENTITY_PRODUCT:
            case BaseName.ENTITY_SHOES:
            case BaseName.ENTITY_WEAR:
                return getData().getAsString(BaseName.NAME);
            case BaseName.ENTITY_HAIRCUT:
            case BaseName.ENTITY_TABLE:
            case BaseName.ENTITY_SPECTACLE:
            case BaseName.ENTITY_ROOM:
                return computeDate(context, getStart());
            default:
                return "Missing implementation";
        }
    }

    public String getName() {
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
            case BaseName.ENTITY_HAIRCUT:
            case BaseName.ENTITY_PLATE:
            case BaseName.ENTITY_PRODUCT:
            case BaseName.ENTITY_TABLE:
            case BaseName.ENTITY_ROOM:
            case BaseName.ENTITY_SHOES:
            case BaseName.ENTITY_SPECTACLE:
            case BaseName.ENTITY_WEAR:
                return getData().getAsString(BaseName.NAME);
            default:
                return "Missing implementation";
        }
    }

    public String getType() {
        return getData().getAsString(BaseName.TYPE);
    }

    public String getDescription() {
        return getData().getAsString(BaseName.DESCRIPTION);
    }

    public int getCount() {
        return getData().containsKey(BaseName.COUNT) ? getData().getAsInteger(BaseName.COUNT) : 0;
    }

    public String getPicture() {
        return getData().getAsString(BaseName.FILE_URL);
    }

    public String getAdvise(Context context) {
        String type = getType();
        switch (ENTITY) {
            case BaseName.ENTITY_PLATE:
                switch (type) {
                    case BaseName.TYPE_PLATE_FASTFOOD:
                    case BaseName.TYPE_PLATE_PIZZA:
                        return context.getString(R.string.moderate_consomation);
                    default:
                        return context.getString(R.string.good_consomation);
                }
            case BaseName.ENTITY_DRINK:
                switch (type) {
                    case BaseName.TYPE_DRINK_BEER:
                    case BaseName.TYPE_DRINK_WINE:
                    case BaseName.TYPE_DRINK_CHAMPAGNE:
                    case BaseName.TYPE_DRINK_SKY:
                        return context.getString(R.string.forbided_consomation);
                    case BaseName.TYPE_DRINK_JUICE:
                        return context.getString(R.string.moderate_consomation);
                    default:
                        return context.getString(R.string.good_consomation);
                }
            default:
                return "";
        }
    }

    public String getAlreadyPresentMessage(Context context) {
        switch (getEntity()) {
            case BaseName.ENTITY_HAIRCUT:
                return context.getString(R.string.error_haircut_already_adding);
            default:
                return "Missing implementation";
        }
    }

    public int getSelection() {
        int count = getCount();
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
                switch (getType()) {
                    case BaseName.TYPE_DRINK_CHAMPAGNE:
                    case BaseName.TYPE_DRINK_SKY:
                    case BaseName.TYPE_DRINK_WATER:
                    case BaseName.TYPE_DRINK_WINE:
                        return count - 1;
                    default:
                        switch (count) {
                            case 3:
                                return 1;
                            case 6:
                                return 2;
                            case 12:
                                return 3;
                            case 24:
                                return 4;
                            default:
                                return 0;
                        }
                }
            default:
                return count > 4 ? 4 : count - 1;
        }
    }

    public int getSpinnerList() {
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
                switch (getType()) {
                    case BaseName.TYPE_DRINK_CHAMPAGNE:
                    case BaseName.TYPE_DRINK_SKY:
                    case BaseName.TYPE_DRINK_WATER:
                    case BaseName.TYPE_DRINK_WINE:
                        return R.array.list_choice_min;
                    default:
                        return R.array.list_choice_drink;
                }
            default:
                return R.array.list_choice_min;
        }
    }

    public String getSaveMessage(Context context, boolean forEdition) {
        switch (ENTITY) {
            case BaseName.ENTITY_HAIRCUT:
            case BaseName.ENTITY_SPECTACLE:
            case BaseName.ENTITY_TABLE:
                return getValidationDate(context, getStart());
            case BaseName.ENTITY_PRODUCT:
            case BaseName.ENTITY_SHOES:
            case BaseName.ENTITY_WEAR:
            default:
                return getValidationArticle(context, forEdition, getCount());
        }}

    private String getValidationDate(Context context, String sqlDate) {
        if (isToday(sqlDate))
            return String.format(context.getString(R.string.add_haircut_chart_today)
                    , Manager.time.getTimeFromSqlDate(sqlDate));
        else if (isTomorrow(sqlDate))
            return String.format(context.getString(R.string.add_haircut_chart_tomorrow)
                    , Manager.time.getTimeFromSqlDate(sqlDate));
        else return String.format(context.getString(R.string.add_haircut_chart)
                    , Manager.compute.dateWithSqlMonth(context.getResources(), sqlDate)
                    , Manager.time.getTimeFromSqlDate(sqlDate));
    }

    private String getValidationArticle(Context context, boolean forEdition, int count) {
        return forEdition
                ? String.format(context.getString(R.string.edit_product_chart), count)
                : String.format(context.getString(R.string.add_product_chart), count);
    }

    public String getDeleteValidation(Context context) {
        switch (ENTITY) {
            case BaseName.ENTITY_DRINK:
            case BaseName.ENTITY_PLATE:
            case BaseName.ENTITY_PRODUCT:
            case BaseName.ENTITY_SHOES:
            case BaseName.ENTITY_WEAR:
                return context.getString(R.string.warning_delete_of_chart);
            case BaseName.ENTITY_HAIRCUT:
            case BaseName.ENTITY_TABLE:
            case BaseName.ENTITY_SPECTACLE:
            case BaseName.ENTITY_ROOM:
                return context.getString(R.string.warning_cancel_of_chart);
            default:
                return "Missing implementation";
        }
    }

    public void setAmount(int amount) {
        getData().put(BaseName.AMOUNT, amount);
    }

    public void setCount(int count) {
        getData().put(BaseName.COUNT, count);
    }

    public void setDateStart(String start) {
        getData().put(BaseName.START, start);
    }

    public void setDateEnd(String end) {
        getData().put(BaseName.END, end);
    }

    private int getPromotion() {
        return getData().containsKey(BaseName.PROMOTION)
                ? getData().getAsInteger(BaseName.PROMOTION) : 0;
    }

    private String computePrice(int price) {
        return new StringBuilder((Manager.compute.price(price))).append(BaseName.DEVISE_FCFA).toString();
    }

    private String computeCount(int count) {
        return new StringBuilder("x").append(count).toString();
    }

    private String computeDate(Context context, String sqlDate) {
        return Manager.compute.dateWithSqlMonth(context.getResources(), sqlDate);
    }

    private boolean isToday(String sqlDate) {
        return Manager.date.isTodayBySqlDate(sqlDate);
    }

    private boolean isTomorrow(String sqlDate) {
        return Manager.date.isTomorrowBySqlDate(sqlDate);
    }

}
