package com.kien.gameobjects;

//import com.gamestudio.state.GameWorldState;

import java.awt.Rectangle;

public abstract class Human extends ParticularObject {

    private boolean isJumping;   //có đang trong trạng thái nhảy ko
    private boolean isDicking;   //có đang trong trạng thái ngồi ko

    private boolean isLanding;   //đang khụy xuống

    public Human(float x, float y, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(x, y, width, height, mass, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run();

    public abstract void jump();

    public abstract void dick();

    public abstract void standUp();

    public abstract void stopRun();

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsLanding(boolean b) {
        isLanding = b;
    }

    public boolean getIsLanding() {
        return isLanding;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean getIsDicking() {
        return isDicking;
    }

    public void setIsDicking(boolean isDicking) {
        this.isDicking = isDicking;
    }

    @Override
    public void Update() {

        super.Update();

        if (getState() == ALIVE || getState() == NOBEHURT) {  //các hoạt động của human phải ở trạng thái sống or bất tử

            if (!isLanding) {  //đang khụy xuống ko thể chạy

                setPosX(getPosX() + getSpeedX());


                if (getDirection() == LEFT_DIR &&
                        getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
                                                                    //khác null là có va chạm vs tường bên trái
                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth() / 2);
                                                // khi va chạm vs tường bên trái có thể bị chồng lên nhau lên cần set lại vị trí hunman phù hợp
                }
                if (getDirection() == RIGHT_DIR &&
                        getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {

                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth() / 2);

                }


                /**
                 * Codes below check the posY of megaMan
                 */
                // plus (+2) because we must check below the character when he's speedY = 0

                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY() != 0 ? getSpeedY() : 2);
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);

                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);

                if (rectTop != null) {//chạm trần

                    setSpeedY(0);
                    setPosY(rectTop.y + getGameWorld().physicalMap.getTileSize() + getHeight() / 2);

                } else if (rectLand != null) { //chạm đất
                    setIsJumping(false);
                    if (getSpeedY() > 0) setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight() / 2 - 1);
                } else { //đang bay
                    setIsJumping(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }

}