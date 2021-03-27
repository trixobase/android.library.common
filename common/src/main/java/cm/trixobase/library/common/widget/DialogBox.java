package cm.trixobase.library.common.widget;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import cm.trixobase.library.common.R;
import cm.trixobase.library.common.constants.BaseName;
import cm.trixobase.library.common.manager.Manager;
import cm.trixobase.library.common.manager.Response;

/*
 * Powered by Trixobase Enterprise on 11/12/18.
 * updated on 20/03/21.
 */

public class DialogBox extends Dialog {

    private ContentValues DATA;
    private View VIEW;
    private Response.onFrontResult ACTIONS;

    private DialogBox(Context context) {
        super(context);
    }

    public static class Builder {

        private final DialogBox instance;
        private final Context context;

        private Builder(Context mContext) {
            this.instance = new DialogBox(mContext);
            this.context = mContext;
            instance.DATA = new ContentValues();
            setTitle("Information !!")
                    .setContent("Oups.. error !!")
                    .setButton("Ok")
                    .setActions(new Response.onFrontResult() {
                @Override
                public void onTrue(Response response) {
                }

                @Override
                public void onFalse(Response response) {
                    instance.showLog("onFalse");
                }

                @Override
                public void onMessage(String message) {
                    instance.showLog("onWarning");
                }
            });
        }

        public Builder setButton(String button) {
            instance.DATA.put("button1", button);
            setView(1);
            return this;
        }

        public Builder setButton(String buttonOne, String buttonTwo) {
            instance.DATA.put("button1", buttonOne);
            instance.DATA.put("button2", buttonTwo);
            setView(2);
            return this;
        }

        public Builder setTitle(String title) {
            instance.DATA.put(BaseName.TITLE, title);
            return this;
        }

        public Builder setContent(String content) {
            instance.DATA.put(BaseName.CONTENT, content);
            return this;
        }

        public Builder setActions(Response.onFrontResult action) {
            instance.ACTIONS = action;
            return this;
        }

        public Builder setContentView(int layout) {
            instance.VIEW =  ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layout, null);
            setView(3);
            return this;
        }

        public Builder setContentView(View view) {
            instance.VIEW = view;
            setView(3);
            return this;
        }

        private Builder setView(int counter) {
            instance.DATA.put(BaseName.COUNTER, counter);
            return this;
        }

        public DialogBox build() {
            return instance;
        }

    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        switch (DATA.getAsInteger(BaseName.COUNTER)) {
            case 1:
                showInformationDialog();
                break;
            case 2:
                showQuestionDialog();
                break;
            case 3:
                showCustomDialog();
                break;

        }
    }

    private void showInformationDialog() {
        setCancelable(true);
        setContentView(R.layout.my_dialog_1);
        MaterialTextView tv_title = findViewById(R.id.tv_dialog_title_id);
        MaterialTextView tv_content = findViewById(R.id.tv_dialog_message_id);
        MaterialButton bt_one = findViewById(R.id.bt_dialog_id);

        tv_title.setText(DATA.getAsString(BaseName.TITLE));
        tv_content.setText(DATA.getAsString(BaseName.CONTENT));
        bt_one.setText(DATA.getAsString("button1"));
        bt_one.setOnClickListener(view -> {
            dismiss();
            ACTIONS.onTrue(null);
        });
    }

    private void showQuestionDialog() {
        setCancelable(false);
        setContentView(R.layout.my_dialog_2);
        MaterialTextView tv_title = findViewById(R.id.tv_dialog_title_id);
        MaterialTextView tv_content = findViewById(R.id.tv_dialog_message_id);
        MaterialButton bt_one = findViewById(R.id.bt_dialog_no_id);
        MaterialButton bt_two = findViewById(R.id.bt_dialog_yes_id);

        tv_title.setText(DATA.getAsString(BaseName.TITLE));
        tv_content.setText(DATA.getAsString(BaseName.CONTENT));
        bt_one.setText(DATA.getAsString("button1"));
        bt_two.setText(DATA.getAsString("button2"));

        bt_one.setOnClickListener(view -> {
            dismiss();
            ACTIONS.onFalse(null);
        });
        bt_two.setOnClickListener(view -> {
            dismiss();
            ACTIONS.onTrue(null);
        });
    }

    private void showCustomDialog() {
        setCancelable(false);
        setContentView(R.layout.my_dialog_3);
        MaterialTextView tv_title = findViewById(R.id.tv_dialog_title_id);
        FrameLayout layout = findViewById(R.id.frameLayout);
        MaterialButton bt_one = findViewById(R.id.bt_dialog_no_id);

        layout.addView(VIEW);
        tv_title.setText(DATA.getAsString(BaseName.TITLE));
        bt_one.setOnClickListener(view -> {
            dismiss();
            ACTIONS.onTrue(null);
        });
    }

    private void showLog(String message) {
        Manager.showLog(DialogBox.class, message);
    }

}