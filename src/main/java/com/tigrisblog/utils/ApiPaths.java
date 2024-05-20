package com.tigrisblog.utils;

 public final class ApiPaths {
  private final static String BASE_PATH="/api";

  public static final class AuthorCTRL {
    public static final String CTRL = BASE_PATH + "/author";
  }
  public  static final class ArticleCTRL {
    public static final String CTRL = BASE_PATH + "/article";
  }
  public static final class CategoryCTRL {
    public static final String CTRL = BASE_PATH + "/category";
  }
}
