package com.kien.gameobjects;


/**
 * @author phamn
 */
public class BulletManager extends ParticularObjectManager {

    public BulletManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObjects() {
        super.UpdateObjects();
        synchronized (particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++) {

                ParticularObject object = particularObjects.get(id);

                if (object.isObjectOutOfCameraView() || object.getState() == ParticularObject.DEATH) {
                    particularObjects.remove(id);    //viên đạn khi ra khỏi khung cam or chạm đối tg khác thì viên đạn phải chết => remove nó
                    //System.out.println("Remove");
                }
            }
        }
    }


}
