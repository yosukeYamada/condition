package com.example.domain;

import java.math.BigDecimal;

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
    /** 快晴のパーセンテージ：5pt */
    private int clearPercentage;
    /** 晴れのパーセンテージ：4pt */
    private int sunnyPercentage;
    /** 曇りのパーセンテージ：3pt */
    private int cloudyPercentage;
    /** 雨のパーセンテージ：2pt */
    private int rainyPercentage;
    /** 嵐のパーセンテージ：1pt */
    private int stormyPercentage;
    /** 平均スコア */
    private double score;

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

    public void setScoreAverage() {
        int numberOfPost = this.clearCount + this.sunnyCount + this.cloudyCount + this.rainyCount + this.stormyCount;  // 1. 投稿数を算出
        int totalScore = (this.clearCount * 5) + (this.sunnyCount * 4) + (this.cloudyCount * 3) + (this.rainyCount * 2)
                + (this.stormyCount * 1); // 2. スコアの合計を算出
        double score = (double) totalScore / numberOfPost; // 3. (スコアの合計 ÷ 投稿数) で平均スコアを算出
        BigDecimal bd = new BigDecimal(score);
        this.score = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue(); // 4. 小数第2位で四捨五入
    }
}