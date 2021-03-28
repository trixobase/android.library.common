package com.trixobase.android.common.widget;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.trixobase.android.common.R;

/*
 * Powered by Trixobase Enterprise on 17/04/2020.
 * updated on 15/01/21.
 */

public class SnackBar {

    private Context context;
    private View content;
    private View.OnClickListener action;
    private String actionText, message;

    private SnackBar(Context context) {
        this.context = context;
    }

    public static class Builder {

        private final SnackBar instance;

        private Builder(Context context, View content) {
            instance = new SnackBar(context);
            instance.content = content;
            instance.message = "Attention !!";
            instance.actionText = "";
            instance.action = view -> {};
        }

        public Builder setContent(View content) {
            return this;
        }

        public Builder setMessage(String value) {
            instance.message = value;
            return this;
        }

        public Builder setAction(String actionText, View.OnClickListener action) {
            instance.action = action;
            instance.actionText = actionText;
            return this;
        }

        public void showShort() {
            instance.display(Snackbar.LENGTH_SHORT);
        }

        public void showLong() {
            instance.display(Snackbar.LENGTH_LONG);
        }

    }

    public static Builder builder(Context context, View content) {
        return new Builder(context, content);
    }

    private void display(int duration) {
        Snackbar.make(content, message, duration)
                .setBackgroundTint(context.getResources().getColor(R.color.snack_bar_background))
                .setActionTextColor(context.getResources().getColor(R.color.snack_bar__text_color))
                .setAction(actionText, action).show();
    }

}
