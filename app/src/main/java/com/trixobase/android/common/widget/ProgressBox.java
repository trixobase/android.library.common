package com.trixobase.android.common.widget;

import android.content.Context;

import com.trixobase.android.common.R;

import dmax.dialog.SpotsDialog;

/*
 * Powered by Trixobase Enterprise on 12/01/21.
 * updated on 23/01/21.
 */

public class ProgressBox {

    private Context CONTEXT;
    private String CONTENT;

    private ProgressBox() {
    }

    public static class Builder {

        private final ProgressBox instance;

        private Builder(Context context) {
            this.instance = new ProgressBox();
            this.instance.CONTEXT = context;
            this.instance.CONTENT = context.getString(R
                    .string.please_wait);
        }

        public Builder setContent(String content) {
            this.instance.CONTENT = content;
            return this;
        }

        public SpotsDialog build() {
            return instance.buildDialog();
        }
    }

    private SpotsDialog buildDialog() {
        SpotsDialog spot = new SpotsDialog(CONTEXT, R.style.SpotsDialog);
        spot.setMessage(CONTENT);
        return spot;
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setCancelable(true);
//
//        setContentView(R.layout.my_progress_dialog);
//        MaterialCardView cardView = findViewById(R.id.cp_cardview);
//        ProgressBar progressBar = findViewById(R.id.cp_pbar);
//        MaterialTextView tv_content = findViewById(R.id.cp_title);
//
//        cardView.setCardBackgroundColor(getContext().getResources().getColor(R.color.card_view_background));
//        progressBar.setIndeterminate(true);
//        progressBar.setBackgroundColor(getContext().getResources().getColor(R.color.white));
//        tv_content.setText(CONTENT);
//    }

}
