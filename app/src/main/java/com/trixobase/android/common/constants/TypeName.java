package com.trixobase.android.common.constants;

import android.content.Context;

import cm.trixobase.library.common.R;

/*
 * Powered by Trixobase Enterprise on 17/11/20.
 * updated on 22/02/21.
 */

class TypeName extends ColumnName {

    public static final String ENTITY_ACCOUNT = "account";
    public static final String ENTITY_ACTIVITY = "activity";
    public static final String ENTITY_CONTACT = "contact";
    public static final String ENTITY_BILL = "bill";
    public static final String ENTITY_DRINK = "drink";
    public static final String ENTITY_HAIRCUT = "haircut";
    public static final String ENTITY_GROUP = "group";
    public static final String ENTITY_MESSAGE = "message";
    public static final String ENTITY_PICTURE = "picture";
    public static final String ENTITY_PLATE = "plate";
    public static final String ENTITY_PRODUCT = "product";
    public static final String ENTITY_ROOM = "room";
    public static final String ENTITY_SPECTACLE = "spectacle";
    public static final String ENTITY_SHOES = "shoes";
    public static final String ENTITY_TABLE = "table";
    public static final String ENTITY_WEAR = "wear";

    public static final String TYPE_STATUS_RECEIVE = "receive";
    public static final String TYPE_STATUS_PENDING = "pending";
    public static final String TYPE_STATUS_PROGRESS = "progress";

    public static final String TYPE_DRINK_BEER = "beer";
    public static final String TYPE_DRINK_WINE = "wine";
    public static final String TYPE_DRINK_CHAMPAGNE = "champagne";
    public static final String TYPE_DRINK_SKY = "sky";
    public static final String TYPE_DRINK_JUICE = "juice";
    public static final String TYPE_DRINK_WATER = "water";

    public static final String TYPE_PRODUCT_COSMETIC = "cosmetic";
    public static final String TYPE_PRODUCT_LOTION = "lotion";
    public static final String TYPE_PRODUCT_PARFUN = "parfun";
    public static final String TYPE_PRODUCT_SHAMPOOING = "shampooing";

    public static final String TYPE_PLATE_PIZZA = "pizza";
    public static final String TYPE_PLATE_FASTFOOD = "fastfood";

    public static final String TYPE_HAIRCUT_NATTE = "natte";
    public static final String TYPE_HAIRCUT_CHIGNON = "chignon";

    public static final String CATEGORY_BUSINESS = "business";
    public static final String CATEGORY_ENJOY = "enjoy";
    public static final String CATEGORY_FAMILY = "family";
    public static final String CATEGORY_WORK = "work";
    public static final String CATEGORY_SPORT = "sport";
    public static final String CATEGORY_STUDY = "study";

    public static String Of(Context context, String type) {
        switch (type) {
            case ENTITY_DRINK:
                return context.getString(R.string.entity_drink);
            case ENTITY_HAIRCUT:
                return context.getString(R.string.entity_haircut);
            case ENTITY_PRODUCT:
                return context.getString(R.string.entity_product);

            case TYPE_DRINK_BEER:
                return context.getString(R.string.type_drink_beer);
            case TYPE_DRINK_WINE:
                return context.getString(R.string.type_drink_wine);
            case TYPE_DRINK_CHAMPAGNE:
                return context.getString(R.string.type_drink_champagne);
            case TYPE_DRINK_SKY:
                return context.getString(R.string.type_drink_sky);
            case TYPE_DRINK_JUICE:
                return context.getString(R.string.type_drink_juice);

            case TYPE_HAIRCUT_CHIGNON:
                return context.getString(R.string.type_haircut_chignon);
            case TYPE_HAIRCUT_NATTE:
                return context.getString(R.string.type_haircut_natte);

            case TYPE_PRODUCT_COSMETIC:
                return context.getString(R.string.type_product_cosmetic);
            case TYPE_PRODUCT_LOTION:
                return context.getString(R.string.type_product_lotion);
            case TYPE_PRODUCT_PARFUN:
                return context.getString(R.string.type_product_parfun);
            case TYPE_PRODUCT_SHAMPOOING:
                return context.getString(R.string.type_product_shampooing);

            case TYPE_PLATE_PIZZA:
                return context.getString(R.string.type_plate_pizza);
            case TYPE_PLATE_FASTFOOD:
                return context.getString(R.string.type_plate_fastfood);

            case TYPE_STATUS_RECEIVE:
                return context.getString(R.string.status_receive);
            case TYPE_STATUS_PENDING:
                return context.getString(R.string.status_pending);
            case TYPE_STATUS_PROGRESS:
                return context.getString(R.string.status_progress);

            default:
                return type;
        }
    }
}
