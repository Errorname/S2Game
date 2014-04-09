package bengine.math;

import bengine.entity.Entity;

public interface BBOwner {
    void handleCollision(Entity entity, double xa, double ya);
}
