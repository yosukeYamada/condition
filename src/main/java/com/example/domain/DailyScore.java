package com.example.domain;

import lombok.Data;

/**
 * 集計に用いる日別の各コンディションのスコア情報を保持するドメインクラス.
 * 
 * @author yuichiyasui
 */
@Data
public class DailyScore {
    /** 快晴の人数 */
    private int clearCount;
    /** 晴れの人数 */
    private int sunnyCount;
    /** 曇りの人数 */
    private int cloudyCount;
    /** 雨の人数 */
    private int rainyCount;
    /** 嵐の人数 */
    private int stormyCount;
    /** 快晴のパーセンテージ */
    private int clearPercentage;
    /** 晴れのパーセンテージ */
    private int sunnyPercentage;
    /** 曇りのパーセンテージ */
    private int cloudyPercentage;
    /** 雨のパーセンテージ */
    private int rainyPercentage;
    /** 嵐のパーセンテージ */
    private int stormyPercentage;

    /**
     * 各プロパティの人数を元に算出したパーセンテージをセットするメソッド.
     * 
     * @param dailyPostCount 一日の投稿数
     */
    public void setPercentage(int dailyPostCount) {
        // 計算結果をdouble型にして四捨五入してint型に変換して代入
        this.clearPercentage = (int) Math.round((double) this.clearCount * 100 / dailyPostCount);
        this.sunnyPercentage = (int) Math.round((double) this.sunnyCount * 100 / dailyPostCount);
        this.cloudyPercentage = (int) Math.round((double) this.cloudyCount * 100 / dailyPostCount);
        this.rainyPercentage = (int) Math.round((double) this.rainyCount * 100 / dailyPostCount);
        this.stormyPercentage = (int) Math.round((double) this.stormyCount * 100 / dailyPostCount);

    }
}