// **************************************************
// GLRK : the OpenGL Refinement Kernel.
// Camera.
// This file is a part of the GLRK library.
// Author : Tamy Boubekeur (Tamy.Boubekeur@labri.fr).
// Copyright (C) 2006 Tamy Boubekeur.
// All rights reserved.
// **************************************************
#ifndef CAMERA_H
#define CAMERA_H

#include "Vec3.h"
#include "Trackball.h"
#include <GL/gl.h>
#include <GL/glu.h>
#include <iostream>

class Camera {
public:
  Camera ();
  virtual ~Camera () {}
  
  inline float getFovAngle () const { return fovAngle; }
  inline void setFovAngle (float newFovAngle) { fovAngle = newFovAngle; }
  inline float getAspectRatio () const { return aspectRatio; }
  inline float getNearPlane () const { return nearPlane; }
  inline void setNearPlane (float newNearPlane) { nearPlane = newNearPlane; }
  inline float getFarPlane () const { return farPlane; }
  inline void setFarPlane (float newFarPlane) { farPlane = newFarPlane; }
  inline unsigned int getScreenWidth () const { return W; }
  inline unsigned int getScreenHeight () const { return H; }
  
  void resize (int W, int H);
  
  void initPos ();

  void move (float dx, float dy, float dz);
  void beginRotate (int u, int v);
  void rotate (int u, int v);
  void endRotate ();
  void zoom (float z);
  void apply ();
  
  template< typename float_t >
  void getPos (float_t & X, float_t & Y, float_t & Z) {
      GLfloat m[4][4];
      build_rotmatrix(m, curquat);
      float _x = -x;
      float _y = -y;
      float _z = -z + _zoom;
      X = m[0][0] * _x +  m[0][1] * _y +  m[0][2] * _z;
      Y = m[1][0] * _x +  m[1][1] * _y +  m[1][2] * _z;
      Z = m[2][0] * _x +  m[2][1] * _y +  m[2][2] * _z;
    }
  inline void getPos (Vec3 & p) { getPos (p[0], p[1], p[2]); }
  
private:
  float fovAngle;
  float aspectRatio;
  float nearPlane;
  float farPlane;
  
  int spinning, moving;
  int beginu, beginv;
  int H, W;
  float curquat[4];
  float lastquat[4];
  float x, y, z;
  float _zoom;
};

#endif // CAMERA_H
// Some Emacs-Hints -- please don't remove:
//
//  Local Variables:
//  mode:C++
//  tab-width:4
//  End:
