package com.util.game;

import java.util.Objects;

public class Zhu {
    private int zhuValue;
    private int X;
    private int Y;
    private Zhu topZhu;
    private Zhu rightZhu;
    private Zhu bottomZhu;
    private Zhu leftZhu;
    private boolean isSanLian;

    public Zhu() {
    }

    public int getZhuValue() {
        return zhuValue;
    }

    public void setZhuValue(int zhuValue) {
        this.zhuValue = zhuValue;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Zhu getTopZhu() {
        return topZhu;
    }

    public void setTopZhu(Zhu topZhu) {
        this.topZhu = topZhu;
    }

    public Zhu getRightZhu() {
        return rightZhu;
    }

    public void setRightZhu(Zhu rightZhu) {
        this.rightZhu = rightZhu;
    }

    public Zhu getBottomZhu() {
        return bottomZhu;
    }

    public void setBottomZhu(Zhu bottomZhu) {
        this.bottomZhu = bottomZhu;
    }

    public Zhu getLeftZhu() {
        return leftZhu;
    }

    public void setLeftZhu(Zhu leftZhu) {
        this.leftZhu = leftZhu;
    }

    public boolean isSanLian() {
        return isSanLian;
    }

    public void setSanLian(boolean sanLian) {
        isSanLian = sanLian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zhu zhu = (Zhu) o;
        return zhuValue == zhu.zhuValue &&
                X == zhu.X &&
                Y == zhu.Y &&
                isSanLian == zhu.isSanLian &&
                Objects.equals(topZhu, zhu.topZhu) &&
                Objects.equals(rightZhu, zhu.rightZhu) &&
                Objects.equals(bottomZhu, zhu.bottomZhu) &&
                Objects.equals(leftZhu, zhu.leftZhu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zhuValue, X, Y, topZhu, rightZhu, bottomZhu, leftZhu, isSanLian);
    }
}
