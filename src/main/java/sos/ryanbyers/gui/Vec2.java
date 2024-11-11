package sos.ryanbyers.gui;

import java.util.Objects;

//stores a simple 2-D coordinate
public class Vec2 {
    public int x;
    public int y;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2 vec2 = (Vec2) o;
        return x == vec2.x && y == vec2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
