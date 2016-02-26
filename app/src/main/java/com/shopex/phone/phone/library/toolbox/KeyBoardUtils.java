package com.shopex.phone.phone.library.toolbox;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by samsung on 2016/2/18.
 * 软键盘相关辅助类KeyBoardUtils
 */
public class KeyBoardUtils {

        /**
         * 打卡软键盘
         *
         * @param mEditText
         *            输入框
         * @param mContext
         *            上下文
         */
        public static void openKeybord(EditText mEditText, Context mContext)
        {
            InputMethodManager imm = (InputMethodManager) mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);
        }

        /**
         * 关闭软键盘
         *
         * @param mEditText
         *            输入框
         * @param mContext
         *            上下文
         */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
