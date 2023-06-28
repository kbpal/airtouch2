package android.support.constraint.solver.widgets;

/* loaded from: classes.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f0x;

    /* renamed from: y */
    public int f1y;

    public void setBounds(int i, int i2, int i3, int i4) {
        this.f0x = i;
        this.f1y = i2;
        this.width = i3;
        this.height = i4;
    }

    void grow(int i, int i2) {
        this.f0x -= i;
        this.f1y -= i2;
        this.width += i * 2;
        this.height += i2 * 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean intersects(Rectangle rectangle) {
        return this.f0x >= rectangle.f0x && this.f0x < rectangle.f0x + rectangle.width && this.f1y >= rectangle.f1y && this.f1y < rectangle.f1y + rectangle.height;
    }

    public boolean contains(int i, int i2) {
        return i >= this.f0x && i < this.f0x + this.width && i2 >= this.f1y && i2 < this.f1y + this.height;
    }

    public int getCenterX() {
        return (this.f0x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f1y + this.height) / 2;
    }
}
