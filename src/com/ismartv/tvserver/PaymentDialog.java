package com.ismartv.tvserver;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PaymentDialog extends Dialog {

	private Context context;
	private int width;
	private int height;

	public PaymentDialog(Context context) {
		super(context);
	}

	public PaymentDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.paymentselect);
		resizeWindow();
	}

	private void resizeWindow() {
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = ((int) (width * 0.8));
		lp.height = ((int) (height * 0.8));
//		lp.alpha = 0.7f;
	}

}