package com.example.liangwenchao.appdemo.ui.view.customview;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.net.URL;

/**
 * 评论与回复view
 * Created by ThinkPad on 2016/2/22.
 * http://aichixihongshi.iteye.com/blog/1207503 关于SpannableString讲解
 */
public class CommentTextView<T> extends TextView {

    public static final String DEFAULT_NAME_TEXT_COLOR = "#1081e0";
    public static final String DEFAULT_COMMENT_CONTENT_TEXT_COLOR = "#666666";
    public static final float DEFAULT_COMMENT_TEXT_SIZE = 14f;

    private Context mContext;

    private AsyncTask myAsyncTask = new AsyncTask<URL,Integer,String>() {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(URL... params) {
            return null;
        }


    };

    public CommentTextView(Context context) {
        super(context);
        initData(context);
    }

    public CommentTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public CommentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context){
        this.mContext = context;
        this.setTextSize(DEFAULT_COMMENT_TEXT_SIZE);
        this.setTextColor(Color.parseColor(DEFAULT_COMMENT_CONTENT_TEXT_COLOR));
    }

    /**
     * 添加评论或回复
     * @param commentName 评论人的名字
     * @param replyToName 要回复给谁的名字
     * @param content 评论（回复）内容
     * @param t
     * @param onCommentClickListener
     */
    public void addComment(String commentName,String replyToName,String content,final T t,final OnCommentClickListener onCommentClickListener){

        SpannableString commentNameSpannableStr = new SpannableString(commentName);
        commentNameSpannableStr.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (onCommentClickListener != null) {
                    onCommentClickListener.clickCommentName(t);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.parseColor(DEFAULT_NAME_TEXT_COLOR));
            }
        }, 0, commentName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.append(commentNameSpannableStr);

        // 添加回复给Someone的名字
        if(!TextUtils.isEmpty(replyToName)){
            this.append("回复");
            SpannableString replyToNameSpannableStr = new SpannableString(replyToName);
            replyToNameSpannableStr.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if(onCommentClickListener != null){
                        onCommentClickListener.clickReplyToName(t);
                    }
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(Color.parseColor(DEFAULT_NAME_TEXT_COLOR));
                }
            }, 0, replyToName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.append(replyToNameSpannableStr);
        }

        this.append(":");

        // 添加评论内容
        SpannableString contentSpannableStr = new SpannableString(content);
        contentSpannableStr.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (onCommentClickListener != null) {
                    onCommentClickListener.clickContent(t);
                }
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.parseColor(DEFAULT_COMMENT_CONTENT_TEXT_COLOR));
            }
        }, 0, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.append(contentSpannableStr);

        this.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public interface OnCommentClickListener<T>{
        void clickCommentName(T t);
        void clickReplyToName(T t);
        void clickContent(T t);
    }


}
