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

   public static final class AboutMeCTRL {
     public static final String CTRL = BASE_PATH + "/aboutMe";
   }
   public static final class ContactCTRL {
     public static final String CTRL = BASE_PATH + "/contactMe";
   }

 }
