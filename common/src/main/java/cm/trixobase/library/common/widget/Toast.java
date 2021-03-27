package cm.trixobase.library.common.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cm.trixobase.library.common.R;

/*
 * Powered by Trixobase Enterprise on 26/12/2018.
 */

public class Toast {

    private Context context;
    private String message;
    private int layout;

    private Toast(Context context) {
        this.context = context;}

    public static class Builder {

        private final Toast instance;

        private Builder(Context context) {
            instance = new Toast(context);
            instance.message = "Empty toast message";
            instance.layout = R.layout.my_toast;
        }

        public Builder setMessage(String value) {
            instance.message = value;
            return this;
        }

        public void showLong() {
            instance.showToast(android.widget.Toast.LENGTH_LONG);
        }

        public void showShort() {
            instance.showToast(android.widget.Toast.LENGTH_SHORT);
        }
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    private void showToast(int duration) {
        if (message.isEmpty())
            return;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View my_layout = inflater.inflate(layout, null);
        TextView tv_message = my_layout.findViewById(R.id.tv_toast_id);
        tv_message.setText(message);
        android.widget.Toast toast = new android.widget.Toast(context);
        toast.setDuration(duration);
        toast.setView(my_layout);
        toast.show();
    }

}