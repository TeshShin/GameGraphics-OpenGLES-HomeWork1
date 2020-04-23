#ifndef OPENGLES_SCENE_H
#define OPENGLES_SCENE_H

#include "global.h"
#include "program.h"
#include "camera.h"
#include "light.h"
#include "object.h"
#include "material.h"

class Scene {

private:
    static Shader* vertexShader;
    static Shader* fragmentShader;
    static Program* program;
    static Camera* camera;
    static Light* light;
    static Object* teapot;
    static Material* flower;

public:
    static void setup(AAssetManager* aAssetManager);
    static void screen(int width, int height);
    static void update(float deltaTime);
};

#endif // OPENGLES_SCENE_H
