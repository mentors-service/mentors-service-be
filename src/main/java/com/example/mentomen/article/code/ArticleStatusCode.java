package com.example.mentomen.article.code;

public enum ArticleStatusCode {
    ARTICLE_INPROGRESS("PROGRESS"),
    ARTICLE_COMPLETED("COMPLETED");

    private String code;

    private ArticleStatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ArticleStatusCode parseCode(String code) {
        for (ArticleStatusCode statusCode : values()) {
            if (statusCode.getCode().equals(code)) {
                return statusCode;
            }
        }
        return null;
    }
}
